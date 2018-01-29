package com.longshen.msgtw.route.rule;

import java.util.List;

/**
 * 
 * @类描述：路由规则结果
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route.rule
 * @类名称：IRouteResult
 * @创建人：longshen
 * @创建时间：2018年1月29日下午3:30:49
 * @修改人：longshen
 * @修改时间：2018年1月29日下午3:30:49
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IRouteResult<T> {
	/**
	 * 
	 * @描述:是否有路由结果
	 * @方法名: isSuccess
	 * @return
	 * @返回类型 boolean
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:31:59
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:31:59
	 * @修改备注
	 * @since
	 * @throws
	 */
	boolean isSuccess();
	/**
	 * 
	 * @描述:路由服务列表，可能匹配多个
	 * @方法名: getRouteService
	 * @return
	 * @返回类型 List<T>
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:32:21
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:32:21
	 * @修改备注
	 * @since
	 * @throws
	 */
	List<T> getRouteService();

}
