package com.longshen.msgtw.route.dubbo;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.route.IRouteService;

import lombok.Getter;

@Getter
public class DubboRouteService implements IRouteService<GenericService> {

	private static final String SEQ = "&";

	private String serviceId;
	private String group;
	private String serviceName;
	private String version;
	private String method;
	private String inputName;
	/**
	 * dubbo 服务泛化调用接口
	 */
	private GenericService gs;
	private int clientTimeout = 3000;

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setGenericService(GenericService service) {
		this.gs = service;
	}

	/**
	 * 
	 * Title: Description:
	 * 
	 * @param group
	 *            服务分组
	 * @param serviceName
	 *            服务名称
	 * @param version
	 *            服务版本号
	 * @param method
	 *            服务方法名
	 * @param inputName
	 *            服务输入参数
	 */
	public DubboRouteService(String group, String serviceName, String version, String method, String inputName) {
		this(group, serviceName, version, method, inputName, 30);
	}

	/**
	 * 
	 * Title: Description:
	 * 
	 * @param group
	 *            服务分组
	 * @param serviceName
	 *            服务名称
	 * @param version
	 *            服务版本
	 * @param method
	 *            服务方法名
	 * @param inputName
	 *            服务输入参数
	 * @param clientTimeout
	 *            请求超时时间
	 */
	public DubboRouteService(String group, String serviceName, String version, String method, String inputName,
			int clientTimeout) {
		super();
		this.group = group;
		this.serviceName = serviceName;
		this.version = version;
		this.method = method;
		this.inputName = inputName;
		this.clientTimeout = clientTimeout;

	}

	@Override
	public GenericService build() throws GtwException {
//应为创建dubbo 请求方需要spring 复用bean  所以创建路径放到
		return null;
	}

	@Override
	public GenericService getService() {
		return this.gs;
	}

	@Override
	public String getServiceURL() {
		return getServiceID();
	}

	/**
	 * 
	 * @描述:获取dubbo服务名 @方法名:
	 * getServiceID @return @创建人：longshen @创建时间：2018年1月29日下午11:29:45 @修改人：longshen @修改时间：2018年1月29日下午11:29:45 @修改备注： @throws
	 */
	@Override
	public String getServiceID() {
		if (StringUtils.isBlank(serviceId)) {
			return getDubboServiceMethodID();
		} else {
			return serviceId;
		}

	}

	public String getDubboServiceID() {
		return group + SEQ + serviceName + SEQ + version;
	}

	/**
	 * 
	 * @描述:获取dubbo服务方法全名 @方法名: getDubboServiceMethodID @return @返回类型 String @创建人
	 *                   longshen @创建时间 2018年1月29日下午11:28:43 @修改人 longshen @修改时间
	 *                   2018年1月29日下午11:28:43 @修改备注 @since @throws
	 */
	public String getDubboServiceMethodID() {
		return group + SEQ + serviceName + SEQ + version + SEQ + method + SEQ + inputName;
	}

	@Override
	public String getServiceVersion() {
		return this.version;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "dubbo";
	}
	
	
	public boolean equals(Object obj) {
		if(obj instanceof DubboRouteService) {
			return StringUtils.equals(this.getDubboServiceMethodID(), ((DubboRouteService) obj).getDubboServiceMethodID());
		}else {
			return false;
		}
	}
	
	public boolean equalsDubboServiceId(DubboRouteService service) {
		
		if(StringUtils.equals(this.getDubboServiceID(), service.getDubboServiceID()) && this.clientTimeout==service.getClientTimeout()) {
			return true;
		}else {
			return false;
		}
	}

}
