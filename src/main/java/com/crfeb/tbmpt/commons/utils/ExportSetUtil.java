package com.crfeb.tbmpt.commons.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象列表转换成map列表
 * @author：smxg
 * @date：2016-10-06 11:12
 */
public class ExportSetUtil {

	/**
	 * 根据map内容和cclaess转换成clasee的实例
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			Method setter = property.getWriteMethod();
			if (setter != null) {
				setter.invoke(obj, map.get(property.getName()));
			}
		}

		return obj;
	}

	/**
	 * 把对象直接转换成map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			if(value instanceof Date){
				map.put(key,  DateUtils.format((Date)value));
			}else{
				map.put(key, value);
			}
		}

		return map;
	}
	
	/**
	 * 把对象列表转换成 列表map
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> objectToListMap(List<Object> objs) throws Exception {
		if (objs == null)
			return null;

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(Object obj :  objs){
			list.add(objectToMap(obj));
		}
		return list;
	}
	

}
