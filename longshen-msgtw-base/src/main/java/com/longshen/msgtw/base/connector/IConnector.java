package com.longshen.msgtw.base.connector;

import com.longshen.msgtw.base.adapter.IAdapter;
import com.longshen.msgtw.exception.GtwException;

/**
 * 
 * @类描述： 路由连接器
 * @项目名称：longshen-msgtw-base
 * @包名： com.longshen.msgtw.base.connector
 * @类名称：IConnector
 * @创建人：longshen
 * @创建时间：2018年1月26日上午11:22:12
 * @修改人：longshen
 * @修改时间：2018年1月26日上午11:22:12
 * @修改备注：负责向后端服务器通讯
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IConnector<REQ, RES> extends IAdapter{
	
	/**
	 * 
	 * @描述:发送请求
	 * @方法名: connector
	 * @param req
	 * @param args
	 * @return
	 * @throws GtwException
	 * @返回类型 RES
	 * @创建人 longshen
	 * @创建时间 2018年1月26日上午11:24:04
	 * @修改人 longshen
	 * @修改时间 2018年1月26日上午11:24:04
	 * @修改备注
	 * @since
	 * @throws
	 */
	RES connector(REQ req,Object... args) throws GtwException;

}
