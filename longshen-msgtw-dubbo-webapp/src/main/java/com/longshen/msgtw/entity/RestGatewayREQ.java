package com.longshen.msgtw.entity;

import com.longshen.msgtw.container.rest.rxnetty.entity.GatewayREQ;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RestGatewayREQ  extends GatewayREQ<ReqData>{
	/**
	 * 是否成功申请信号量
	 */
	private boolean hasSemaphore=false;

}
