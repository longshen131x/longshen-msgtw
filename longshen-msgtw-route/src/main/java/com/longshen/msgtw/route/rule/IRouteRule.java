package com.longshen.msgtw.route.rule;
/**
 * 
 * @类描述：路由规则
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route.rule
 * @类名称：IRouteRule
 * @创建人：longshen
 * @创建时间：2018年1月29日下午3:33:28
 * @修改人：longshen
 * @修改时间：2018年1月29日下午3:33:28
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IRouteRule<PARAM extends IRouteRuleParam,RRS extends IRouteResult<?>>{
/**
 * 
 * @描述:获取规则名称  保证唯一
 * @方法名: getRuleName
 * @return
 * @返回类型 String
 * @创建人 longshen
 * @创建时间 2018年1月29日下午3:36:43
 * @修改人 longshen
 * @修改时间 2018年1月29日下午3:36:43
 * @修改备注
 * @since
 * @throws
 */
	String getRuleName();
	/**
	 * 
	 * @描述:条件匹配规则
	 * @方法名: condition
	 * @param param
	 * @return
	 * @返回类型 boolean
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:37:25
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:37:25
	 * @修改备注
	 * @since
	 * @throws
	 */
	boolean condition(PARAM param);
	/**
	 * 
	 * @描述:条件匹配后动作
	 * @方法名: action
	 * @param param
	 * @return
	 * @返回类型 RRS
	 * @创建人 longshen
	 * @创建时间 2018年1月29日下午3:38:17
	 * @修改人 longshen
	 * @修改时间 2018年1月29日下午3:38:17
	 * @修改备注
	 * @since
	 * @throws
	 */
	RRS action(PARAM param);
}
