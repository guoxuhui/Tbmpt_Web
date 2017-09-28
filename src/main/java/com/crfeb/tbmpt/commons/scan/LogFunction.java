package com.crfeb.tbmpt.commons.scan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 每个类的功能描述 
 * @author zhangqh 
 * @date 2016-8-2 下午01:03:59 
 */  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE)  
@Documented  
public @interface LogFunction {
    
    String sysName() default "";
    String moduleName() default "";
    String value() default "";
}  