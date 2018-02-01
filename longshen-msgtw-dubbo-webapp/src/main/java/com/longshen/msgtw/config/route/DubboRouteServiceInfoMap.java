package com.longshen.msgtw.config.route;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;

@Data
public class DubboRouteServiceInfoMap implements Serializable {

	/**
	 * @字段：serialVersionUID @功能描述：
	 * @创建人：longshen
	 * @创建时间：2018年2月1日上午10:43:27
	 */

	private static final long serialVersionUID = -6923753286522402966L;
	private ConcurrentHashMap<String, DubboRouteServiceInfo> serviceMap = new ConcurrentHashMap<String, DubboRouteServiceInfo>();

	/**
	 * 
	 * @描述:添加服务
	 * @方法名: addDubboRouteServiceInfo
	 * @param serviceId
	 * @param serviceInof
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年2月1日上午10:48:50
	 * @修改人 longshen
	 * @修改时间 2018年2月1日上午10:48:50
	 * @修改备注
	 * @since
	 * @throws
	 */
	public void addDubboRouteServiceInfo(String serviceId, DubboRouteServiceInfo serviceInof) {
       serviceMap.put(serviceId, serviceInof);
	}

	/**
	 * 
	 * @描述:获取服务
	 * @方法名: getDubboRouteServiceInfo
	 * @param serviceId
	 * @return
	 * @返回类型 DubboRouteServiceInfo
	 * @创建人 longshen
	 * @创建时间 2018年2月1日上午10:49:01
	 * @修改人 longshen
	 * @修改时间 2018年2月1日上午10:49:01
	 * @修改备注
	 * @since
	 * @throws
	 */
	public DubboRouteServiceInfo getDubboRouteServiceInfo(String serviceId) {
		
		return serviceMap.get(serviceId);

	}
}
