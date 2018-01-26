package com.longshen.msgtw.container.rest.rxnetty;

import org.apache.commons.lang3.math.NumberUtils;

import lombok.Getter;

/**
 * 
 * @类描述：微服务网关配置
 * @项目名称：longshen-msgtw-rest
 * @包名： com.longshen.msgtw.container.rest.rxnetty
 * @类名称：RxNettyConfiguration
 * @创建人：longshen
 * @创建时间：2018年1月26日下午12:03:10
 * @修改人：longshen
 * @修改时间：2018年1月26日下午12:03:10
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public class RxNettyConfiguration {
	
	private final static String VMD_PORT="msgtw.port";
	
	private final static String VMD_THREAD="msgtw.thread";
	
	/**
	 * 默认服务端口号 9000
	 */
	@Getter
	private int port=9000;
	
	/**
	 * 默认线程数 1000
	 */
	@Getter
	private  int nThreads=1000;
	
	/**
	 * 
	 * Title:
	 * Description:优先获取系统变量配置的端口号 和线程数
	 */
    public RxNettyConfiguration() {
    	String sProt=System.getProperty(VMD_PORT);
    	if(sProt !=null&& NumberUtils.isDigits(sProt)) {
    		port=NumberUtils.toInt(sProt);
    	}
    	String sThread=System.getProperty(VMD_THREAD);
    	if(sThread !=null&& NumberUtils.isDigits(sThread)) {
    		nThreads=NumberUtils.toInt(sThread);
    	}
    }

}
