package com.longshen.msgtw.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class ResData extends Object implements Serializable {

	/**
	 * @字段：serialVersionUID @功能描述：
	 * @创建人：longshen
	 * @创建时间：2018年2月1日下午3:22:29
	 */

	private static final long serialVersionUID = -2388477709082026168L;

	private String version;
	private String resultcode;
	private String resultdesc;
	private String orgtratecode;
	private String orgmsgid;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss.sss")
	private Date  sendTime;
	
	private Map<String,Object> body;
	
	
	public ResData() {}

}
