package com.longshen.msgtw.base.adapter;

import com.longshen.msgtw.exception.GtwException;

/**
 * 
* @ClassName: ICallback  
* @Description: 回调函数 
* @author longshen 
* @date 2018年1月26日  
*  
* @param <REQ>
* @param <RES>
 */
public interface ICallback<REQ, RES> {

	/**
	 * 
	 * @描述:容器请求前处理
	 * @方法名: before
	 * @param Req
	 * @param args
	 * @throws GtwException
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年1月26日上午11:16:35
	 * @修改人 longshen
	 * @修改时间 2018年1月26日上午11:16:35
	 * @修改备注
	 * @since
	 * @throws
	 */
	void before(RES Req,Object... args) throws Exception;
	
	/**
	 * 
	 * @描述:回调函数
	 * @方法名: callback
	 * @param res
	 * @param args
	 * @return
	 * @throws Exception
	 * @返回类型 RES
	 * @创建人 longshen
	 * @创建时间 2018年1月26日上午11:19:24
	 * @修改人 longshen
	 * @修改时间 2018年1月26日上午11:19:24
	 * @修改备注
	 * @since
	 * @throws
	 */
	RES callback(RES res,Object... args) throws  Exception;
	
   /**
    * 
    * @描述: 容器请求后处理
    * @方法名: after
    * @param req
    * @param args
    * @throws Exception
    * @返回类型 void
    * @创建人 longshen
    * @创建时间 2018年1月26日上午11:20:07
    * @修改人 longshen
    * @修改时间 2018年1月26日上午11:20:07
    * @修改备注
    * @since
    * @throws
    */
	void after(REQ req,Object... args) throws  Exception;
	
	/**
	 * 
	 * @描述:抛出异常处理
	 * @方法名: exception
	 * @param t
	 * @返回类型 void
	 * @创建人 longshen
	 * @创建时间 2018年1月26日上午11:20:50
	 * @修改人 longshen
	 * @修改时间 2018年1月26日上午11:20:50
	 * @修改备注
	 * @since
	 * @throws
	 */
	void exception(Throwable t);

}
