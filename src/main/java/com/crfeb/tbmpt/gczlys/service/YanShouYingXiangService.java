package com.crfeb.tbmpt.gczlys.service;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.aqxj.model.dto.AqxjXjdflDto;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczlys.model.YanShouYingXiang;
import com.crfeb.tbmpt.gczlys.model.dto.YanShouYingXiangDto;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;
import java.util.Map;


public interface YanShouYingXiangService extends ICommonService<YanShouYingXiang>{
	
	 void selectDataGrid(PageInfo pageInfo);
	 
	 public String update(YanShouYingXiang dto, User user);
	 
	 int insert(YanShouYingXiang yanShouYingXiang, User user);
	 
	 String checkClumIfexits(YanShouYingXiang yanShouYingXiang, String[] clumNames);

	/**
	 * 根据条件获取巡检日历信息
	 * @param condition
	 * @return
	 */
	 List<Map<String,Object>> selectReport(Map<String,Object> condition);

	void selectDataGridList(PageInfo pageInfo);

}
