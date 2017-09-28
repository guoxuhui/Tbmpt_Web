package com.crfeb.tbmpt.commons.scan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 方法描述 
 * @author zhangqh 
 * @date 2016-8-2 下午12:55:25 
 */  
@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface LogOperate {  
    String value() default "";
    String param() default "";
}  