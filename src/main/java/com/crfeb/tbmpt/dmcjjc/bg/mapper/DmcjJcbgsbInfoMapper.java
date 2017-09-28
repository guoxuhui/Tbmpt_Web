package com.crfeb.tbmpt.dmcjjc.bg.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbDetails;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.vo.JcbgsbInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;

public interface DmcjJcbgsbInfoMapper extends AutoMapper<JcbgsbInfo>{
	public List<JcbgsbInfoDto> selectBgsbPage(Page<JcbgsbInfo> page,
			Map<String, Object> condition);
	public List<JcbgsbInfo> selectBgsb(List<String> ids);
	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid);
	public void batchInsert(List<JcbgsbDetails> list);
	public void deleteDetails(String fid); 
	public void qy(String id);
	public void jy(String id);
}
