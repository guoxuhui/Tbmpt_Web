package com.crfeb.tbmpt.dmcjjc.info.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ISuperService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;

/**
 * 3.1.4日常监测管理
 * @author noah
 *
 */
public interface IDmcjJcInfoService extends ISuperService<JcInfo>{
	public void selectDataGrid(PageInfo pageInfo);
	public void save(DmcjJcInfoDto dto);
	public void update(DmcjJcInfoDto dto);
	public JcInfo findById(Long id);
	public void qy(String id);
	public void jy(String id);
	public HSSFWorkbook expData(List<String> ids,String pid);
	//给导入文件里的监测点计算变化量
	public List<DmcjJcInfoDetailsDto> calcBhl( Map<String,Map<String,String>> list,String  jcTime,String pid);
	//计算变化率
	public List<DmcjJcInfoDetailsDto> calcBhl(String fid,String jcTime,PageInfo pageInfo,String pid);
	//根据外键删除子表
	public void deleteDetails(String fid);
	//根据监测点编号查询子表是否存在该监测点
	public int selectDetailsByJcd(String jcdbh);
	//根据外键查询子表记录
	public List<DmcjJcInfoDetailsDto> selectDetailsByFid(String fid);
	//根据主键批量删除子表记录
	public void deleteDetailsByIds(List<String> list);
	//保存监测信息明细
	public void saveDetail(JcDetails detail);

	//不存在的监测点
	public List<String> isno( Map<String,Map<String,String>> map,String  jcTime,String pid);
	//创建excel模板
	public HSSFWorkbook generateExcelTemplate();

	
	/**
	 * 方法说明：同时更新主子表数据<br>
	 * （此方法在更新明细数据时会先将主表之前的明细全部删除，再添加新的明细数据）<br>
	 * @param dto 数据dto
	 * @author:YangYj
	 * @Time: 2016年12月13日 下午2:58:55
	 */
	public void updateAll(DmcjJcInfoDto dto);

}
