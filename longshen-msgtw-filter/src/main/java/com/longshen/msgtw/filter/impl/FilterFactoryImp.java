package com.longshen.msgtw.filter.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.annotation.FilterType;
import com.longshen.msgtw.filter.support.AbstractFilterFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterFactoryImp<REQ, RES> extends AbstractFilterFactory<REQ, RES> {

	private ConcurrentHashMap<FilterType, FilterChain<REQ, RES>> filterChains;

	public FilterFactoryImp() {
	}

	@Override
	public RES doFilter(REQ req, Object... args) throws GtwException {
		RES res = null;
		try {
			// PRE 过滤器过滤
			try {
				res = filterChains.get(FilterType.PRE).doFilterChain(req, res, args);

			} catch (Exception e) {
				log.error("The execute [{}]  filter is error:[{}]",
						new String[] { FilterType.PRE.getCode(), e.getMessage() }, e);
				throw e;
			}

			// ROUTE 服务调用过滤器
			try {
				res = filterChains.get(FilterType.ROUTE).doFilterChain(req, res, args);

			} catch (Exception e) {
				log.error("The execute [{}]  filter is error:[{}]",
						new String[] { FilterType.ROUTE.getCode(), e.getMessage() }, e);
				throw e;
			}

		} catch (Exception e) {
			res = filterChains.get(FilterType.ERROR).doFilterChain(req, res, args);
		} finally {
			try {

				res = filterChains.get(FilterType.POST).doFilterChain(req, res, args);
			} catch (Exception e2) {
				log.error("The execute [{}]  filter is error:[{}]",
						new String[] { FilterType.POST.getCode(), e2.getMessage() }, e2);
				res = filterChains.get(FilterType.ERROR).doFilterChain(req, res, args);
			}
		}

		return res;
	}

	/**
	 * 
	 * @描述:初始化执行工厂 @方法名: init @throws
	 *             GtwException @创建人：longshen @创建时间：2018年1月29日下午2:11:16 @修改人：longshen @修改时间：2018年1月29日下午2:11:16 @修改备注： @throws
	 */
	@Override
	public void init() throws GtwException {
		filterChains = new ConcurrentHashMap<FilterType, FilterChain<REQ, RES>>();
		// 求转发前执行的过滤器链
		FilterChain<REQ, RES> preFilterChain = new FilterChain<REQ, RES>(FilterType.PRE,
				serviceFilterOnlineMap.get(FilterType.PRE.getCode()), FilterChainOrder.NEXT);
		// 服务调用过滤器
		FilterChain<REQ, RES> routeFilterChain = new FilterChain<REQ, RES>(FilterType.ROUTE,
				serviceFilterOnlineMap.get(FilterType.ROUTE.getCode()), FilterChainOrder.NEXT);
		// 服务返回处理过滤器
		FilterChain<REQ, RES> postFilterChain = new FilterChain<REQ, RES>(FilterType.POST,
				serviceFilterOnlineMap.get(FilterType.POST.getCode()), FilterChainOrder.PREVIOUD);
		// 异常过滤器
		FilterChain<REQ, RES> errorFilterChain = new FilterChain<REQ, RES>(FilterType.ERROR,
				serviceFilterOnlineMap.get(FilterType.ERROR.getCode()), FilterChainOrder.NEXT);

		filterChains.put(FilterType.PRE, preFilterChain);
		filterChains.put(FilterType.ROUTE, routeFilterChain);
		filterChains.put(FilterType.POST, postFilterChain);
		filterChains.put(FilterType.ERROR, errorFilterChain);

	}

	@Override
	public void start() throws GtwException {
		// do nothing

	}

	@Override
	public void shutdown() throws GtwException {
		// do nothing

	}

}
