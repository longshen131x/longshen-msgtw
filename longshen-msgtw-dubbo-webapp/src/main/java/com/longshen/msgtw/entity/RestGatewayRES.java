package com.longshen.msgtw.entity;

import com.longshen.msgtw.container.rest.rxnetty.entity.GatewayRES;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @类描述：rest服务json应答数据
 * @项目名称：longshen-msgtw-dubbo-webapp
 * @包名： com.longshen.msgtw.entity
 * @类名称：RestGatewayRES
 * @创建人：longshen
 * @创建时间：2018年2月1日下午3:35:50
 * @修改人：longshen
 * @修改时间：2018年2月1日下午3:35:50
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RestGatewayRES extends GatewayRES<ReqData> {

}
