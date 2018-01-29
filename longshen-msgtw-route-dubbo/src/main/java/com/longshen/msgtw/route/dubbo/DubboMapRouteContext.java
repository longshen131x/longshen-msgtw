package com.longshen.msgtw.route.dubbo;

import com.longshen.msgtw.route.IRouteRepository;
import com.longshen.msgtw.route.IRouteServiceFactory;
import com.longshen.msgtw.route.rule.IRouteRuleFactory;
import com.longshen.msgtw.route.rule.map.MapRouteResult;
import com.longshen.msgtw.route.rule.map.MapRouteRuleParam;
import com.longshen.msgtw.route.rule.map.support.AbstractMapRouteContext;

public class DubboMapRouteContext extends AbstractMapRouteContext<DubboRouteService>{

	@Override
	public IRouteRepository<MapRouteRuleParam, MapRouteResult, ?> getRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRouteRuleFactory<?, MapRouteRuleParam, MapRouteResult> getRuleFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRouteServiceFactory<DubboRouteService> getServiceFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
