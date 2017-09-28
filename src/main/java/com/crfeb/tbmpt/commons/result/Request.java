package com.crfeb.tbmpt.commons.result;

/**
 * 数据请求
 * @author Administrator
 */
public class Request {
	private String appId;

    private String appSecret;

    private Object arg;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Object getArg() {
		return arg;
	}

	public void setArg(Object arg) {
		this.arg = arg;
	}

	
    
    
}
