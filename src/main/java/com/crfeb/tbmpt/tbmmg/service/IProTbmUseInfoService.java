package com.crfeb.tbmpt.tbmmg.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.model.ProTbmUseInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.model.dto.TbmUseInfoDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;

public interface IProTbmUseInfoService extends ICommonService<ProTbmUseInfo>{
	
	/**
	 * 获取数据表格的内容
	 * @param pageInfo
	 */
	void selectDataGrid(PageInfo pageInfo,User user);
	
	/**
	 * 获取盾构机使用历史记录列表
	 * @return
	 */
	List<ProTbmUseInfo> selectAllList();
	
	String save(TbmUseInfoDto tbmUseInfoDto,User user) throws Exception;

	String update(TbmUseInfoDto tbmUseInfoDto, User currentUser);

	TbmUseInfoDto selectBytbmid(String tbmid);
	
}
