package com.crfeb.tbmpt.dmcjjc.info.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ISuperService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;

/**
 * 3.1.3监测点管理
 * @author noah
 *
 */
public interface IDmcjJcPointService extends ISuperService<JcPoint>{
	public JcPoint findById(Long id);
	public void qy(String id);
	public void jy(String id);
	public HSSFWorkbook expData(List<String> ids);
	public void findByJCDNO(String JcdNo,String gcNo);
	public void selectDataGrid(PageInfo pageInfo);
	public List<DmcjJcPointDto> selectJcPointsByJcdno(List<Map<String,String>> list);
	public int countPointByJcdno(String jcdno);
	public Float getJcInfoScgc(String jcdno,String jcTime,String pid);
	public Float getJcBgScgc(String jcdno,String jcTime,String pid);
	public Float getJcBgsbScgc(String jcdno,String jcTime,String pid);
	public List<JcPoint> getJcPointsByPid(String pid);
	//wpg:生成excel模版 返回给controller
	public HSSFWorkbook generateExcelTemplate();
	//wpg:把 获取 execel文件 数据 集合 转换成   监测点对象  并  保存数据库
	public String transformationBingPreservation(List<List<Map<String,String>>> list,String gcbh) throws Exception;
}
