package com.longshen.msgtw.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 
 * @类描述：请求内容对象
 * @项目名称：longshen-msgtw-dubbo-webapp @包名： com.longshen.msgtw.entity
 * @类名称：ReqData
 * @创建人：longshen
 * @创建时间：2018年2月1日下午3:08:52
 * @修改人：longshen
 * @修改时间：2018年2月1日下午3:08:52 @修改备注：
 * @version v1.0
 * @see
 * @bug
 * @Copyright
 * @mail *@qq.com
 */
@Data
public class ReqData extends Object implements Serializable {

	/**
	 * @字段：serialVersionUID @功能描述：
	 * @创建人：longshen
	 * @创建时间：2018年2月1日下午3:08:10
	 */

	private static final long serialVersionUID = -2056863220022951041L;

	private String version;

	private String sender;

	private String receiver;
	/**
	 * 渠道终端类型
	 */
	private String chlterminatype;
	/**
	 * 交易编号
	 */
	private String tratecode;

	private String msgid;
	@JSONField(format = "yyyyMMdd")
	private Date msgdate;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss.sss")
	private Date sendtime;

	private Map<String, Object> body;

}
