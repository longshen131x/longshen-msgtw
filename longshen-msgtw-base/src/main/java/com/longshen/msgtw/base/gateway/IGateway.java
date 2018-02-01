package com.longshen.msgtw.base.gateway;

import com.longshen.msgtw.base.adapter.IAdapter;
import com.longshen.msgtw.base.container.IContainer;
import com.longshen.msgtw.exception.GtwException;
/**
 * 
 * @类描述：微服务网关定义
 * @项目名称：longshen-msgtw-base
 * @包名： com.longshen.msgtw.base.gateway
 * @类名称：IGateway
 * @创建人：longshen
 * @创建时间：2018年2月1日下午2:42:14
 * @修改人：longshen
 * @修改时间：2018年2月1日下午2:42:14
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
public interface IGateway<REQ,RES> extends IAdapter {
	
	void inject(IContainer<REQ, RES> container,Object... args) throws GtwException;

}
