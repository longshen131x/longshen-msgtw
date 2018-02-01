package com.longshen.msgtw.config.route;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class DubboRouteServiceInfo implements Serializable {
	/**
	 * @字段：serialVersionUID @功能描述：
	 * @创建人：longshen
	 * @创建时间：2018年2月1日上午10:20:11
	 */

	private static final long serialVersionUID = -1110420142858865068L;

	private String serviceGroup;
	private String serviceName;
	private String version;
	private List<String> methodList = new ArrayList<String>();
	private Integer clientTimeout;

	/**
	 * 
	 * @描述:获取方法名
	 * @方法名: getMethodName
	 * @param methodInfo
	 * @return
	 * @返回类型 String
	 * @创建人 longshen
	 * @创建时间 2018年2月1日上午10:34:55
	 * @修改人 longshen
	 * @修改时间 2018年2月1日上午10:34:55
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static String getMethodName(String methodInfo) {
		if (StringUtils.isNotBlank(methodInfo) && methodInfo.indexOf("::") != -1) {
			String[] arg = methodInfo.split("::");
			if (arg.length == 2) {
				return arg[0];
			}
		}
		return null;

	}
	/**
	 * 
	 * @描述:获取入参对象名称
	 * @方法名: getInputName
	 * @param methodInfo
	 * @return
	 * @返回类型 String
	 * @创建人 longshen
	 * @创建时间 2018年2月1日上午10:35:10
	 * @修改人 longshen
	 * @修改时间 2018年2月1日上午10:35:10
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static String getInputName(String methodInfo) {
		if (StringUtils.isNotBlank(methodInfo) && methodInfo.indexOf("::") != -1) {
			String[] arg = methodInfo.split("::");
			if (arg.length == 2) {
				return arg[1];
			}
		}
		return null;

	}

}
