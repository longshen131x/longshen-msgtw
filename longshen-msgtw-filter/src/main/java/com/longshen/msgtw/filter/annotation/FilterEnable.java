package com.longshen.msgtw.filter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @类描述：过滤器开关
 * @项目名称：longshen-msgtw-filter
 * @包名： com.longshen.msgtw.filter.annotation
 * @类名称：FilterEnable
 * @创建人：longshen
 * @创建时间：2018年1月27日下午2:53:46
 * @修改人：longshen
 * @修改时间：2018年1月27日下午2:53:46
 * @修改备注：
 * @version v1.0
 * @see 
 * @bug 
 * @Copyright 
 * @mail *@qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FilterEnable {
	/**
	 * 
	 * @描述:过滤器开关，true表示开启 false 表示关闭
	 * @方法名: value
	 * @return
	 * @返回类型 boolean
	 * @创建人 longshen
	 * @创建时间 2018年1月27日下午2:54:32
	 * @修改人 longshen
	 * @修改时间 2018年1月27日下午2:54:32
	 * @修改备注
	 * @since
	 * @throws
	 */
	boolean value() default true;

}
