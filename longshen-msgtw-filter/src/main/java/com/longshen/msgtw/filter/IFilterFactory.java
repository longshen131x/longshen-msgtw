package com.longshen.msgtw.filter;

import java.util.List;

import com.longshen.msgtw.base.adapter.IAdapter;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.annotation.FilterType;

public interface IFilterFactory<REQ, RES> extends IAdapter {
	/**
	 * 
	 * @描述:添加过滤器 @方法名: addFilters @param filter @throws GtwException @返回类型 void @创建人
	 * longshen @创建时间 2018年1月26日下午3:11:22 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:11:22 @修改备注 @since @throws
	 */
	void addFilter(IFilter<REQ, RES> filter) throws GtwException;

	/**
	 * 
	 * @描述:添加一组过滤器 @方法名: addFilters @param filters @throws GtwException @返回类型
	 * void @创建人 longshen @创建时间 2018年1月26日下午3:12:55 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:12:55 @修改备注 @since @throws
	 */
	void addFilters(List<IFilter<REQ, RES>> filters) throws GtwException;

	/**
	 * 
	 * @描述:获取指定过滤器 @方法名: getFilter @param t @return @throws GtwException @返回类型
	 * T @创建人 longshen @创建时间 2018年1月26日下午3:17:51 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:17:51 @修改备注 @since @throws
	 */
	<T> T getFilter(Class<T> t) throws GtwException;

	/**
	 * 
	 * @描述:获取指定过滤器 @方法名: getFilter @param filterType @param id @return @throws
	 * GtwException @返回类型 T @创建人 longshen @创建时间 2018年1月26日下午3:18:04 @修改人
	 * longshen @修改时间 2018年1月26日下午3:18:04 @修改备注 @since @throws
	 */
	<T> T getFilter(FilterType filterType, String id) throws GtwException;

	/**
	 * 
	 * @描述:过滤器执行入口 @方法名: doFilter @param req @param args @return @throws
	 * GtwException @返回类型 RES @创建人 longshen @创建时间 2018年1月26日下午3:19:11 @修改人
	 * longshen @修改时间 2018年1月26日下午3:19:11 @修改备注 @since @throws
	 */
	RES doFilter(REQ req, Object... args) throws GtwException;
}
