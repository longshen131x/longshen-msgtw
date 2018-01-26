package com.longshen.msgtw.container.rest.rxnetty.utils;

import com.longshen.msgtw.container.rest.rxnetty.entity.GatewayREQ;
import com.longshen.msgtw.container.rest.rxnetty.entity.GatewayRES;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @类描述：静态装配类
 * @项目名称：longshen-msgtw-rest
 * @包名： com.longshen.msgtw.container.rest.rxnetty.utils
 * @类名称：AssemblySupport
 * @创建人：longshen
 * @创建时间：2018年1月26日下午2:05:50
 * @修改人：longshen
 * @修改时间：2018年1月26日下午2:05:50
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
@Slf4j
public class AssemblySupport {
	/**
	 * 
	 * @描述:通道响应
	 * @方法名: HttpServerResponse
	 * @param req
	 * @param res
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年1月26日下午2:07:53
	 * @修改人 longshen
	 * @修改时间 2018年1月26日下午2:07:53
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static void httpServerResponse(GatewayREQ<?> req,GatewayRES<?> res) {
		try {
			req.getOutput().setStatus(HttpResponseStatus.OK);
			req.getOutput().writeStringAndFlush(res.getContent());
			req.getOutput().close();
		} catch (Throwable t) {
         log.error("微服务网关装配异常:{}",t.getMessage(),t);
		}finally {
			log.info("[网关总耗时:{}ms] -----交易结束[{}]-----",(System.currentTimeMillis()-req.getTradeStartTime()),req.getTradeId());
		}
	}

}
