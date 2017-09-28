package com.crfeb.tbmpt.gpztcl.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclXyfysMapper;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclXyfys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclXyfysService;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;
import com.crfeb.tbmpt.sys.model.User;

@Service
public class GpztclXyfysServiceImpl extends CommonServiceImpl<GpztclXyfysMapper, GpztclXyfys> implements GpztclXyfysService{
	@Autowired
    private ProRSectionLineMapper proRSectionLineMapper;
	@Autowired
    private GpztclXyfysMapper gpztclXyfysMapper;

	/**
	 * 根据线路id查找主表信息
	 */
	public List<SectionLineVo> selectByXlId(String xlId){
		
		List<SectionLineVo> list = proRSectionLineMapper.selectByXlId(xlId);
		return list;
	}

	/**
	 * 查询平曲线列表
	 */
	public List<GpztclXyfysDto> selectXyfys(String xlId) {
		List<GpztclXyfysDto>list=gpztclXyfysMapper.selectXyfysMapper(xlId);
		return list;
	}


}
