package com.longshen.msgtw.route;

import java.util.List;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.rule.IRouteResult;
import com.longshen.msgtw.route.rule.IRouteRule;
import com.longshen.msgtw.route.rule.IRouteRuleParam;

/**
 * 
 * @类描述：存储器配置
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route
 * @类名称：IRouteRepository
 * @创建人：longshen
 * @创建时间：2018年1月29日下午3:45:28
 * @修改人：longshen
 * @修改时间：2018年1月29日下午3:45:28
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IRouteRepository <PARAM extends IRouteRuleParam,RRS extends IRouteResult<?>,RR extends IRouteRule<PARAM, RRS>>{

	/**
	 * 
	 * @描述:获取规则列表
	 * @方法名: getRouteRuleList
	 * @return
	 * @throws GtwException
	 * @返回类型 List<RR>
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:47:00
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:47:00
	 * @修改备注
	 * @since
	 * @throws
	 */
	List<RR> getRouteRuleList() throws GtwException;
	
	/**
	 * 
	 * @描述:刷新容器
	 * @方法名: reload
	 * @throws GtwException
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:46:42
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:46:42
	 * @修改备注
	 * @since
	 * @throws
	 */
	void reload() throws GtwException;
 }
