package com.longshen.msgtw.route.rule;

import java.util.List;

import com.longshen.msgtw.base.adapter.IAdapter;
/**
 * 
 * @类描述：路由规则工厂
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route.rule
 * @类名称：IRouteRuleFactory
 * @创建人：longshen
 * @创建时间：2018年1月29日下午3:41:58
 * @修改人：longshen
 * @修改时间：2018年1月29日下午3:41:58
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
import com.longshen.msgtw.route.IRouteRepository;
public interface IRouteRuleFactory<R extends IRouteRule<PARAM, RRS>, PARAM extends IRouteRuleParam, RRS extends IRouteResult<?>>
		extends IAdapter {
	
	IRouteRepository<PARAM, RRS, R> getRepository();
	
	void setRepository(IRouteRepository<PARAM, RRS, R> repository);
	
	void addRule(R rule);
	
	void addRules(List<R> ruleList);
	
	R getRule(String ruleId);
	
	List<R> getRules();
	
	RRS match(PARAM param);
	
	void refresh();
	

}
