package com.longshen.msgtw.container.rest.rxnetty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.MDC;

import com.longshen.msgtw.base.container.IContainer;
import com.longshen.msgtw.common.Constants;
import com.longshen.msgtw.common.TradeIdWorker;
import com.longshen.msgtw.common.utils.NetUtils;
import com.longshen.msgtw.container.rest.rxnetty.entity.GatewayREQ;
import com.longshen.msgtw.container.rest.rxnetty.entity.GatewayRES;
import com.longshen.msgtw.container.rest.rxnetty.utils.AssemblySupport;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.IFilterFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.reactivex.netty.channel.RxDefaultThreadFactory;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.server.HttpServerBuilder;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import io.reactivex.netty.spectator.SpectatorEventsListenerFactory;
import io.reactivex.netty.spectator.http.HttpServerListener;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.functions.Func1;

/**
 * 
 * @类描述：基于netty实现的微服务网关容器
 * @项目名称：longshen-msgtw-rest @包名： com.longshen.msgtw.container.rest.rxnetty
 * @类名称：RxNettyContainer
 * @创建人：longshen
 * @创建时间：2018年1月26日下午3:32:47
 * @修改人：longshen
 * @修改时间：2018年1月26日下午3:32:47 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Slf4j
public abstract class RxNettyContainer<REQ extends GatewayREQ<?>, RES extends GatewayRES<?>>
		implements IContainer<REQ, RES> {

	IFilterFactory<REQ, RES> filterFactory;

	TradeIdWorker tradeIdWorker;

	HttpServerListener listener;
	HttpServer<ByteBuf, ByteBuf> server;

	SpectatorEventsListenerFactory factory;

	HttpServerBuilder<ByteBuf, ByteBuf> builder;

	RxNettyConfiguration configuration;

	public RxNettyContainer(IFilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	public RxNettyContainer(IFilterFactory filterFactory, TradeIdWorker tradeIdWorker) {
		this.filterFactory = filterFactory;
		this.tradeIdWorker = tradeIdWorker;
	}

	abstract protected REQ newREQ();

	abstract protected RES newRES();

	@Override
	public void init() throws GtwException {

		if (tradeIdWorker == null)
			tradeIdWorker = new TradeIdWorker(0, 0);
		factory = new SpectatorEventsListenerFactory();
		configuration = new RxNettyConfiguration();

		ServerBootstrap bootstrap = new ServerBootstrap();

		RequestHandler<ByteBuf, ByteBuf> requestHandler = new RequestHandler<ByteBuf, ByteBuf>() {

			@Override
			public Observable<Void> handle(HttpServerRequest<ByteBuf> request,
					final HttpServerResponse<ByteBuf> response) {
				long tradeStartTime = System.currentTimeMillis();
				String tradeId = String.valueOf(tradeIdWorker.nextId());
				MDC.put(Constants.TRADEID_KEY, tradeId);
				log.info("-----交易开始[{}]-----", tradeId);

				final REQ req = newREQ();
				req.setTradeId(tradeId);
				req.setTradeStartTime(tradeStartTime);
				req.setInput(request);
				req.setOutput(response);

				req.setClientUrl(request.getUri());
				try {
					/**
					 * 获取客户端
					 */
					String clientIP = request.getHeaders().get("X-Forwarde-For");
					if (clientIP == null) {
						InetSocketAddress insocket = (InetSocketAddress) request.getNettyChannel().remoteAddress();
						clientIP = insocket.getAddress().getHostAddress();
					}
					req.setClientHost(clientIP);

					// 读取参数
					if (!request.getQueryParameters().isEmpty()) {
						for (Map.Entry<String, List<String>> entry : request.getQueryParameters().entrySet()) {
							if (entry.getValue().size() == 1) {
								req.putClientParam(entry.getKey(), entry.getValue().get(0));
							}
						}

						req.setClientParameters(request.getQueryParameters());
					}

					// 请求头
					List<Entry<String, String>> headers = request.getHeaders().entries();
					for (Entry<String, String> entry : headers) {
						req.putClientHeader(entry.getKey(), entry.getValue());
					}
					// 处理请求逻辑
					return request.getContent().map(new Func1<ByteBuf, Void>() {

						@Override
						public Void call(ByteBuf data) {
							req.setClientContent(data.toString(Charset.defaultCharset()));
							RES res = null;
							try {
								res = filterFactory.doFilter(req);
								if (res == null) {
									res = newRES();
									res.setContent("微服务网关请求处理失败");
								}
							} catch (Exception e) {
								log.error("网关过滤器执行失败{}", e.getMessage(), e);

								res = newRES();
								res.setContent("微服务网关请求处理异常");

							}

							AssemblySupport.httpServerResponse(req, res);

							return null;
						}

					});

				} catch (Exception e) {
					log.error("网关接入回调执行失败,{}", e.getMessage(), e);
					RES res = newRES();
					res.setContent("服务器异常");
					AssemblySupport.httpServerResponse(req, res);
					return null;
				}
			}
		};

		// 设施netty服务
		builder = new HttpServerBuilder<ByteBuf, ByteBuf>(bootstrap, configuration.getPort(), requestHandler, true);
		builder.withMetricEventsListenerFactory(factory);
		builder.withEventExecutorGroup(new DefaultEventExecutorGroup(configuration.getNThreads(),
				new RxDefaultThreadFactory("msgtw-processor")));
		server = builder.build();
		listener = factory.forHttpServer(server);
	}

	@Override
	public void start() throws GtwException {

		server.start();
		startlog();
	}

	@Override
	public void shutdown() throws GtwException {
		if (server != null) {

			try {
				server.shutdown();
			} catch (Exception e) {
				log.error("服务网关停止中出现异常", e);
				throw new GtwException("服务网关停止中出现异常",e);
			}
		}

	}

	

	private void startlog() {
		StringBuffer sb=new StringBuffer();
		sb.append("\n/--------------------------------------------------------------------------------------\n");
		sb.append("-微服务网关启动成功\n");
		sb.append("-服务地址:http://").append(NetUtils.getLocalIp()).append(":").append(server.getServerPort()).append("/\n");
		sb.append("-微服务线程数:").append(configuration.getNThreads()).append("\n");
		sb.append("--------------------------------------------------------------------------------------/");


	}

}
