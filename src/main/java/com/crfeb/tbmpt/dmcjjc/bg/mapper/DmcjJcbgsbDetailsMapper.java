package com.crfeb.tbmpt.dmcjjc.bg.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;

public interface DmcjJcbgsbDetailsMapper extends AutoMapper<JcbgsbDetails>{
	public List<DmcjJcInfoDetailsDto> selectJcbgsbDetailPage(Page<JcbgsbDetails> page, Map<String, Object> condition);
}
