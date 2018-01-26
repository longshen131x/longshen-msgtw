package com.longshen.msgtw.container.rest.rxnetty.entity;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class GatewayRES<T> {
	
	private Map<String,String> headers=new HashMap<String,String>();
	
	private String content="";
	
	private T contentData;
	
	
	public GatewayRES() {
		headers.put("Content-Type", "text/plain;charset=utf-8");
	}

	
	public void putHeader(String key,Object val) {
		
		this.headers.put(key, String.valueOf(val));
		
	}
}
