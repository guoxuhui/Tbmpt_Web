package com.crfeb.tbmpt.commons.scan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 免记录日志注解标识 
 * @author wjhu 
 * @date 2016-8-2 下午01:03:59 
 */  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD, ElementType.TYPE})  
@Documented  
public @interface LogNo {  
  
}  