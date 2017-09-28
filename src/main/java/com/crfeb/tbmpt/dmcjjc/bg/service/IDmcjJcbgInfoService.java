package com.crfeb.tbmpt.dmcjjc.bg.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ISuperService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;

public interface IDmcjJcbgInfoService extends ISuperService<JcbgInfo>{
	public void save(DmcjJcInfoDto dto);
	public void update(DmcjJcInfoDto dmcjDDInfo);
	public JcbgInfo findById(Long id);
	public HSSFWorkbook expData(List<String> asList,String pid);
	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid,String jcTime,String pid);
	public void selectDataGrid(PageInfo pageInfo);
	public void qy(String id);
	public void jy(String id);
	public void updateShangbaoStatus(String id);
	public void deleteDetails(String id);
	public List<DmcjJcInfoDetailsDto> calcBhl(String fid, String jcTime,PageInfo pageInfo,String pid);
	public void deleteDetailsByIds(List<String> asList);
	public void saveDetail(JcbgDetail detail);
	public List<DmcjJcInfoDetailsDto> selectDetailsByFid(String id);
	/**
	 * 方法说明：同时更新主子表数据<br>
	 * （此方法在更新明细数据时会先将主表之前的明细全部删除，再添加新的明细数据）<br>
	 * @param dto 数据dto
	 * @author:YangYj
	 * @Time: 2016年12月13日 下午2:58:55
	 */
	public void updateAll(DmcjJcInfoDto dto);
}
