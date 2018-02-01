package com.longshen.msgtw.boot;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@SpringBootApplication
public class ApplicationBoot{
	
	@PostConstruct
	public void init() {
		log.info("** [{}]  strat ",getComponentName());
	}
	@PreDestroy
	public void destroy() {
		log.info("** [{}]  stop ",getComponentName());

	}
	
	@PreDestroy
	public String getComponentName() {
		return "dubbo  微服务网关";
	}
	
 
}
