package com.longshen.msgtw.container.rest.rxnetty.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longshen.msgtw.common.utils.NetUtils;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 网关请求对象
 * @类描述：
 * @项目名称：longshen-msgtw-rest
 * @包名： com.longshen.msgtw.container.rest.rxnetty.entity
 * @类名称：GatewayREQ
 * @创建人：longshen
 * @创建时间：2018年1月26日下午1:55:16
 * @修改人：longshen
 * @修改时间：2018年1月26日下午1:55:16
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class GatewayREQ<T> extends INOUT<HttpServerRequest<ByteBuf>, HttpServerResponse<ByteBuf>> {
	
	public static final String LOACALHOST=NetUtils.getLocalIp();
	
	/**
	 * 交易流水号
	 */
	String tradeId;
	/**
	 * 交易开始时间
	 */
	long  tradeStartTime;
	/**
	 * 交易结束数据
	 */
	long tradeEndTime;
	/**
	 * 后端服务开始时间
	 */
	long routeStartTime;
	/**
	 * 后端服务结束时间
	 */
	long routeEndTime;
	/**
	 * 网关机器HOST
	 */
	String localHost=LOACALHOST;
	/**
	 * k客户端HOST
	 */
	String clientHost;
	/**
	 * 请求报文
	 */
	String clientContent="";
	/**
	 * 自定义数据对象
	 */
	T clientContentData;

	String clientUrl;
	
	Map<String,String> clientParams=new HashMap<String,String>();
	
	Map<String,List<String>> clientParameters=new HashMap<String,List<String>>();
	
	Map<String,String> clientHeaders=new HashMap<String,String>();
	
	
	public void putClientHeader(String key,String value) {
		this.clientHeaders.put(key, value);
	}
	
	public void putClientHeaders(Map<String,String> clientHeaders) {
		this.clientHeaders.putAll(clientHeaders);
	}
	
	public void putClientParam(String key,String value) {
		this.clientParams.put(key, value);
	}
	
	public void putClientParams(Map<String,String> clientParams) {
		this.clientParams.putAll(clientParams);
	}
	
	public void putclientParameter(String key,List<String> values) {
		this.clientParameters.put(key, values);
	}
	
	public void putClientParameters(Map<String,List<String>>  clientParameters) {
		this.clientParameters.putAll(clientParameters);
	}
	
}
