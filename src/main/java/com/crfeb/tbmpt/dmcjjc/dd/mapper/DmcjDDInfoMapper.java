package com.crfeb.tbmpt.dmcjjc.dd.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dmcjjc.dd.model.DdInfo;

public interface DmcjDDInfoMapper extends AutoMapper<DdInfo> {
	public List<DdInfo> selectDdInfoPage(Pagination page, Map<String, Object> params);
	public void qy(String id);
	public void jy(String id);
	public List<DdInfo> selectDdInfos(List<String> list);
}
