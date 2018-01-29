package com.longshen.msgtw.filter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.IFilter;
import com.longshen.msgtw.filter.annotation.FilterType;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述：过滤器调用链
 * @项目名称：longshen-msgtw-filter @包名： com.longshen.msgtw.filter.impl
 * @类名称：FilterChain
 * @创建人：longshen
 * @创建时间：2018年1月29日下午12:35:37
 * @修改人：longshen
 * @修改时间：2018年1月29日下午12:35:37 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Slf4j
public class FilterChain<REQ, RES> {

	private Map<String, IFilter<REQ, RES>> filters;

	private FilterChainOrder order;

	private FilterType filterType;

	private List<Map.Entry<String, IFilter<REQ, RES>>> filterList;

	public FilterChain(FilterType filterType, Map<String, IFilter<REQ, RES>> filters) {
		this(filterType, filters, FilterChainOrder.NEXT);
	}

	public FilterChain(FilterType filterType, Map<String, IFilter<REQ, RES>> filters, FilterChainOrder order) {
		this.filterType = filterType;
		this.filters = filters;
		this.order = order;

		if (this.order == null)
			this.order = FilterChainOrder.NEXT;

		if (this.filters != null) {
			filterList = new ArrayList<Map.Entry<String, IFilter<REQ, RES>>>(this.filters.entrySet());
		}

	}

	/**
	 * 
	 * @描述:执行过滤器链 @方法名: doFilterChain @param req @param res @param
	 * args @return @throws GtwException @返回类型 RES @创建人 longshen @创建时间
	 * 2018年1月29日下午1:42:22 @修改人 longshen @修改时间
	 * 2018年1月29日下午1:42:22 @修改备注 @since @throws
	 */
	public RES doFilterChain(REQ req, RES res, Object... args) throws GtwException {
		RES returnRES = res;
		if (filterList != null && filterList.size() > 0) {
			ListIterator<Map.Entry<String, IFilter<REQ, RES>>> listIterator = null;
			if (FilterChainOrder.PREVIOUD.equals(order)) {
				listIterator = filterList.listIterator(filterList.size());
				while (listIterator.hasPrevious()) {
					Map.Entry<String, IFilter<REQ, RES>> entry = listIterator.previous();
					IFilter<REQ, RES> filter = entry.getValue();
					RES resTemp = doHander(filter, filterType, req, res, args);
					if (resTemp != null) { //责任链模式 过滤器上有返回 整条链执行结束
						returnRES = resTemp;
					}

				}

			}
			if (FilterChainOrder.NEXT.equals(order)) {
				listIterator = filterList.listIterator(filterList.size());
				while (listIterator.hasNext()) {
					Map.Entry<String, IFilter<REQ, RES>> entry = listIterator.next();
					IFilter<REQ, RES> filter = entry.getValue();
					RES resTemp = doHander(filter, filterType, req, res, args);
					if (resTemp != null) { //责任链模式 过滤器上有返回 整条链执行结束
						returnRES = resTemp;
					}

				}

			}
		}
	 return returnRES;
	}

	/**
	 * 
	 * @描述:单步执行过滤器 @方法名: doHander @param filter @param filterType @param req @param
	 * res @param args @return @throws GtwException @返回类型 RES @创建人 longshen @创建时间
	 * 2018年1月29日下午1:53:25 @修改人 longshen @修改时间
	 * 2018年1月29日下午1:53:25 @修改备注 @since @throws
	 */
	public RES doHander(IFilter<REQ, RES> filter, FilterType filterType, REQ req, RES res, Object... args)
			throws GtwException {
		// 过滤器执行校验
		boolean chechResult = filter.check(filterType, req, res, args);
		if (chechResult) {
			log.debug("executer filter [{}][{}]", filterType.getCode(), filter.getClass().getName());
			RES resTemp = filter.run(filterType, req, res, args);
			return resTemp;
		} else {
			// 检测未通过跳过过滤器
			return null;
		}

	}

}
