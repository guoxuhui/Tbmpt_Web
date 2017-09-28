package com.crfeb.tbmpt.dmcjjc.bg.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;

public interface DmcjJcbgInfoMapper extends AutoMapper<JcbgInfo>{

	public List<JcbgInfo> selectBgPage(Page<JcbgInfo> page,
			Map<String, Object> condition);
	public List<JcbgInfo> selectBg(List<String> ids);
	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid);
	public void qy(String id);
	public void jy(String id);
	
	public void deleteDetails(String fid);
	public void updateShangbaoStatus(String id);
	public void updateShangbaoStatusWsb(String id);
	public void batchInsert(List<JcbgDetail> list);
}
