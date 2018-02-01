package com.longshen.msgtw.config.disconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
/**
 * 
 * @类描述：分布式配置文件接入初始化
 * @项目名称：longshen-msgtw-dubbo-webapp
 * @包名： com.longshen.msgtw.config.disconfig
 * @类名称：DisConfConfig
 * @创建人：longshen
 * @创建时间：2018年2月1日上午10:07:47
 * @修改人：longshen
 * @修改时间：2018年2月1日上午10:07:47
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;

@Configuration
@EnableAspectJAutoProxy
public class DisConfConfig {
	@Bean(destroyMethod = "destroy")
	public DisconfMgrBean disconfMgrBean() {
		DisconfMgrBean disconf = new DisconfMgrBean();
		disconf.setScanPackage("com.longshen.msgtw.config.disconfig,com.longshen.msgtw.config");
		return disconf;
	}

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public DisconfMgrBeanSecond disconfMgrBeanSecond() {
		return new DisconfMgrBeanSecond();
	}

}
