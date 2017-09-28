package com.crfeb.tbmpt.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.project.mapper.ProDwgcInfoMapper;
import com.crfeb.tbmpt.project.mapper.ProFbgcInfoMapper;
import com.crfeb.tbmpt.project.model.ProDwgcInfo;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.vo.ProDwgcVo;
import com.crfeb.tbmpt.project.service.IProDwgcInfoService;
import com.crfeb.tbmpt.project.service.IProFbgcInfoService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

@Service
public class ProDwgcServiceImpl extends CommonServiceImpl<ProDwgcInfoMapper, ProDwgcInfo> implements IProDwgcInfoService{
	
	@Autowired
	private ProDwgcInfoMapper proDwgcInfoMapper;
	@Autowired
    private OrgzMapper orgzMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo,User user) {
//		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
//    	String code = "-1";
//    	if(orgz != null){
//    		if(orgz.getType() <= 1){
//    			code = orgz.getCode();
//    		}else{
//    			Orgz orgz2 = orgzMapper.selectById(orgz.getPid());
//    			code = orgz2.getCode();
//    		}
//    	}
//    	pageInfo.getCondition().put("code", code);
        Page<ProDwgcVo> page = new Page<ProDwgcVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProDwgcVo> list = proDwgcInfoMapper.selectVoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	
	
	@Override
	public List<ProDwgcInfo> getDwgcInfoByProId(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PRO_ID", id);
		List<ProDwgcInfo> list = proDwgcInfoMapper.selectByMap(map);
		return list;
	}
	
	
}
