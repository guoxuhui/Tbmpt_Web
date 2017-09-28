package com.crfeb.tbmpt.dgjj.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjj.mapper.DgjjBzglMapper;
import com.crfeb.tbmpt.dgjj.model.DgjjBzgl;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglService;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

@Service
public class DgjjBzglServiceImp extends CommonServiceImpl<DgjjBzglMapper, DgjjBzgl> implements DgjjBzglService {
	@Autowired
    private OrgzMapper orgzMapper;
	@Autowired
    private DgjjBzglMapper bzglMapper;
	
	/**
	 * 班组数据表格查询方法
	 */
	@Override
	public void selectDataGrid(PageInfo pageInfo, User user) {
		// TODO Auto-generated method stub
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
        Page<DgjjBzglDto> page = new Page<DgjjBzglDto>(pageInfo.getNowpage(), pageInfo.getSize());
        List<DgjjBzglDto> list = bzglMapper.selectVoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
		
	}
	
}
