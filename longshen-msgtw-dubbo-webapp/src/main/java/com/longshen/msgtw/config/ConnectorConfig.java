package com.longshen.msgtw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.longshen.msgtw.config.route.DubboRouteServiceRepository;
import com.longshen.msgtw.route.dubbo.DubboMapRouteContext;
import com.longshen.msgtw.route.dubbo.DubboRouteServiceFactory;
import com.longshen.msgtw.route.rule.map.MapRouteRuleFactory;

/**
 * 
 * @类描述：dubbo连接器相关配置
 * @项目名称：longshen-msgtw-dubbo-webapp
 * @包名： com.longshen.msgtw.config
 * @类名称：ConnectorConfig
 * @创建人：longshen
 * @创建时间：2018年1月31日下午5:04:46
 * @修改人：longshen
 * @修改时间：2018年1月31日下午5:04:46
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
@Configuration
public class ConnectorConfig {
	
	@Bean
	public DubboMapRouteContext dubboMapRouteContext() {
		return new DubboMapRouteContext();
	}
	
	@Bean
	public MapRouteRuleFactory mapRouteRuleFactory() {
		return new MapRouteRuleFactory();
	}
	@Bean
	public DubboRouteServiceFactory dubboRouteServiceFactory(DubboRouteServiceRepository repository,ApplicationConfig applicationConfig,RegistryConfig registryConfig,ConsumerConfig consumerConfig) {
		DubboRouteServiceFactory factory=new DubboRouteServiceFactory();
		factory.setApplicationConfig(applicationConfig);
		factory.setRegistryConfig(registryConfig);
		factory.setConsumerConfig(consumerConfig);
		factory.setRepository(repository);
		return factory;
	}

}
