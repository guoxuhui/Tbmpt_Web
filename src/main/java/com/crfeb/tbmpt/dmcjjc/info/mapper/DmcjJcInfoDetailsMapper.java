package com.crfeb.tbmpt.dmcjjc.info.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;

public interface DmcjJcInfoDetailsMapper extends AutoMapper<JcDetails>{
	public void batchInsert(List<JcDetails> list);
	public void deleteDetails(String fid);
	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid);
	public List<DmcjJcInfoDetailsDto> selectJcInfoDetailPage(Pagination page, Map<String, Object> params);
	public void deleteDetailsByIds(List<String> list);
	public JcDetails selectJcDetails(@Param("jcdNo")  String jcdNo,@Param("gcbh")  String gcbh);
	public Float selectBeValueOfCenturiedScgcByJcdNo(@Param("jcdNo")  String jcdNo,@Param("gcbh")  String gcbh);

}
