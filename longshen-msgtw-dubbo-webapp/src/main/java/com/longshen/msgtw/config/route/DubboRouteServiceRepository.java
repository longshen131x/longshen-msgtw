package com.longshen.msgtw.config.route;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.longshen.msgtw.common.utils.FileUtils;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.IRouteServiceRespository;
import com.longshen.msgtw.route.dubbo.DubboRouteService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@EqualsAndHashCode(callSuper = false)
@DisconfFile(filename = DubboRouteServiceRepository.FILE)
public class DubboRouteServiceRepository implements IRouteServiceRespository<DubboRouteService> {

	public static final String FILE = "routefilter_service.josn";

	private DubboRouteServiceInfoMap serviceMap;

	@PostConstruct
	public void loadConfig() throws IOException {
		log.info("-------------LOAD DUBBO ROUTE SERVICE CONDIG START -----------------");
		String jsonStr = FileUtils.readFileToString(FILE, Charset.forName("UTF-8"));

		DubboRouteServiceInfoMap map = JSON.parseObject(jsonStr, DubboRouteServiceInfoMap.class);

		serviceMap = map;
		log.info("远程服务一共配置[{}]项", map.getServiceMap().size());
		log.info("-------------LOAD DUBBO ROUTE SERVICE CONDIG END   -----------------");

	}

	@Override
	public List<DubboRouteService> getRouteServiceList() throws GtwException {
		Iterator<String> it = serviceMap.getServiceMap().keySet().iterator();
		List<DubboRouteService> serviceList = new ArrayList<DubboRouteService>();
		while (it.hasNext()) {
			String serviceId = it.next();
			DubboRouteServiceInfo serviceInof = serviceMap.getDubboRouteServiceInfo(serviceId);
			Iterator<String> methodIt = serviceInof.getMethodList().iterator();
			while (methodIt.hasNext()) {
				String methodInfo = methodIt.next();
				String methodName = DubboRouteServiceInfo.getMethodName(methodInfo);
				String inputName = DubboRouteServiceInfo.getInputName(methodInfo);
				if (StringUtils.isBlank(methodName) || StringUtils.isBlank(inputName)) {
					log.error(
							"**Dubbo Route Service Error ,methodName or inputName is empty ,auot ignore ,DubboRouteServiceInfo[{}]..",
							ToStringBuilder.reflectionToString(serviceInof, ToStringStyle.SIMPLE_STYLE));

				}else {
					
					DubboRouteService service =new DubboRouteService(serviceInof.getServiceGroup(), serviceInof.getServiceName(), serviceInof.getVersion(), methodName, inputName, serviceInof.getClientTimeout());
					service.setServiceId(serviceId+"::"+methodName);
					serviceList.add(service);
				}
			}
		}
		return serviceList;
	}

	@Override
	public List<DubboRouteService> getRouteServiceListByCondition(DubboRouteService rs) throws GtwException {

		throw new GtwException("no supported");
	}

	@Override
	public int addRouteService(DubboRouteService rs) throws GtwException {
		throw new GtwException("no supported");
	}

	@Override
	public int updateRouteService(DubboRouteService rs) throws GtwException { // TODO Auto-generated method stub
		throw new GtwException("no supported");
	}

	@Override
	public void reload() throws GtwException {

		try {
			loadConfig();
		} catch (IOException e) {
			throw new GtwException("999999", "路由服务配置错误", e);
		}
	}

}
