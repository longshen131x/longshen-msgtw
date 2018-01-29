package com.longshen.msgtw.route;

import com.longshen.msgtw.base.adapter.IAdapter;
import com.longshen.msgtw.route.rule.IRouteResult;
import com.longshen.msgtw.route.rule.IRouteRuleFactory;
import com.longshen.msgtw.route.rule.IRouteRuleParam;
/**
 * 
 * @类描述：路由规则上下文   
 * IRouteService 服务路由信息  IRouteRule 路由规则   IRouteResult 路由返回结果
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route
 * @类名称：IRouteContext
 * @创建人：longshen
 * @创建时间：2018年1月29日下午4:04:39
 * @修改人：longshen
 * @修改时间：2018年1月29日下午4:04:39
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IRouteContext<RS extends IRouteService<?>, PARAM extends IRouteRuleParam, RRS extends IRouteResult<?>>
		extends IAdapter {
	/**
	 * 
	 * @描述:获取存储器
	 * @方法名: getRepository
	 * @return
	 * @返回类型 IRouteRepository<PARAM,RRS,?>
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:06:56
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:06:56
	 * @修改备注
	 * @since
	 * @throws
	 */
	IRouteRepository<PARAM,RRS,?> getRepository();
	
	/**
	 * 
	 * @描述:获取规则工厂
	 * @方法名: getRuleFactory
	 * @return
	 * @返回类型 IRouteRuleFactory<?,PARAM,RRS>
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:07:53
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:07:53
	 * @修改备注
	 * @since
	 * @throws
	 */
	IRouteRuleFactory<?,PARAM,RRS> getRuleFactory();
	
	/**
	 * 
	 * @描述:获取服务工厂
	 * @方法名: getServiceFactory
	 * @return
	 * @返回类型 IRouteServiceFactory<RS>
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:10:02
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:10:02
	 * @修改备注
	 * @since
	 * @throws
	 */
	IRouteServiceFactory<RS> getServiceFactory();
	/**
	 * 
	 * @描述:根据路由参数获取路由结果
	 * @方法名: route
	 * @param param
	 * @return
	 * @返回类型 RS
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:10:53
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:10:53
	 * @修改备注
	 * @since
	 * @throws
	 */
	RS route(PARAM param);
	
	/**
	 * 
	 * @描述: 属性路由信息
	 * @方法名: refresh
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午4:11:28
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午4:11:28
	 * @修改备注
	 * @since
	 * @throws
	 */
	void refresh();

}
