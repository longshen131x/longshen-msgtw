package com.longshen.msgtw.filter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Filter {
/**
 * 
 * @描述:过滤器名称
 * @方法名: name
 * @return
 * @返回类型 String
 * @创建人 longshen
 * @创建时间 2018年1月27日下午2:50:30
 * @修改人 longshen
 * @修改时间 2018年1月27日下午2:50:30
 * @修改备注
 * @since
 * @throws
 */
	String name() default "";
/**
 * 
 * @描述:过滤器编号
 * @方法名: id
 * @return
 * @返回类型 String
 * @创建人 longshen
 * @创建时间 2018年1月27日下午2:50:47
 * @修改人 longshen
 * @修改时间 2018年1月27日下午2:50:47
 * @修改备注
 * @since
 * @throws
 */
	String id() default "";
/**
 * 
 * @描述:过滤器类型
 * @方法名: value
 * @return
 * @返回类型 FilterType[]
 * @创建人 longshen
 * @创建时间 2018年1月27日下午2:51:08
 * @修改人 longshen
 * @修改时间 2018年1月27日下午2:51:08
 * @修改备注
 * @since
 * @throws
 */
	FilterType[]
			value()  default  FilterType.PRE;
	/**
	 * 
	 * @描述:过滤器权值
	 * @方法名: order
	 * @return
	 * @返回类型 int
	 * @创建人 longshen
	 * @创建时间 2018年1月27日下午2:51:20
	 * @修改人 longshen
	 * @修改时间 2018年1月27日下午2:51:20
	 * @修改备注
	 * @since
	 * @throws
	 */
	int order() default 0;

}
