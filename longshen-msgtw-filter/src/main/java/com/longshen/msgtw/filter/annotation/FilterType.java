package com.longshen.msgtw.filter.annotation;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @类描述：过滤器类型定义
 * @项目名称：longshen-msgtw-filter @包名： com.longshen.msgtw.filter.annotation
 * @类名称：FilterType
 * @创建人：longshen
 * @创建时间：2018年1月26日下午2:48:13
 * @修改人：longshen
 * @修改时间：2018年1月26日下午2:48:13 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
public enum FilterType {

	PRE("PRE", "请求转发前执行的过滤器"),
	ROUTE("ROUTE", "请求转发过滤器"),
	POST("POST", "请求响应前执行的过滤器"),
	ERROR("ERROR", "异常处理过滤器");
	@Getter
	@Setter
	String code;
	@Getter
	@Setter
	String msg;

	private FilterType(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
