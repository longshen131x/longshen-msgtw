package com.longshen.msgtw.route.dubbo.connector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.esotericsoftware.minlog.Log;
import com.longshen.msgtw.base.connector.IConnector;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.IRouteContext;
import com.longshen.msgtw.route.IRouteService;
import com.longshen.msgtw.route.dubbo.DubboRouteService;
import com.longshen.msgtw.route.rule.IRouteResult;
import com.longshen.msgtw.route.rule.IRouteRuleParam;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述：dubbo服务连接器
 * @项目名称：longshen-msgtw-route-dubbo @包名：
 *                                  com.longshen.msgtw.route.dubbo.connector
 * @类名称：DubboConnector
 * @创建人：longshen
 * @创建时间：2018年1月31日下午3:31:58
 * @修改人：longshen
 * @修改时间：2018年1月31日下午3:31:58 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Slf4j
public abstract class DubboConnector<REQ, RES, PARAM extends IRouteRuleParam, RS extends IRouteResult<?>>
		implements IConnector<REQ, RES> {

	private final String LOG_FORMAT = "** dubbo context[{}]  service[{}] invoke {} ,cost[{}] ms";

	private IRouteContext<DubboRouteService, PARAM, RS> routeContext;

	/**
	 * 
	 * @描述:获取服务上下文 @方法名: getRouteContext @return @返回类型
	 * IRouteContext<DubboRouteService,PARAM,RS> @创建人 longshen @创建时间
	 * 2018年1月31日下午3:38:06 @修改人 longshen @修改时间
	 * 2018年1月31日下午3:38:06 @修改备注 @since @throws
	 */
	protected abstract IRouteContext<DubboRouteService, PARAM, RS> getRouteContext();

	/**
	 * 
	 * @描述:调用前处理 @方法名: preConnector @param req @param args @return @throws
	 * GtwException @返回类型 Object @创建人 longshen @创建时间 2018年1月31日下午3:39:59 @修改人
	 * longshen @修改时间 2018年1月31日下午3:39:59 @修改备注 @since @throws
	 */
	protected abstract Object preConnector(REQ req, Object... args) throws GtwException;

	/**
	 * 
	 * @描述:获取服务规则 @方法名: getRouteRuleParam @param req @param args @return @throws
	 * GtwException @返回类型 PARAM @创建人 longshen @创建时间 2018年1月31日下午3:44:03 @修改人
	 * longshen @修改时间 2018年1月31日下午3:44:03 @修改备注 @since @throws
	 */
	protected abstract PARAM getRouteRuleParam(REQ req, Object... args) throws GtwException;

	/**
	 * 
	 * @描述:调用后处理 @方法名: postConnector @param output @param req @param
	 * args @return @throws GtwException @返回类型 RES @创建人 longshen @创建时间
	 * 2018年1月31日下午3:55:19 @修改人 longshen @修改时间
	 * 2018年1月31日下午3:55:19 @修改备注 @since @throws
	 */
	protected abstract RES postConnector(Object output, REQ req, Object... args) throws GtwException;

	protected abstract RES doException(REQ req, Exception ex, Object... args) throws GtwException;

	/**
	 * 
	 * @描述:初始化服务上下文 @方法名: init @throws
	 * GtwException @创建人：longshen @创建时间：2018年1月31日下午3:58:52 @修改人：longshen @修改时间：2018年1月31日下午3:58:52 @修改备注： @throws
	 */
	@Override
	public void init() throws GtwException {
		routeContext = getRouteContext();
		routeContext.init();
	}

	@Override
	public void start() throws GtwException {
		if (routeContext != null)
			routeContext.start();
	}

	@Override
	public void shutdown() throws GtwException {
		if (routeContext != null)
			routeContext.shutdown();
	}

	/**
	 * 
	 * @描述:服务调用 @方法名: connector @param req @param args @return @throws
	 * GtwException @创建人：longshen @创建时间：2018年1月31日下午4:01:45 @修改人：longshen @修改时间：2018年1月31日下午4:01:45 @修改备注： @throws
	 */
	@Override
	public RES connector(REQ req, Object... args) throws GtwException {
		String serviceId = "";
		long begin = System.currentTimeMillis();
		DubboRouteService service = routeContext.route(getRouteRuleParam(req, args));
		if (service != null) {
			serviceId = service.getServiceID();
			GenericService gs = service.getGs();

			try {
				if (gs != null) {
					Object reqObject = preConnector(req, args);

					Object output = gs.$invoke(service.getMethod(), new String[] { service.getInputName() },
							new Object[] { reqObject });

					RES res = postConnector(output, req, args);
					log.info(LOG_FORMAT, routeContext.getClass().getSimpleName(), serviceId, "successful",
							System.currentTimeMillis() - begin);
					return res;

				} else {
					GtwException ge = new GtwException("999999",
							"dubbo context[" + routeContext.getClass().getSimpleName() + "] 服务未初始化["
									+ToStringBuilder.reflectionToString(req, ToStringStyle.SHORT_PREFIX_STYLE) + "]",null);
					return doException(req, ge, args);
				}
			} catch (Exception e) {
				log.info(LOG_FORMAT, routeContext.getClass().getSimpleName(), serviceId, "fail",
						System.currentTimeMillis() - begin);
				return doException(req, e, args);
			}

		}else {
			GtwException ge = new GtwException("999999",
					"dubbo context[" + routeContext.getClass().getSimpleName() + "] 服务路由信息未找到["
							+ToStringBuilder.reflectionToString(req, ToStringStyle.SHORT_PREFIX_STYLE) + "]",null);
			return doException(req, ge, args);
		}

	}

}
