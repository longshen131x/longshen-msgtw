package com.longshen.msgtw.route.rule.map;

import org.apache.commons.lang3.StringUtils;

import com.longshen.msgtw.route.rule.IRouteRuleParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapRouteRuleParam implements IRouteRuleParam {
	// 服务唯一标识
	private String serviceURL;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serviceURL == null) ? 0 : serviceURL.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MapRouteRuleParam) {
			String objServiceURL=((MapRouteRuleParam) obj).getServiceURL();
			if(StringUtils.isEmpty(objServiceURL)||StringUtils.isEmpty(serviceURL)) {
				return false;
			}else {
				return StringUtils.equals(objServiceURL, serviceURL);
			}
		}
		return false;
	}	
	
}
