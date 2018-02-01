package com.longshen.msgtw.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.longshen.msgtw.base.container.IContainer;
import com.longshen.msgtw.base.gateway.IGateway;
import com.longshen.msgtw.container.rest.rxnetty.RxNettyContainer;
import com.longshen.msgtw.entity.RestGatewayREQ;
import com.longshen.msgtw.entity.RestGatewayRES;
import com.longshen.msgtw.filter.IFilter;
import com.longshen.msgtw.filter.IFilterFactory;
import com.longshen.msgtw.filter.impl.FilterFactoryImp;
import com.longshen.msgtw.gateway.Gateway;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MSGatewayConfig {

	/**
	 * 
	 * @描述:定义执行的微服务 @方法名: msGateway @param filterFactory @param
	 *              container @return @返回类型
	 *              IGateway<RestGatewayREQ,RestGatewayRES> @创建人 longshen @创建时间
	 *              2018年2月1日下午3:51:04 @修改人 longshen @修改时间
	 *              2018年2月1日下午3:51:04 @修改备注 @since @throws
	 */
	@Bean("msGateway")
	public IGateway<RestGatewayREQ, RestGatewayRES> msGateway(
			IFilterFactory<RestGatewayREQ, RestGatewayRES> filterFactory,
			IContainer<RestGatewayREQ, RestGatewayRES> container) {

		Gateway<RestGatewayREQ, RestGatewayRES> gateway = new Gateway<RestGatewayREQ, RestGatewayRES>();
		log.info("***Gateway init***");
		gateway.setFilterFactory(filterFactory);
		gateway.setContainer(container);
		return gateway;
	}

	public IFilterFactory<RestGatewayREQ, RestGatewayRES> filterFactory() {
		List<IFilter<RestGatewayREQ, RestGatewayRES>> filterList = new ArrayList<IFilter<RestGatewayREQ, RestGatewayRES>>();
		// 添加过滤器 要顺序添加

		// 添加过滤器

		IFilterFactory<RestGatewayREQ, RestGatewayRES> filterFactory = new FilterFactoryImp<RestGatewayREQ, RestGatewayRES>();
		filterFactory.addFilters(filterList);
		filterFactory.init();
		return filterFactory;

	}

	public IContainer<RestGatewayREQ, RestGatewayRES> container(
			IFilterFactory<RestGatewayREQ, RestGatewayRES> filterFactory) {

		IContainer<RestGatewayREQ, RestGatewayRES> container = new RxNettyContainer<RestGatewayREQ, RestGatewayRES>(
				filterFactory) {

					@Override
					protected RestGatewayREQ newREQ() {
						return new RestGatewayREQ();
					}

					@Override
					protected RestGatewayRES newRES() {
						return new RestGatewayRES();
					}
			
		};

		return container;
	}

}
