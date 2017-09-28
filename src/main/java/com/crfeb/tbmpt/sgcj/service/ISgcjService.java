package com.crfeb.tbmpt.sgcj.service;

import org.springframework.stereotype.Service;

/**
 * 获取施工可视化模块配置文件以及机器数据
 * 
 * @author smxg
 * 
 */
@Service
public interface ISgcjService {

	/**
	 * 根据项目ID获取项目配置文件数据
	 * @param priId
	 * @return
	 */
	String getConfig(String proId);
	
	
}
