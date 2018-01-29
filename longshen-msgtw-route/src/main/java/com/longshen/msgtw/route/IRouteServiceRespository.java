package com.longshen.msgtw.route;

import java.util.List;

import com.longshen.msgtw.exception.GtwException;

/**
 * 
 * @类描述：服务存储器配置
 * @项目名称：longshen-msgtw-route @包名： com.longshen.msgtw.route
 * @类名称：IRouteServiceRespository
 * @创建人：longshen
 * @创建时间：2018年1月29日下午3:56:37
 * @修改人：longshen
 * @修改时间：2018年1月29日下午3:56:37 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
public interface IRouteServiceRespository<RS extends IRouteService<?>> {
	List<RS> getRouteServiceList() throws GtwException;

	List<RS> getRouteServiceListByCondition(RS rs) throws GtwException;

	int addRouteService(RS rs) throws GtwException;

	int updateRouteService(RS rs) throws GtwException;

	void reload() throws GtwException;
}
