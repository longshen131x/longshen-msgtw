package com.longshen.msgtw.filter.support;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.IFilter;
import com.longshen.msgtw.filter.IFilterFactory;
import com.longshen.msgtw.filter.annotation.Filter;
import com.longshen.msgtw.filter.annotation.FilterEnable;
import com.longshen.msgtw.filter.annotation.FilterType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractFilterFactory<REQ, RES> implements IFilterFactory<REQ, RES> {

	/**
	 * 在线过滤器
	 */
	public Map<String, Map<String, IFilter<REQ, RES>>> serviceFilterOnlineMap = new LinkedHashMap<String, Map<String, IFilter<REQ, RES>>>();
	/**
	 * 离线过滤器
	 */
	public Map<String, Map<String, IFilter<REQ, RES>>> serviceFilterOfflineMap = new LinkedHashMap<String, Map<String, IFilter<REQ, RES>>>();

	/**
	 * 
	 * @描述:添加过滤器 @方法名: addFilter @param filter @throws
	 *           GtwException @创建人：longshen @创建时间：2018年1月29日上午11:22:01 @修改人：longshen @修改时间：2018年1月29日上午11:22:01 @修改备注： @throws
	 */
	@Override
	public void addFilter(IFilter<REQ, RES> filter) throws GtwException {
		FilterEnable filterEnable = filter.getClass().getAnnotation(FilterEnable.class);
		if (filterEnable != null && filterEnable.value()) {
			Filter filterAnnotation = filter.getClass().getAnnotation(Filter.class);
			if (filterAnnotation != null) {
				String filterId = filterAnnotation.id();
				if (filterId == null || filterId.length() < 1) {
					filterId = filter.getClass().getName();
				}
				FilterType[] filterTypes = filterAnnotation.value();
				for (FilterType filterType : filterTypes) {
					String code = filterType.getCode();

					Map<String, IFilter<REQ, RES>> filterMap = serviceFilterOnlineMap.get(code);
					if (filterMap == null) {
						filterMap = new LinkedHashMap<String, IFilter<REQ, RES>>();

					}
					filterMap.put(filterId, filter);
					serviceFilterOnlineMap.put(code, filterMap);
				}

			}
		} else {

			Filter filterAnnotation = filter.getClass().getAnnotation(Filter.class);
			if (filterAnnotation != null) {
				String filterId = filterAnnotation.id();
				if (filterId == null || filterId.length() < 1) {
					filterId = filter.getClass().getName();
				}
				FilterType[] filterTypes = filterAnnotation.value();
				for (FilterType filterType : filterTypes) {
					String code = filterType.getCode();

					Map<String, IFilter<REQ, RES>> filterMap = serviceFilterOfflineMap.get(code);
					if (filterMap == null) {
						filterMap = new LinkedHashMap<String, IFilter<REQ, RES>>();

					}
					filterMap.put(filterId, filter);
					serviceFilterOfflineMap.put(code, filterMap);
				}

			}
		}
	}

	@Override
	public void addFilters(List<IFilter<REQ, RES>> filters) throws GtwException {
		log.info("/--------------FilterFactory[{}] int[START]------------------------/",
				this.getClass().getSimpleName());
		for (IFilter<REQ, RES> iFilter : filters) {
			// 初始化成功后添加
			iFilter.init();
			log.info("----Filter[{}] int ----", iFilter.toString());
			this.addFilter(iFilter);
		}

		log.info("/--------------FilterFactory[{}] int[ENT]------------------------/", this.getClass().getSimpleName());

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getFilter(FilterType filterType, String id) throws GtwException {
		IFilter<REQ, RES> filter = null;
		if (!serviceFilterOnlineMap.isEmpty()) {
			Map<String, IFilter<REQ, RES>> filterMap = serviceFilterOnlineMap.get(filterType.getCode());
			if (filterMap != null && filterMap.isEmpty()) {
				filter = filterMap.get(id);
			}
		}
		if (filter == null) {

			if (!serviceFilterOnlineMap.isEmpty()) {
				Map<String, IFilter<REQ, RES>> filterMap = serviceFilterOfflineMap.get(filterType.getCode());
				if (filterMap != null && filterMap.isEmpty()) {
					filter = filterMap.get(id);
				}
			}
		}

		return (T) filter;
	}

	@Override
	public <T> T getFilter(Class<T> t) throws GtwException {

		Filter filterAnnotation = t.getAnnotation(Filter.class);
		if (filterAnnotation != null) {
			String filterId = filterAnnotation.id();
			if (filterId == null || filterId.length() < 1) {
				filterId = t.getName();
			}

			FilterType[] filterTypes = filterAnnotation.value();
			for (FilterType filterType : filterTypes) {
				T filter = this.getFilter(filterType, filterId);
				if (filter != null) {
					return filter;
				}
			}

		}
		return null;
	}

}
