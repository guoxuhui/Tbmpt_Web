package com.crfeb.tbmpt.sgkshgl.jcdtgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.mapper.SgkshglJcdtglMapper;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.SgkshglJcdtgl;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.dto.SgkshglJcdtglDto;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.service.ISgkshglJcdtglService;
import com.crfeb.tbmpt.sys.model.User;
import com.baomidou.framework.service.impl.CommonServiceImpl;

/**
 * <p>基础底图管理 ServiceImpl</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class SgkshglJcdtglServiceImpl extends CommonServiceImpl<SgkshglJcdtglMapper, SgkshglJcdtgl> implements ISgkshglJcdtglService {

	@Autowired
    private SgkshglJcdtglMapper sgkshglJcdtglMapper ;
	//项目
	@Autowired
	IProProjectinfoService proProjectinfoService;
    //区间
    @Autowired
    private IProRProjectSectionService proSectionService;
    //线路
    @Autowired
    private IProRSectionLineService proRSectionLineService;
     
    /***
     * 根据用户查询列表
     */
    @Override
    public List<SgkshglJcdtglDto> selectDataGrid(User user) {
    	List<String> idList = new ArrayList<String>();
    	List<ProProjectinfo> projectinfoList = new ArrayList<ProProjectinfo>();
    	projectinfoList = proProjectinfoService.getProjectInfosByUserId(user.getId());
    	if(projectinfoList !=null){ //项目
    		for (ProProjectinfo projectinfo : projectinfoList) {
    		   List<ProRProjectSection> sectionList =  proSectionService.getSectionByProId(projectinfo.getId());
    		   if(sectionList !=null){//区间
    	    		for (ProRProjectSection section : sectionList) {
    	    		   idList.add(section.getId());       //获取Id
    	    		   List<ProRSectionLine> lineList =  proRSectionLineService.getLineBySectionId(section.getId());
    	    		   if(sectionList !=null){//线路
    	    	    		for (ProRSectionLine line : lineList) {
    	    	    			idList.add(line.getId()); //获取Id
    	    	    		}
    	    	       }
    	    		}
    	       }
    		}
    	}
    	
    	List<SgkshglJcdtglDto> jcdtglList = new ArrayList<SgkshglJcdtglDto>();
    	if(idList !=null && idList.size()>0){
    		jcdtglList = sgkshglJcdtglMapper.selectByIdList(idList);
    		if(jcdtglList.size()>0){
    			getPro(jcdtglList);
    		}
    	}
    	return jcdtglList;
       
    }
    /***
     * 获取： 项目、区间、线路 Id
     * @param jcdtglList
     */
    public void getPro(List<SgkshglJcdtglDto> jcdtglList){
    	for (SgkshglJcdtglDto dto : jcdtglList){
			if(dto.getMapType() !="" && dto.getMapType().equals("DM")){
				ProRSectionLine line = proRSectionLineService.selectById(dto.getRefId());
				if(line !=null){
					dto.setProId(line.getProId());
					dto.setSectionId(line.getSectionId());
					dto.setLineId(line.getId());
				}
			}else if(dto.getMapType() !="" && !dto.getMapType().equals("DM")){
				ProRProjectSection section = proSectionService.selectById(dto.getRefId());
				if(section !=null){
					dto.setProId(section.getProId());
					dto.setSectionId(section.getId());
				}
			}
		}
    	
    }
    
    /***
     * 根据关联Id查询列表
     */
    @Override
	public List<SgkshglJcdtglDto> selectDataGridByRefId(String refId) {
    	List<SgkshglJcdtglDto> jcdtglList = new ArrayList<SgkshglJcdtglDto>();
    	if(StringUtils.isNotBlank(refId)){
    		jcdtglList = sgkshglJcdtglMapper.selectByrefId(refId);
    		if(jcdtglList.size()>0){
    			getPro(jcdtglList);
    		}
    	}
    	return jcdtglList;
	}

    @Override
	public String addSave(SgkshglJcdtglDto dto) {
    	SgkshglJcdtgl jcdtgl =null;
    	if(StringUtils.isNotBlank(dto.getLineId())){
    		ProRSectionLine line = proRSectionLineService.selectById(dto.getLineId());        
    		if(line !=null){
    			jcdtgl = new SgkshglJcdtgl();
        		jcdtgl.setRefId(line.getId());
        		jcdtgl.setRefName(line.getLineName());
        		jcdtgl.setMapPath(dto.getMapPath());
        		jcdtgl.setMapType(dto.getMapType());
    		}
    		
    	}
    	else if(StringUtils.isNotBlank(dto.getSectionId())){
    		
    		ProRProjectSection section = proSectionService.selectById(dto.getSectionId());
    		if(section !=null){
    			jcdtgl = new SgkshglJcdtgl();
    			jcdtgl = new SgkshglJcdtgl();
        		jcdtgl.setRefId(section.getId());
        		jcdtgl.setRefName(section.getSectionName());
        		jcdtgl.setMapPath(dto.getMapPath());
        		jcdtgl.setMapType(dto.getMapType());
    		}
    	}
    	if(jcdtgl !=null){
    		this.insert(jcdtgl);
    		return "";
    	}
		return "添加失败！";
	}
    /***
     * 编辑
     */
	public String edit(SgkshglJcdtglDto dto) {
		SgkshglJcdtgl jcdtgl =null;
    	if(StringUtils.isNotBlank(dto.getLineId()) && dto.getMapType() !="" && dto.getMapType().equals("DM")){
    		ProRSectionLine line = proRSectionLineService.selectById(dto.getLineId());        
    		if(line !=null){
    			jcdtgl = new SgkshglJcdtgl();
    			jcdtgl.setId(dto.getId());
        		jcdtgl.setRefId(line.getId());
        		jcdtgl.setRefName(line.getLineName());
        		jcdtgl.setMapPath(dto.getMapPath());
        		jcdtgl.setMapType(dto.getMapType());
    		}
    		
    	}
    	else if(StringUtils.isNotBlank(dto.getSectionId())){
    		jcdtgl = new SgkshglJcdtgl();
    		ProRProjectSection section = proSectionService.selectById(dto.getSectionId());
    		if(section !=null){
    			jcdtgl = new SgkshglJcdtgl();
    			jcdtgl.setId(dto.getId());
        		jcdtgl.setRefId(section.getId());
        		jcdtgl.setRefName(section.getSectionName());
        		jcdtgl.setMapPath(dto.getMapPath());
        		jcdtgl.setMapType(dto.getMapType());
    		}
    	}
    	if(jcdtgl !=null){
    		this.updateById(jcdtgl);
    		return "";
    	}
		return "编辑失败！";
		
	}

	 

	

	
}