package com.longshen.msgtw.config.route;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.longshen.msgtw.common.utils.FileUtils;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.rule.map.MapRouteRule;
import com.longshen.msgtw.route.rule.map.MapRouteRuleParam;
import com.longshen.msgtw.route.rule.map.support.AbstractMapRouteRepository;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service("mapRouteRepository") // DubboMapRouteContext 中使用
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@EqualsAndHashCode(callSuper = false)
@DisconfFile(filename = StaticRouteRuleRepository.FILENAME)
public class StaticRouteRuleRepository extends AbstractMapRouteRepository {

	public final static String FILENAME = "routefilter_static.json";

	private StaticRouteRuleInfo staticRouteRuleInfo;

	@PostConstruct
	public void loadConfig() throws IOException {
		log.info("--------------- LOAD ROUTE STATIC CONDIG START---------");
		String jsonStr = FileUtils.readFileToString(FILENAME, Charset.forName("UTF-8"));
		StaticRouteRuleInfo nStaticRouteRuleInfo = JSON.parseObject(jsonStr, StaticRouteRuleInfo.class);
		staticRouteRuleInfo = nStaticRouteRuleInfo;
		log.info("静态路由信息一共配置[{}]项", staticRouteRuleInfo.getRouteMap().size());
		log.info("--------------- LOAD ROUTE STATIC CONDIG END  ---------");

	}

	@Override
	public List<MapRouteRule> getRouteRuleList() throws GtwException {
		List<MapRouteRule> ruleList = new ArrayList<MapRouteRule>();
		Iterator<String> msgTypeIt = staticRouteRuleInfo.getRouteMap().keySet().iterator();
		while (msgTypeIt.hasNext()) {
			String msgType = msgTypeIt.next();
			String serviceId = staticRouteRuleInfo.getServiceId(msgType);
			MapRouteRuleParam param = new MapRouteRuleParam();
			param.setServiceURL(msgType);
			MapRouteRule routeRule = new MapRouteRule(param, serviceId);
			ruleList.add(routeRule);

		}

		return ruleList;
	}

	@Override
	public void reload() throws GtwException {

		try {
			loadConfig();
		} catch (IOException e) {
			throw new GtwException("999999", "静态路由加载异常", e);
		}
	}

}
