package com.longshen.msgtw.config.route;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;

/**
 * 
 * @类描述：静态路由规则
 * @项目名称：longshen-msgtw-dubbo-webapp
 * @包名： com.longshen.msgtw.config.route
 * @类名称：RouteStaticRuleInfo
 * @创建人：longshen
 * @创建时间：2018年2月1日下午1:51:20
 * @修改人：longshen
 * @修改时间：2018年2月1日下午1:51:20
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
@Data
public class StaticRouteRuleInfo {
	/**
	 * key  serviceid(msgtype)  value serviceid::methodname
	 */
	private ConcurrentHashMap<String, String> routeMap=new ConcurrentHashMap<String,String>();
	
	
	public String getServiceId(String serviceId) {
		return this.routeMap.get(serviceId);
		
	}

}
