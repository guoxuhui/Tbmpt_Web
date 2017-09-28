package com.crfeb.tbmpt.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ProNoUtils;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.vo.ProjectSectionVo;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * ProRProjectSection 表数据服务层接口实现类
 *
 */
@Service
public class ProRProjectSectionServiceImpl extends CommonServiceImpl<ProRProjectSectionMapper, ProRProjectSection> implements IProRProjectSectionService {
    @Autowired
    private OrgzMapper orgzMapper;
    @Autowired
    private ProRProjectSectionMapper proRProjectSectionMapper;
    @Autowired
    private ProProjectinfoMapper proProjectinfoMapper;
    
	public void selectDataGrid(PageInfo pageInfo) {
        Page<ProjectSectionVo> page = new Page<ProjectSectionVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProjectSectionVo> list = proRProjectSectionMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
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
        Page<ProjectSectionVo> page = new Page<ProjectSectionVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProjectSectionVo> list = proRProjectSectionMapper.selectVoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

	public List<ProRProjectSection> getSectionByProId(String pid) {
		return proRProjectSectionMapper.getSectionByProId(pid);
	}

	public int save(ProRProjectSection section) {
		int result = 0;
		ProProjectinfo pro = proProjectinfoMapper.selectById(section.getProId());
//		section.setSectionCode(ProNoUtils.generateSectionNo(pro,section, "-"));
		result = proRProjectSectionMapper.insert(section);
		return result;
	}

}