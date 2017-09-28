package com.crfeb.tbmpt.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.mapper.ProFbgcInfoMapper;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.vo.ProFbgcVo;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;
import com.crfeb.tbmpt.project.service.IProFbgcInfoService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbminfoMapper;

@Service
public class ProFbgcServiceImpl extends CommonServiceImpl<ProFbgcInfoMapper, ProFbgcInfo> implements IProFbgcInfoService{

	@Autowired
	private ProFbgcInfoMapper proRFbgcInfoMapper;
	
	@Autowired
    private OrgzMapper orgzMapper;
	
	@Override
	public List<ProFbgcVo> selectDataGridByDwgcId(String dwgcId) {
		
        List<ProFbgcVo> list = proRFbgcInfoMapper.selectVoListByDwgcId(dwgcId);
        return list;
	}
	
	@Override
	public List<ProFbgcInfo> getFbgcByPid(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PRO_ID", id);
		List<ProFbgcInfo> list = proRFbgcInfoMapper.selectByMap(map);
		return list;
	}
	
	@Override
	public List<ProFbgcInfo> getFbgcBySid(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("DWGC_ID", id);
		List<ProFbgcInfo> list = proRFbgcInfoMapper.selectByMap(map);
		return list;
	}

	@Override
	public List<ProFbgcInfo> getFbgcInfoBySectionId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(ProFbgcInfo fbgcInfo) {
		proRFbgcInfoMapper.insert(fbgcInfo);
		return true;
	}

	@Override
	public boolean edit(ProFbgcInfo fbgcInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean del(ProFbgcInfo fbgcInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	


	@Override
    public void selectDataGrid(PageInfo pageInfo,User user) {
//    	Orgz orgz = orgzMapper.selectById(user.getOrgzId());
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
        Page<ProFbgcVo> page = new Page<ProFbgcVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProFbgcVo> list = proRFbgcInfoMapper.selectVoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
	
}
