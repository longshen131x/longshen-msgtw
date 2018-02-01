package com.longshen.msgtw.route.dubbo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.IRouteServiceFactory;
import com.longshen.msgtw.route.IRouteServiceRespository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @类描述：dubbo 服务工程类
 * @项目名称：longshen-msgtw-route-dubbo @包名： com.longshen.msgtw.route.dubbo
 * @类名称：DubboRouteServiceFactory
 * @创建人：longshen
 * @创建时间：2018年1月30日上午8:41:02
 * @修改人：longshen
 * @修改时间：2018年1月30日上午8:41:02 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Data
@Slf4j
public class DubboRouteServiceFactory implements IRouteServiceFactory<DubboRouteService> {

	/**
	 * 存储所以网关服务 一个交易一条服务记录
	 */
	private ConcurrentHashMap<String, DubboRouteService> serviceMap = new ConcurrentHashMap<String, DubboRouteService>();
	/**
	 * 存储dubbo泛化服务
	 */
	private ConcurrentHashMap<String, GenericService> gsMap = new ConcurrentHashMap<String, GenericService>();

	private IRouteServiceRespository<DubboRouteService> repository;

	// dubbo 相关注入

	private ApplicationConfig applicationConfig;
	private RegistryConfig registryConfig;
	private ConsumerConfig consumerConfig;

	@Override
	@PostConstruct
	public void init() throws GtwException {
		List<DubboRouteService> serviceList = repository.getRouteServiceList();
		Iterator<DubboRouteService> it = serviceList.iterator();
		while (it.hasNext()) {
			// 遍历初始化服务
			addRouterService(it.next());
		}
		// 打印服务列表
		printServiceInfo(this.serviceMap);
	}

	private void printServiceInfo(Map<String, DubboRouteService> map) {

	}

	@Override
	public void start() throws GtwException {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() throws GtwException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRouterService(DubboRouteService routeService) {
		if (routeService == null)
			throw new IllegalStateException("route service could not null");
		if (serviceMap.containsKey(routeService.getServiceID())) {
			log.warn("----替换服务{}----", routeService.getServiceID());
		}
		GenericService gs = buildGenericService(routeService);
		routeService.setGenericService(gs);
		serviceMap.put(routeService.getDubboServiceID(), routeService);

	}

	/**
	 * 
	 * @描述:构建dubbo服务请求者 @方法名: buildGenericService @param routeService @return @返回类型
	 *                  GenericService @创建人 longshen @创建时间 2018年1月30日下午10:51:12 @修改人
	 *                  longshen @修改时间 2018年1月30日下午10:51:12 @修改备注 @since @throws
	 */
	private GenericService buildGenericService(DubboRouteService routeService) {
		String dubboServiceId = routeService.getDubboServiceID();
		if (gsMap.containsKey(dubboServiceId)) {
			return gsMap.get(dubboServiceId);
		} else {
			// 创建dubbo消费者
			ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
			// 设置公共属性
			reference.setApplication(applicationConfig);
			reference.setRegistry(registryConfig);
			reference.setConsumer(consumerConfig);
			// 设置服务
			reference.setInterface(routeService.getServiceName());
			reference.setGroup(routeService.getGroup());
			reference.setVersion(routeService.getVersion());
			reference.setTimeout(routeService.getClientTimeout());
			try {
				GenericService gs = reference.get();
				gsMap.put(dubboServiceId, gs);
				return gs;
			} catch (Exception e) {
				log.error("*** {}服务初始化失败，无法订阅或初始化", routeService.getDubboServiceID(), e);
				throw new GtwException("服务初始化失败，无法订阅或初始化", e);
			}

		}
	}

	@Override
	public DubboRouteService getServiceByID(String serviceId) {
		return serviceMap.get(serviceId);
	}

	@Override
	public IRouteServiceRespository<DubboRouteService> getRepository() {
		return this.repository;
	}

	@Override
	public void setRepository(IRouteServiceRespository<DubboRouteService> repository) {
		// 避免重复加载
		if (this.repository == null) {
			this.repository = repository;
		}
	}

	@Override
	public void refresh() {
		// 刷新存储器
		repository.reload();
		// 合并服务信息
		merageServiceInfo();
	}

	/**
	 * 
	 * @描述:合并加载服务 1.新增服务无条件增加 2.删除服务--删除对应服务，并销毁dubbo服务 3.变更服务 删除服务，并新增变更后的服务 @方法名:
	 * merageServiceInfo @返回类型 void @创建人 longshen @创建时间 2018年1月31日上午8:34:32 @修改人
	 * longshen @修改时间 2018年1月31日上午8:34:32 @修改备注 @since @throws
	 */
	private void merageServiceInfo() {

		ConcurrentHashMap<String, DubboRouteService> nServiceMap = new ConcurrentHashMap<String, DubboRouteService>();
		ConcurrentHashMap<String, GenericService> nGsMap = new ConcurrentHashMap<String, GenericService>();

		List<DubboRouteService> serviceList = repository.getRouteServiceList();
		Iterator<DubboRouteService> it = serviceList.iterator();
		while (it.hasNext()) {
			DubboRouteService service = it.next();
			// 判断交易容易是否存在
			if (serviceMap.containsKey(service.getServiceID())) {
				DubboRouteService oldService = serviceMap.get(service.getServiceID());
				if (!oldService.equalsDubboServiceId(oldService)) {//服务信息发生变更
					addRouterService(nServiceMap,nGsMap,service);
				} else {
                nGsMap.put(service.getDubboServiceID(), oldService.getGs()); //把原有的服务添加到新的容器中
				addRouterService(nServiceMap,nGsMap,service);
				}
			}else {
				addRouterService(nServiceMap,nGsMap,service);
			}
		}
		
		//判断待移除的泛化服务
		Iterator<GenericService> gsIt=gsMap.values().iterator();
		List<GenericService> delGsList=new ArrayList<GenericService>();
		while(gsIt.hasNext()) {
			GenericService gs=gsIt.next();
			if(!nGsMap.containsKey(gs)) {
				delGsList.add(gs);
			}
		}
		//刷新后的服务
		printServiceInfo(nServiceMap);
         
		this.serviceMap.clear();
		this.gsMap.clear();
		this.serviceMap=nServiceMap;
		this.gsMap=nGsMap;
		
		//销毁服务
		
		
		
	}
	

	
	
	private void addRouterService(ConcurrentHashMap<String, DubboRouteService> nServiceMap,ConcurrentHashMap<String, GenericService> nGsMap,DubboRouteService routeService) {
		
		if (routeService == null)
			throw new IllegalStateException("route service could not null");
		if (nServiceMap.containsKey(routeService.getServiceID())) {
			log.warn("----替换服务{}----", routeService.getServiceID());
		}
		GenericService gs = buildGenericService(nGsMap,routeService);
		routeService.setGenericService(gs);
		nServiceMap.put(routeService.getDubboServiceID(), routeService);

	}
	
	private GenericService buildGenericService(ConcurrentHashMap<String, GenericService> nGsMap,DubboRouteService routeService) {
		String dubboServiceId = routeService.getDubboServiceID();
		if (nGsMap.containsKey(dubboServiceId)) {
			return nGsMap.get(dubboServiceId);
		} else {
			// 创建dubbo消费者
			ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
			// 设置公共属性
			reference.setApplication(applicationConfig);
			reference.setRegistry(registryConfig);
			reference.setConsumer(consumerConfig);
			// 设置服务
			reference.setInterface(routeService.getServiceName());
			reference.setGroup(routeService.getGroup());
			reference.setVersion(routeService.getVersion());
			reference.setTimeout(routeService.getClientTimeout());
			try {
				GenericService gs = reference.get();
				nGsMap.put(dubboServiceId, gs);
				return gs;
			} catch (Exception e) {
				log.error("*** {}服务初始化失败，无法订阅或初始化", routeService.getDubboServiceID(), e);
				throw new GtwException("服务初始化失败，无法订阅或初始化", e);
			}

		}
	}

}
