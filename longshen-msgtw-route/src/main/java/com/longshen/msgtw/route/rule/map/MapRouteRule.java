package com.longshen.msgtw.route.rule.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.longshen.msgtw.route.rule.IRouteRule;

import lombok.Data;

/**
 * 
 * @类描述：映射贵些路由，使用服务标识与后台服务映射
 * @项目名称：longshen-msgtw-route @包名： com.longshen.msgtw.route.rule.map
 * @类名称：MapRouteRule
 * @创建人：longshen
 * @创建时间：2018年1月29日下午4:30:43
 * @修改人：longshen
 * @修改时间：2018年1月29日下午4:30:43 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Data
public class MapRouteRule implements IRouteRule<MapRouteRuleParam, MapRouteResult> {

	public static final String SEQ = "&";
	public static final String NAME = MapRouteRule.class.getSimpleName();

	private MapRouteRuleParam ruleParam;

	private List<String> services = new ArrayList<String>();

	public static String buildID(MapRouteRuleParam ruleParam) {
		return NAME + SEQ + ruleParam.getServiceURL();
	}

	/**
	 * 
	 * Title:构建规则 Description:
	 * 
	 * @param ruleParam
	 *            规则参数
	 * @param service
	 *            名字规则相关服务信息
	 */
	public MapRouteRule(MapRouteRuleParam ruleParam, String... service) {

		this.ruleParam = ruleParam;
		if (!ArrayUtils.isEmpty(service)) {
			this.services.addAll(Arrays.asList(service));
		}
	}

	/**
	 * 
	 * @描述:增加服务 @方法名: addService @param service @返回类型 void @创建人 longshen @创建时间
	 * 2018年1月29日下午4:41:22 @修改人 longshen @修改时间
	 * 2018年1月29日下午4:41:22 @修改备注 @since @throws
	 */
	public void addService(String service) {
		Iterator<String> it = services.iterator();
		while (it.hasNext()) {
			if (it.next().equals(service))
				return;
		}
		services.add(service);
	}

	/**
	 * 
	 * @描述: @方法名: getRuleName @return
	 * MapRouteRule&serviceURL @创建人：longshen @创建时间：2018年1月29日下午4:46:01 @修改人：longshen @修改时间：2018年1月29日下午4:46:01 @修改备注： @throws
	 */
	@Override
	public String getRuleName() {
		return MapRouteRule.buildID(ruleParam);
	}

	@Override
	public boolean condition(MapRouteRuleParam param) {
		if (ruleParam != null & param != null) {
			return ruleParam.equals(param);
		}
		return false;
	}

	@Override
	public MapRouteResult action(MapRouteRuleParam param) {
		MapRouteResult result = new MapRouteResult(true, services);
		return result;
	}

}
