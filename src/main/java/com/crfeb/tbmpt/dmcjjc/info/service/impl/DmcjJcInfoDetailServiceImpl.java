package com.crfeb.tbmpt.dmcjjc.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcInfoDetailsMapper;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcInfoDetailService;

@Service
public class DmcjJcInfoDetailServiceImpl extends
		SuperServiceImpl<DmcjJcInfoDetailsMapper, JcDetails> implements
		IDmcjJcInfoDetailService {

	@Autowired
	private DmcjJcInfoDetailsMapper jcInfoDetailsMapper;
	
	public List<DmcjJcInfoDetailsDto> selectJcInfoDetailPage(Pagination page, Map<String, Object> params){
		return jcInfoDetailsMapper.selectJcInfoDetailPage(page, params);
	}
}
