package com.crfeb.tbmpt.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbminfoMapper;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;

/**
 * 
 * ProRSectionLine 表数据服务层接口实现类
 * 
 */
@Service
public class ProRSectionLineServiceImpl extends CommonServiceImpl<ProRSectionLineMapper, ProRSectionLine> implements IProRSectionLineService {
    @Autowired
    private OrgzMapper orgzMapper;
    @Autowired
    private ProRProjectSectionMapper proRProjectSectionMapper;
    @Autowired
    private ProRSectionLineMapper proRSectionLineMapper;
    
    @Autowired
    private ProTbminfoMapper proTbminfoMapper;
	
	public void selectDataGrid(PageInfo pageInfo) {
        Page<SectionLineVo> page = new Page<SectionLineVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<SectionLineVo> list = proRSectionLineMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

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
        Page<SectionLineVo> page = new Page<SectionLineVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<SectionLineVo> list = proRSectionLineMapper.selectVoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
	
	public List<ProRSectionLine> getLineBySectionId(String sid) {
		return proRSectionLineMapper.getLineBySectionId(sid);
	}

	
	public int startWork(String id) {
		int result = 0;
		ProRSectionLine line = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		List<ProRSectionLine> list = proRSectionLineMapper.selectByMap(map);
		if(list.size()>=1){
			line = list.get(0);
			/** 施工状态：0：未开工，1=已开工，2=已完工 */
			line.setLineStatus(1);
			result = proRSectionLineMapper.updateById(line);
		}
		return result;
	}

	public int endWork(String id) {
		int result = 0;
		ProRSectionLine line = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		List<ProRSectionLine> list = proRSectionLineMapper.selectByMap(map);
		if(list.size()>=1){
			line = list.get(0);
			line.setUsingTbmId(null);
			/** 施工状态：0：未开工，1=已开工，2=已完工 */
			line.setLineStatus(2);
			result = proRSectionLineMapper.updateById(line);
		}
		return result;
	}

	public int save(ProRSectionLine l) {
		int result = 0;
		//保存线路信息
		l.setUsingTbmId(l.getTbmId());
		proRSectionLineMapper.insert(l);
		return result;
	}

	public int edit(ProRSectionLine l) {
		int result = 0;
		l.setUsingTbmId(l.getTbmId());
		
		proRSectionLineMapper.updateSelectiveById(l);
		return result;
	}

	public int del(String id) {
		int result = 0;
		ProRSectionLine line = proRSectionLineMapper.selectById(id);
		/** 施工状态：0：未开工，1=已开工，2=已完工 */
		if(line.getLineStatus() == 0){
			result = proRSectionLineMapper.deleteById(id);
		}
		return result;
	}

	/**
	 * 获取所有线路信息
	 * @return List
	 */
	@Override
	public List<ProRSectionLine> selectLineList() {
		return proRSectionLineMapper.getLineList();
	}

}