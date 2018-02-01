package com.longshen.msgtw.gateway;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.longshen.msgtw.base.container.IContainer;
import com.longshen.msgtw.base.gateway.IGateway;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.IFilterFactory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述：微服务网关
 * @项目名称：longshen-msgtw-dubbo-webapp @包名： com.longshen.msgtw.gateway
 * @类名称：Gateway
 * @创建人：longshen
 * @创建时间：2018年2月1日下午2:55:39
 * @修改人：longshen
 * @修改时间：2018年2月1日下午2:55:39 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Data
@Slf4j(topic = "boot")
public class Gateway<REQ, RES> implements IGateway<REQ, RES> {

	/**
	 * 过滤器工厂
	 */
	protected IFilterFactory<REQ, RES> filterFactory;

	/**
	 * 连接器工厂
	 */
	protected IContainer<REQ, RES> container;

	/**
	 * 
	 * @描述:启动微服务网关 @方法名: postConstruct @返回类型 void @创建人 longshen @创建时间
	 *             2018年2月1日下午3:01:16 @修改人 longshen @修改时间
	 *             2018年2月1日下午3:01:16 @修改备注 @since @throws
	 */
	@PostConstruct
	public void postConstruct() {
		try {
			init();
			start();
		} catch (Exception e) {
			log.error("微服务网关启动异常，异常信息为:{}", e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @描述:销毁微服务网关 @方法名: preDestroy @返回类型 void @创建人 longshen @创建时间
	 *             2018年2月1日下午3:01:46 @修改人 longshen @修改时间
	 *             2018年2月1日下午3:01:46 @修改备注 @since @throws
	 */
	@PreDestroy
	public void preDestroy() {
		shutdown();
	}

	@Override
	public void init() throws GtwException {
		filterFactory.init();
		container.init();
	}

	@Override
	public void start() throws GtwException {

		filterFactory.start();
		container.start();
	}

	@Override
	public void shutdown() throws GtwException {

		filterFactory.start();
		container.start();
	}

	@Override
	public void inject(IContainer<REQ, RES> container, Object... args) throws GtwException {
		this.container = container;
	}

}
