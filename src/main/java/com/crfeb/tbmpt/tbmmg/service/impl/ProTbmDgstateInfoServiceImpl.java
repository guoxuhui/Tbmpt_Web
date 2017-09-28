package com.crfeb.tbmpt.tbmmg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbmDgstateInfoMapper;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbminfoMapper;
import com.crfeb.tbmpt.tbmmg.model.ProTbmDgstateInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbmDgstateInfoService;

/**
 *
 * ProTbmDgstateInfo 表数据服务层接口实现类
 *
 */
@Service
public class ProTbmDgstateInfoServiceImpl extends CommonServiceImpl<ProTbminfoMapper, ProTbminfo> implements IProTbmDgstateInfoService {

    @Autowired
    private ProTbmDgstateInfoMapper proTbmDgstateInfoMapper;

	@Override
	public List<ProTbmDgstateInfo> getWzZb(String riqi) {
		return proTbmDgstateInfoMapper.getWzZb(riqi);
	}

	@Override
	public List<ProTbmDgstateInfo> getTbmInfo(String riqi) {
		return proTbmDgstateInfoMapper.getTbmInfo(riqi);
	}

	@Override
	public Object getGzDbInfo(String riqi) {
		ArrayList<Object> result = new ArrayList<Object>();
		HashMap<String,String[]> zbmap = new HashMap<String,String[]>();
		ArrayList<Map<String,Object>> tbmlist = new ArrayList<Map<String,Object>>();
		
		List<ProTbmDgstateInfo> zbs = getWzZb(riqi);
		for(ProTbmDgstateInfo zb:zbs){
			String[] strs = zb.getZb().split(",");
			zbmap.put(zb.getSzwz(),strs);
		}
		
		List<ProTbmDgstateInfo> tbms = getTbmInfo(riqi);
		for(ProTbmDgstateInfo tbm:tbms){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name",tbm.getDgj());
			map.put("value",tbm.getyGzstopDays()+"/"+tbm.getyStopDays());
			map.put("wz",tbm.getSzwz());
			tbmlist.add(map);
		}
		
		result.add(zbmap);
		result.add(tbmlist);
		return result;
	}

	

}