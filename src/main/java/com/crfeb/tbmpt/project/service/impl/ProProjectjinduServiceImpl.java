package com.crfeb.tbmpt.project.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;

import com.crfeb.tbmpt.project.mapper.ProProjectjinduMapper;

import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProProjectjindu;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduQueryVo;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduVo;


import com.crfeb.tbmpt.project.service.IProProjectjinduService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

@Service
public class ProProjectjinduServiceImpl extends CommonServiceImpl<ProProjectjinduMapper, ProProjectjindu> implements IProProjectjinduService {
	
	@Autowired
	private ProProjectjinduMapper proProjectjinduMapper;
	@Autowired
    private OrgzMapper orgzMapper;
//	@Override
//	public void selectDataGrid(PageInfo pageInfo) {
//		// TODO Auto-generated method stub
//		
//		Page<ProProjectjinduVo> page = new Page<ProProjectjinduVo>(pageInfo.getNowpage(), pageInfo.getSize());
//        List<ProProjectjinduVo> list = proProjectjinduMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
//        pageInfo.setRows(list);
//        pageInfo.setTotal(page.getTotal());
//		
//	}
	/**
	 * 显示一天的数据
	 */
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
		Page<ProProjectjinduVo> page = new Page<ProProjectjinduVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProProjectjinduVo> list = proProjectjinduMapper.selectInfoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
		
	}
	
	/**
	 * 根据日期,项目id查找明细
	 */
	@Override
	public List<ProProjectjinduQueryVo> selectaddDetails(String rq, String proId) {
		List<ProProjectjinduQueryVo> list = proProjectjinduMapper.selectDetails(rq,proId);
		return list;
	}

	/**
	 * 根据时间删除记录
	 */
	@Override
	public void deleteByTime(String time) {
		// TODO Auto-generated method stub
		proProjectjinduMapper.deleteByTime(time);
	}

	
}
