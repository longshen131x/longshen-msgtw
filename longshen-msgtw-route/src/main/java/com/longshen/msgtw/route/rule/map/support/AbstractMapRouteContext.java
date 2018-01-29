package com.longshen.msgtw.route.rule.map.support;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.IRouteContext;
import com.longshen.msgtw.route.IRouteService;
import com.longshen.msgtw.route.rule.map.MapRouteResult;
import com.longshen.msgtw.route.rule.map.MapRouteRuleParam;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述：MAP 路由管理器
 * @项目名称：longshen-msgtw-route @包名： com.longshen.msgtw.route.rule.map.support
 * @类名称：AbstractMapRouteContext
 * @创建人：longshen
 * @创建时间：2018年1月29日下午10:06:48
 * @修改人：longshen
 * @修改时间：2018年1月29日下午10:06:48 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Slf4j
public abstract class AbstractMapRouteContext<RS extends IRouteService<?>>
		implements IRouteContext<RS, MapRouteRuleParam, MapRouteResult> {

	@Override
	public void init() throws GtwException {
		// 规则工厂初始化
		getRuleFactory().init();
	}

	@Override
	public void start() throws GtwException {
		getRuleFactory().start();
		getServiceFactory().start();
	}

	@Override
	public void shutdown() throws GtwException {
		getRuleFactory().shutdown();
		getServiceFactory().shutdown();
		;
	}

	@Override
	public RS route(MapRouteRuleParam param) {

		MapRouteResult result = getRuleFactory().match(param);
		if (result.isSuccess()) {
			List<String> serviceList = result.getRouteService();
			if (serviceList.size() > 1) {
				log.warn("--- 路由重复,param[{}],serviceList[{}],自动忽略获取第一条路由信息---",
						ToStringBuilder.reflectionToString(param, ToStringStyle.SHORT_PREFIX_STYLE),
						ToStringBuilder.reflectionToString(serviceList, ToStringStyle.SHORT_PREFIX_STYLE));
			}
			String serviceID = serviceList.get(0);
			return getServiceFactory().getServiceByID(serviceID);
		}
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

}
