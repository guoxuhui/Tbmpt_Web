package com.crfeb.tbmpt.dmcjjc.bg.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ISuperService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;

public interface IDmcjJcbgsbInfoService extends ISuperService<JcbgsbInfo>{
	public void save(DmcjJcInfoDto dmcjJcPoint);
	public void del(Long id);
	public void update(JcbgsbInfo dmcjDDInfo);
	public JcbgsbInfo findById(Long id);
	public void selectDataGrid(PageInfo pageInfo);
	public HSSFWorkbook expData(List<String> asList);
	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid,String jcTime,String pid);
	public List<DmcjJcInfoDetailsDto> calcBhl(String fid, String jcTime, PageInfo pageInfo, String id);
	public void deleteDetails(String id);
	public void deleteAndDetails(String id);
	public Boolean deleteAndDetailsList(String[] ids);
	public void qy(String id);
	public void jy(String id);
	/**
	 * 方法说明：同时更新主子表数据<br>
	 * （此方法在更新明细数据时会先将主表之前的明细全部删除，再添加新的明细数据）<br>
	 * @param dto 数据dto
	 * @author:YangYj
	 * @Time: 2016年12月13日 下午2:58:55
	 */
	public void updateAll(DmcjJcInfoDto dto);
}
