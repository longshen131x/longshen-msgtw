package com.longshen.msgtw.route;

import com.longshen.msgtw.base.adapter.IAdapter;
/**
 * 
 * @类描述：服务工程
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route
 * @类名称：IRouteServiceFactory
 * @创建人：longshen
 * @创建时间：2018年1月29日下午3:49:39
 * @修改人：longshen
 * @修改时间：2018年1月29日下午3:49:39
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IRouteServiceFactory<RS extends IRouteService<?>> extends IAdapter {
 /**
  * 
  * @描述:增加路由服务
  * @方法名: addRouterService
  * @param routeService
  * @返回类型 void
  * @创建人 longshen
  * @创建时间 2018年1月29日下午3:51:42
  * @修改人 longshen
  * @修改时间 2018年1月29日下午3:51:42
  * @修改备注
  * @since
  * @throws
  */
	void addRouterService(RS routeService);
	/**
	 * 
	 * @描述:获取服务
	 * @方法名: getServiceByID
	 * @param serviceId
	 * @return
	 * @返回类型 RS
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:53:03
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:53:03
	 * @修改备注
	 * @since
	 * @throws
	 */
	RS getServiceByID(String serviceId);
	
	/**
	 * 
	 * @描述:获取存储器配置
	 * @方法名: getRepository
	 * @return
	 * @返回类型 IRouteServiceRespository<RS>
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:00:04
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:00:04
	 * @修改备注
	 * @since
	 * @throws
	 */
	IRouteServiceRespository<RS> getRepository();
	
	/**
	 * 
	 * @描述:设置存储器配置
	 * @方法名: setRepository
	 * @param repository
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:01:02
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:01:02
	 * @修改备注
	 * @since
	 * @throws
	 */
	void setRepository(IRouteServiceRespository<RS> repository);
	/**
	 * 
	 * @描述:属性
	 * @方法名: refresh
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:01:39
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:01:39
	 * @修改备注
	 * @since
	 * @throws
	 */
	void refresh();
	
}
