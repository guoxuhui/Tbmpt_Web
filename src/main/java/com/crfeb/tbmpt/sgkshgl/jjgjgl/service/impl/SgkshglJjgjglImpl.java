package com.crfeb.tbmpt.sgkshgl.jjgjgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.monitor.mapper.ProGeoLinesMapper;
import com.crfeb.tbmpt.monitor.model.GeoLineModel;
import com.crfeb.tbmpt.monitor.model.ProGeoLines;
import com.crfeb.tbmpt.monitor.service.IProGeoLinesService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.mapper.SgkshglJjgjglMapper;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.SgkshglJjgjgl;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.dto.SgkshglJjgjglDto;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.service.ISgkshglJjgjglService;
import com.crfeb.tbmpt.sys.mapper.LogMapper;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Log;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.ILogService;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * ProGeoLines 表数据服务层接口实现类
 *
 */
@Service
public class SgkshglJjgjglImpl extends CommonServiceImpl<SgkshglJjgjglMapper, SgkshglJjgjgl> implements ISgkshglJjgjglService{
	
	@Autowired
	private SgkshglJjgjglMapper sgkshglJjgjglMapper;

	@Override
	public List<SgkshglJjgjglDto> selectBylineId(String lineId) {
		List<SgkshglJjgjglDto> list=sgkshglJjgjglMapper.selectBylineId(lineId);		
		return list;
	}

	@Override
	public String saveAll(String points, String lineId) {
		String str=points;
		if(StringUtils.isNotBlank(str)){
    	String[] s=str.split(";");
    	List<SgkshglJjgjgl> list =new ArrayList<SgkshglJjgjgl>();
    	Double ring=1.0;
    	for(int i=0;i<s.length;i++){
    		SgkshglJjgjgl sgkshglJjgjgl=new SgkshglJjgjgl();
    		String[] sub=s[i].split(",");    		
    		sgkshglJjgjgl.setLId(lineId);
    		sgkshglJjgjgl.setLng(sub[0]);
    		sgkshglJjgjgl.setLat(sub[1]);
    		if(i==0){
    			sgkshglJjgjgl.setRing(ring);
    		}else{
    			sgkshglJjgjgl.setRing(i*5.0);
    		}
    		list.add(sgkshglJjgjgl);
    	}
    	Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("L_ID", lineId);		
		sgkshglJjgjglMapper.deleteByMap(columnMap);
		sgkshglJjgjglMapper.insertBatch(list);
		}else{
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("L_ID", lineId);		
			sgkshglJjgjglMapper.deleteByMap(columnMap);
		}
		return "成功";
	}


	 


}