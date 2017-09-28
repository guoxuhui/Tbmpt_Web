package com.crfeb.tbmpt.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置文件
 * @author smxg
 *
 */
public class SysPropertieUtil {

	private static SysPropertieUtil sysPropertie = null;
	private Properties prop = null;
	/**
	 * 获取配置信息
	 */
	private Properties getProperties() {
		InputStream in = this.getClass().getResourceAsStream("/config/sys.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
	private SysPropertieUtil() {
		if(prop == null){
			prop = getProperties();
		}
	}
	
	public static SysPropertieUtil getInstince(){
		if(sysPropertie == null){
			sysPropertie = new SysPropertieUtil();
		}
		return sysPropertie;
	}
	
	/**
	 * 获取文件上传保存路径
	 * @return uploadPath
	 */
	public String getUploadPath(){
		return prop.getProperty("uploadPath");
	}

	/**
	 * 获取文件浏览路径
	 * @return browsePath
	 */
	public String getBrowsePath(){
		return prop.getProperty("browsePath");
	}
	
	/**
	 * 获取Map浏览路径
	 * @return browsePath
	 */
	public String getMapPath(){
		return prop.getProperty("mapPath");
	}
	
	/**
	 * 获取Feature浏览路径
	 * @return browsePath
	 */
	public String getFeatureMaPath(){
		return prop.getProperty("featurePath");
	}

	public String getArcgisToken(){
		return prop.getProperty("arcgisToken");
	}
	
}
