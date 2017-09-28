package com.crfeb.tbmpt.webservice;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crfeb.tbmpt.commons.result.Request;
import com.crfeb.tbmpt.commons.result.Result;

public class BaseService {
	
	/**
	 * 校验是否合法性
	 * @param parameter
	 * @return
	 */
	protected boolean audit(String parameter){
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		String secret =  rq.getAppSecret();
		//if(secret.equals("999999")){
			return true;
		//}else return false;
	}
	
	/**
	 * 校验是否合法性
	 * @param parameter
	 * @return
	 */
	protected String erroMessage(){
		Result rs = new Result();
		rs.setMsg("非法调用!");
		rs.setSuccess(false);
		rs.setObj(null);
		return toJsonString(rs);
	}
	
	/**
	 * 校验是否合法性
	 * @param parameter
	 * @return
	 */
	protected String toJsonString(Result rs){
		/*
		QuoteFieldNames———-输出key时是否使用双引号,默认为true 
		WriteMapNullValue——–是否输出值为null的字段,默认为false 
		WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
		WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
		WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
		WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
		*/
		return JSONObject.toJSONString(rs,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
}
