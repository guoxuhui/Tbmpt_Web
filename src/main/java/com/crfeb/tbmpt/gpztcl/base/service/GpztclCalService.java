package com.crfeb.tbmpt.gpztcl.base.service;


import com.alibaba.fastjson.JSONArray;
/**
 * 管片错台偏移测量计算接口
 * @author gxh
 *
 */
public interface GpztclCalService {

	/**
	 * 计算设计中心线坐标
	 * 如果计算错误或者没有设计中心参数，则返回空值
	 * @param sczbs JSONArray "[{'环号': 340,  '坐标X': 46364.2988,  '坐标Y': 190583.5955}]";
	 * @param lineId
	 * @return
	 */
	public String calSjzxInfo(JSONArray sczb,String lineId);
	
	/**
	 * 根据线路ID获取平曲线要素
	 * 如果查询空则返回null
	 * @param lineId
	 * @return
	 */
	public JSONArray getXyfysInfo(String lineId);
	
	/**
	 * 根据线路ID获取平曲线要素
	 * 如果查询空则返回null
	 * @param lineId
	 * @return
	 */
	public JSONArray getSqxysInfo(String lineId);
	
}
