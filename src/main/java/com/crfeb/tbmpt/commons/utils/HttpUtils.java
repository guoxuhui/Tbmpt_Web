package com.crfeb.tbmpt.commons.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Http数据访问工具类
 * @author gxh
 *
 */
public class HttpUtils {
	private static final Logger LOG = LogManager.getLogger(HttpUtils.class);
	private static final ThreadLocal<HttpClient> threadLocal = new ThreadLocal<HttpClient>();

	/**
	 * 根据url地址同步获取数据内容
	 * 
	 * @param url
	 * @return 返回结果为字符串
	 */
	public static String getByHttpClient(String url) {
		try {
			HttpClient httpclient = getHttpClient();
			GetMethod get = new GetMethod(url);
			httpclient.executeMethod(get);
			String result = get.getResponseBodyAsString();

			get.releaseConnection();
			return result;
		} catch (Exception e) {
			LOG.info("Error in getting from " + url, e);
		}
		return null;
	}
	
	/**
	 * 根据url地址同步获取数据内容
	 * 
	 * @param url
	 * @return 返回结果为字符串
	 */
	public static String getByHttpClientPost(String url,NameValuePair[] param) {
		try {
			HttpClient httpclient = getHttpClient();
			PostMethod post = new PostMethod(url);
	        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");    
	        post.setRequestBody(param);  
	        post.releaseConnection();  
			
			httpclient.executeMethod(post);
			String result = post.getResponseBodyAsString();
			post.releaseConnection();
			return result;
		} catch (Exception e) {
			LOG.info("Error in getting from " + url, e);
		}
		return null;
	}
	
	/**
	 * 根据url地址同步获取数据内容
	 * 可以设定当前request的地区以及语言
	 * @param url
	 * @param locale
	 * @return 返回结果为字符串
	 */
	public static String getByHttpClient(String url,String locale) {
		try {
			HttpClient httpclient = getHttpClient();
			GetMethod get = new GetMethod(url);
			get.addRequestHeader("Accept-Language",locale);
			httpclient.executeMethod(get);
			String result = get.getResponseBodyAsString();

			get.releaseConnection();
			return result;
		} catch (Exception e) {
			LOG.info("Error in getting from " + url, e);
		}
		return null;
	}
	
	/**
	 * 获取httpclient连接池
	 * 同时设置每个连接相应最大时常为5秒钟
	 * @return HttpClient
	 */
	private static HttpClient getHttpClient() {

		HttpClient httpClient = (HttpClient) threadLocal.get();
		if (httpClient == null) {
			httpClient = new HttpClient();
			httpClient.getParams().setContentCharset("UTF-8");
			httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(5000);
			httpClient.getHttpConnectionManager().getParams()
				.setSoTimeout(5000);
			threadLocal.set(httpClient);
		}
		return httpClient;
	}

}
