package com.longshen.msgtw.route.rule.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.IRouteRepository;
import com.longshen.msgtw.route.rule.IRouteRuleFactory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述：基于map的路由工厂类
 * @项目名称：longshen-msgtw-route @包名： com.longshen.msgtw.route.rule.map
 * @类名称：MapRouteRuleFactory
 * @创建人：longshen
 * @创建时间：2018年1月29日下午9:15:22
 * @修改人：longshen
 * @修改时间：2018年1月29日下午9:15:22 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Slf4j
public class MapRouteRuleFactory implements IRouteRuleFactory<MapRouteRule, MapRouteRuleParam, MapRouteResult> {

	private ConcurrentHashMap<String, MapRouteRule> routeRuleMap = new ConcurrentHashMap<String, MapRouteRule>();

	private IRouteRepository<MapRouteRuleParam, MapRouteResult, MapRouteRule> repository;

	@Override
	public void init() throws GtwException {

		if (repository != null) {
			List<MapRouteRule> ruleList = repository.getRouteRuleList();
			Iterator<MapRouteRule> it = ruleList.iterator();
			while (it.hasNext()) {
				addRule(it.next());
			}
		} else {
			log.error("--MapRouteRepository is null,{} initialize fail", this.getClass().getSimpleName());
		}

		printServiceInfo(routeRuleMap);
	}

	@Override
	public void start() throws GtwException {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() throws GtwException {
		// TODO Auto-generated method stub

	}

	@Override
	public IRouteRepository<MapRouteRuleParam, MapRouteResult, MapRouteRule> getRepository() {
		return repository;
	}

	@Override
	public void setRepository(IRouteRepository<MapRouteRuleParam, MapRouteResult, MapRouteRule> repository) {
		this.repository = repository;
	}

	@Override
	public void addRule(MapRouteRule rule) {
		if (rule == null) {
			throw new IllegalStateException("rule counld not null");
		}
		String ruleId = rule.getRuleName();
		if (routeRuleMap.containsKey(ruleId)) {
			log.warn("---替换规则{}---", ruleId);
			routeRuleMap.put(ruleId, rule);
		} else {
			routeRuleMap.put(ruleId, rule);
		}
	}

	@Override
	public void addRules(List<MapRouteRule> ruleList) {
		if (ruleList == null)
			throw new IllegalStateException("ruleList could not null");
		Iterator<MapRouteRule> it = ruleList.iterator();
		while (it.hasNext()) {
			addRule(it.next());
		}
	}

	@Override
	public MapRouteRule getRule(String ruleId) {
		if (ruleId == null)
			throw new IllegalStateException("ruleId could not null");
		if (routeRuleMap.containsKey(ruleId)) {
			return routeRuleMap.get(ruleId);
		} else {
			return null;
		}
	}

	@Override
	public List<MapRouteRule> getRules() {
		List<MapRouteRule> ruleList = new ArrayList<MapRouteRule>();
		ruleList.addAll(routeRuleMap.values());

		return ruleList;
	}

	@Override
	public MapRouteResult match(MapRouteRuleParam param) {
		if (param == null)
			throw new IllegalStateException("MapRouteRuleParam param could not null");
		if (routeRuleMap.containsKey(MapRouteRule.buildID(param))) {
			MapRouteRule rule = routeRuleMap.get(MapRouteRule.buildID(param));
			if (rule.condition(param)) {
				return rule.action(param);
			}
		}
		// 匹配不到返回失败
		MapRouteResult result = new MapRouteResult(false, null);
		return result;

	}

	@Override
	public void refresh() {

		repository.reload();

		ConcurrentHashMap<String, MapRouteRule> newRouteRuleMap = new ConcurrentHashMap<String, MapRouteRule>();
		List<MapRouteRule> reuleList = repository.getRouteRuleList();
		Iterator<MapRouteRule> it = reuleList.iterator();
		while (it.hasNext()) {
			MapRouteRule rule = it.next();
			newRouteRuleMap.put(rule.getRuleName(), rule);
			printServiceInfo(newRouteRuleMap);
			routeRuleMap = newRouteRuleMap;

		}
	}

	private void printServiceInfo(ConcurrentHashMap<String, MapRouteRule> routeRuleMap) {
		Iterator<String> it = routeRuleMap.keySet().iterator();
		while (it.hasNext()) {
			String routeId = it.next();
			MapRouteRule routeRule = routeRuleMap.get(routeId);
			log.info("---Static Route INfo [serviceURL[{}],services{}]---", routeRule.getRuleParam().getServiceURL(),
					routeRule.getServices());
		}
	}

}
