package com.crfeb.tbmpt.dmcjjc.info.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.dmcjjc.zdcjddb.model.Zdcjddb;
import com.crfeb.tbmpt.dmcjjc.zdcjddb.model.ZdcjddbDetails;

public interface DmcjJcPointMapper extends AutoMapper<JcPoint>{
	public List<DmcjJcPointDto> selectJcPointPage(Pagination page, @Param("params")Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	public void qy(String id);
	public void jy(String id);
	public List<JcPoint> selectJcPoints(List<String> list);
	public List<DmcjJcPointDto> selectJcPointsByJcdno(List<Map<String,String>> list);
	public int countPointByJcdno(String jcdno);
	public Float getJcInfoScgc(@Param("jcdno")  String jcdno,@Param("jcTime") String jcTime,@Param("pid")  String pid);
	public Float getJcBgScgc(@Param("jcdno")  String jcdno,@Param("jcTime") String jcTime,@Param("pid")  String pid);
	public Float getJcBgsbScgc(@Param("jcdno") String jcdno, @Param("jcTime") String jcTime,@Param("pid") String pid);

	public List<Zdcjddb> getZdcjddbData(@Param("qujians") List<String> qujians,@Param("sDate") String sDate,@Param("eDate") String eDate);
	public List<ZdcjddbDetails> getZdcjddbDetailsData(@Param("qujians") List<String> qujians,@Param("jcdno") String jcdno,@Param("sDate") String sDate,@Param("eDate") String eDate);
}
