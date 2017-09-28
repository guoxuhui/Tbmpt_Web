package com.crfeb.tbmpt.project.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * ProRSectionLine 表数据服务层接口
 *
 */
public interface IProRSectionLineService extends ICommonService<ProRSectionLine> {

	void selectDataGrid(PageInfo pageInfo);
	void selectDataGrid(PageInfo pageInfo,User user);
	
	/**
	 * 获取所有线路信息
	 * @return List
	 */
	List<ProRSectionLine> selectLineList();
	
	/**
	 * 根据区间编号获取线路信息
	 * @param id
	 * @return
	 */
	List<ProRSectionLine> getLineBySectionId(String id);
	
	
	
	/**
	 * 保存线路信息
	 * 1、保存线路信息
	 * 2、修改盾构机状态为已经分配1
	 * @param ProRSectionLine
	 * @return
	 */
	int save(ProRSectionLine l);
	
	/**
	 * 保存线路信息
	 * 1、更新线路信息
	 * 2、如果 盾构机改变 
	 *   1、需要更新释放之前的盾构机0
	 *   2、重新分配新的盾构机为1
	 * @param ProRSectionLine
	 * @return
	 */
	int edit(ProRSectionLine l);
	
	/**
	 * 删除线路信息
	 * 0、判断是否可以删除，只有状态为0 可以删除，已开工和已完工不能删除
	 * 1、释放盾构机
	 * 2、删除数据
	 * @param id
	 * @return
	 */
	int del(String id);
	
	/**
	 * 当前线路开始工作，line_status 状态设置为1，此时线路不可以修改和删除
	 * 1、更新状态为1
	 * @param id
	 * @return
	 */
	int startWork(String id);
	
	/**
	 * 当前线路完工，需要设置状态为2，
	 * 1、此时需要释放盾构机状态，设置盾构机状态为未分配0
	 * 2、以及设置当前线路盾构机为空，施工盾构机不变
	 * 3、更新状态为2
	 * @param id
	 * @return
	 */
	int endWork(String id);
}