package com.longshen.msgtw.route.dubbo;

import javax.annotation.Resource;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.IRouteRepository;
import com.longshen.msgtw.route.IRouteServiceFactory;
import com.longshen.msgtw.route.rule.IRouteRuleFactory;
import com.longshen.msgtw.route.rule.map.MapRouteResult;
import com.longshen.msgtw.route.rule.map.MapRouteRuleFactory;
import com.longshen.msgtw.route.rule.map.MapRouteRuleParam;
import com.longshen.msgtw.route.rule.map.support.AbstractMapRouteContext;
import com.longshen.msgtw.route.rule.map.support.AbstractMapRouteRepository;

public class DubboMapRouteContext extends AbstractMapRouteContext<DubboRouteService> {

	@Resource(name = "mapRouteRepository")
	private AbstractMapRouteRepository repostitory;

	@Resource
	private DubboRouteServiceFactory serviceFactory;

	@Resource
	private MapRouteRuleFactory ruleFactory;

	@Override
	public IRouteRepository<MapRouteRuleParam, MapRouteResult, ?> getRepository() {
		return repostitory;
	}

	@Override
	public IRouteRuleFactory<?, MapRouteRuleParam, MapRouteResult> getRuleFactory() {
		// TODO Auto-generated method stub
		return ruleFactory;
	}

	@Override
	public IRouteServiceFactory<DubboRouteService> getServiceFactory() {
		// TODO Auto-generated method stub
		return serviceFactory;
	}

	@Override
	public void init() throws GtwException {
		ruleFactory.setRepository(repostitory);
		super.init();
	}

	@Override
	public void refresh() {
    super.refresh();
	}

}
