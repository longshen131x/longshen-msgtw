package com.longshen.msgtw.filter;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.annotation.FilterType;

/**
 * 
 * @类描述：网关过滤器
 * @项目名称：longshen-msgtw-filter @包名： com.longshen.msgtw.filter
 * @类名称：IFilter
 * @创建人：longshen
 * @创建时间：2018年1月26日下午3:01:29
 * @修改人：longshen
 * @修改时间：2018年1月26日下午3:01:29 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
public interface IFilter<REQ, RES> {
	/**
	 * 
	 * @描述:过滤器初始化 @方法名: init @throws GtwException @返回类型 void @创建人 longshen @创建时间
	 * 2018年1月26日下午3:01:48 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:01:48 @修改备注 @since @throws
	 */
	void init() throws GtwException;

	/**
	 * 
	 * @描述:过滤器刷新 @方法名: refresh @throws GtwException @返回类型 void @创建人 longshen @创建时间
	 * 2018年1月26日下午3:02:07 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:02:07 @修改备注 @since @throws
	 */
	void refresh() throws GtwException;

	/**
	 * 
	 * @描述:注入自定义对象 @方法名: mod @param mod @throws GtwException @返回类型 void @创建人
	 * longshen @创建时间 2018年1月26日下午3:02:25 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:02:25 @修改备注 @since @throws
	 */
	<MOD> void mod(MOD mod) throws GtwException;

	/**
	 * 
	 * @描述:过滤器执行前检查 @方法名: check @param filterType @param req @param res @param
	 * args @return @throws GtwException @返回类型 boolean @创建人 longshen @创建时间
	 * 2018年1月26日下午3:02:42 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:02:42 @修改备注 @since @throws
	 */
	boolean check(FilterType filterType, REQ req, RES res, Object... args) throws GtwException;

	/**
	 * 
	 * @描述:执行过滤器 @方法名: run @param filterType @param req @param res @param
	 * args @return @throws GtwException @返回类型 RES @创建人 longshen @创建时间
	 * 2018年1月26日下午3:03:08 @修改人 longshen @修改时间
	 * 2018年1月26日下午3:03:08 @修改备注 @since @throws
	 */
	RES run(FilterType filterType, REQ req, RES res, Object... args) throws GtwException;
}
