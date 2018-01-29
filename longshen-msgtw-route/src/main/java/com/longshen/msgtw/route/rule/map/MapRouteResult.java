package com.longshen.msgtw.route.rule.map;

import java.util.List;

import com.longshen.msgtw.route.rule.IRouteResult;
/**
 * 
 * @类描述：MAP规则匹配返回结果
 * @项目名称：longshen-msgtw-route
 * @包名： com.longshen.msgtw.route.rule.map
 * @类名称：MapRouteResult
 * @创建人：longshen
 * @创建时间：2018年1月29日下午4:18:55
 * @修改人：longshen
 * @修改时间：2018年1月29日下午4:18:55
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public class MapRouteResult implements IRouteResult<String> {
	private boolean successFlag;

	private List<String> services;

	public MapRouteResult(boolean successFlag, List<String> services) {
		this.successFlag = successFlag;
		this.services = services;
	}

	@Override
	public boolean isSuccess() {
		return successFlag;
	}

	@Override
	public List<String> getRouteService() {
		return services;
	}

}
