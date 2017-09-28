package com.crfeb.tbmpt.dmcjjc.info.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;

public interface DmcjJcInfoMapper extends AutoMapper<JcInfo>{
	public List<DmcjJcInfoDto> selectJcInfoPage(Pagination page, Map<String, Object> params);
	public void qy(String id);
	public void jy(String id);
	public List<JcInfo> selectJcInfos(List<String> list);
}
