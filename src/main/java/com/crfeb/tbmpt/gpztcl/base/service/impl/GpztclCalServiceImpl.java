package com.crfeb.tbmpt.gpztcl.base.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.wsdl.SzzxSoapProxy;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclQxys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclCalService;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclQxysService;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclXyfysService;

/**
 * 管片姿态测量计算接口
 * @author gxh
 *
 */
@Service
public class GpztclCalServiceImpl implements GpztclCalService{

	@Autowired
    private GpztclXyfysService gpztclXyfysService;
	@Autowired
	private GpztclQxysService gpztclQxysService;
	
	private SzzxSoapProxy proxy = new SzzxSoapProxy();
	
	/**
	 * 计算设计中心线坐标
	 * 如果计算错误或者没有设计中心参数，则返回空值
	 * @param sczbs JSONArray "[{'环号': 340,  '坐标X': 46364.2988,  '坐标Y': 190583.5955}]";
	 * @param lineId
	 * @return
	 */
	public String calSjzxInfo(JSONArray sczb,String lineId){
		
		if(sczb == null){
			return null;
		}
		JSONArray xyys = this.getXyfysInfo(lineId);
		JSONArray sqys = this.getSqxysInfo(lineId);
		if(sqys == null || xyys == null){
			return null;
		}
		
		String strXyys = xyys.toString();
		String strSqys = sqys.toString();
		strXyys = strXyys.replace("\"", "'");
		strSqys = strSqys.replace("\"", "'");
		String strSczb = sczb.toString();
		strSczb = strSczb.replace("\"", "'");
		String result = null;
		try {
			System.out.println(strXyys);
			System.out.println(strSqys);
			System.out.println(strSczb);
			result = proxy.hqxlzxzb(strXyys, strSqys, strSczb);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据线路ID获取平曲线要素
	 * 如果查询空则返回null
	 * @param lineId
	 * @return
	 */
	public JSONArray getXyfysInfo(String lineId){
		List<GpztclXyfysDto> list = gpztclXyfysService.selectXyfys(lineId);
		JSONArray arr = new JSONArray();
		for(GpztclXyfysDto dto:list){
			JSONObject json = new JSONObject();
			json.put("qdzh", dto.getQdZh());
			json.put("zdzh", dto.getZdZh());
			json.put("qdzbX", dto.getQdZbX());
			json.put("qdzbY", dto.getQdZbY());
			json.put("qdfwj_dms", dto.getQdFwj());
			json.put("qdfwj", dto.getQdFwj());
			json.put("qdbj", dto.getQdBj());
			json.put("zdbj", dto.getZdBj());
			json.put("zx", dto.getZx());
			json.put("qdpyl", dto.getQdPyl());
			json.put("zdpyl", dto.getZdPyl());
			arr.add(json);
		}
		if(arr.size() == 0){
			return null;
		}else{
			return arr;
		}
	}
	
	/**
	 * 根据线路ID获取平曲线要素
	 * 如果查询空则返回null
	 * @param lineId
	 * @return
	 */
	public JSONArray getSqxysInfo(String lineId){
		List<GpztclQxys> list = gpztclQxysService.selectByFIdQxysList(lineId);
		JSONArray arr = new JSONArray();
		for(GpztclQxys dto:list){
			JSONObject json = new JSONObject();
			json.put("bpd_zh", dto.getBpdzh());
			json.put("bpd_gc", dto.getBpdgc());
			json.put("sjqxbj", dto.getSjqxbj());
			arr.add(json);
		}
		if(arr.size() == 0){
			return null;
		}else{
			return arr;
		}
	}
	
}
