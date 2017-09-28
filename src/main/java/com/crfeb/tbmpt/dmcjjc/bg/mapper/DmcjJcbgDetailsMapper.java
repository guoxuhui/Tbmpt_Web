package com.crfeb.tbmpt.dmcjjc.bg.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;

public interface DmcjJcbgDetailsMapper extends AutoMapper<JcbgDetail>{
	public void batchInsert(List<JcbgDetail> list);
	public void deleteDetails(String fid);
	public List<DmcjJcInfoDetailsDto> selectJcbgDetailPage(Page<JcbgDetail> page, Map<String, Object> condition);
	public void deleteDetailsByIds(List<String> list);
	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid);
}
