package com.longshen.msgtw.filter.impl;

/**
 * 
 * @类描述：适配器执行顺序
 * @项目名称：longshen-msgtw-filter @包名： com.longshen.msgtw.filter.impl
 * @类名称：FilterChainOrder
 * @创建人：longshen
 * @创建时间：2018年1月29日下午12:34:26
 * @修改人：longshen
 * @修改时间：2018年1月29日下午12:34:26 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
public enum FilterChainOrder {
	// 先入先出顺序执行
	NEXT,
	// 先入后出顺序执行
	PREVIOUD;
}
