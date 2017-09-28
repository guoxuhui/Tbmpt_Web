package com.crfeb.tbmpt.dmcjjc.info.service;

import java.util.List;
import java.util.Map;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;

public interface IDmcjJcInfoDetailService extends ISuperService<JcDetails>{
	public List<DmcjJcInfoDetailsDto> selectJcInfoDetailPage(Pagination page, Map<String, Object> params);
}
