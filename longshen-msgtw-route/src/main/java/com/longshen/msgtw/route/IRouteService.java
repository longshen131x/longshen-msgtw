package com.longshen.msgtw.route;

import com.longshen.msgtw.exception.GtwException;

/**
 * 
 * @类描述：路由服务接口
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route
 * @类名称：IRouteService
 * @创建人：longshen
 * @创建时间：2018年1月29日下午3:25:15
 * @修改人：longshen
 * @修改时间：2018年1月29日下午3:25:15
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IRouteService<T> {

	/**
	 * 
	 * @描述:构建服务
	 * @方法名: build
	 * @return
	 * @throws GtwException
	 * @返回类型 T
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:26:06
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:26:06
	 * @修改备注
	 * @since
	 * @throws
	 */
	
	T build() throws GtwException;
	/**
	 * 
	 * @描述:获取服务对象
	 * @方法名: getService
	 * @return
	 * @返回类型 T
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:26:45
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:26:45
	 * @修改备注
	 * @since
	 * @throws
	 */
	T getService();
	/**
	 * 
	 * @描述:获取服务URL信息
	 * @方法名: getServiceURL
	 * @return
	 * @返回类型 String
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:27:16
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:27:16
	 * @修改备注
	 * @since
	 * @throws
	 */
	String getServiceURL();
	/**
	 * 
	 * @描述:获取服务唯一表示
	 * @方法名: getServiceID
	 * @return
	 * @返回类型 String
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:28:02
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:28:02
	 * @修改备注
	 * @since
	 * @throws
	 */
	String getServiceID();
	
	/**
	 * 
	 * @描述:获取服务版本
	 * @方法名: getServiceVersion
	 * @return
	 * @返回类型 String
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:28:45
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:28:45
	 * @修改备注
	 * @since
	 * @throws
	 */
	String getServiceVersion();
	
	/**
	 * 
	 * @描述:获取服务路由类型
	 * @方法名: getType
	 * @return
	 * @返回类型 String
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:29:17
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:29:17
	 * @修改备注
	 * @since
	 * @throws
	 */
	String getType();
}
