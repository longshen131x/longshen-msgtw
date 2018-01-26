package com.longshen.msgtw.base.adapter;

import com.longshen.msgtw.exception.GtwException;

/**
 * 适配器
 * @author longshen
 *
 */
public interface IAdapter {
	/**
	 * 初始化网关
	 * @throws GtwException
	 */
	void init() throws GtwException;
	
	/**
	 * 启动网关
	 * @throws GtwException
	 */
	void  start() throws GtwException;
	
	/**
	 * 
	* @Title: shutdown  
	* @Description: 停止网关
	* @param     参数  
	* @return void    返回类型  
	* @throws  GtwException
	 */
	void shutdown() throws GtwException;
	

}
