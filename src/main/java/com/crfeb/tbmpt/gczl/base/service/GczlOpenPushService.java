package com.crfeb.tbmpt.gczl.base.service;

import java.util.List;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLJtwzDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.model.User;

public interface GczlOpenPushService {
	  void selectDataGrid(PageInfo pageInfo);
	  
	  /**
		 * 【管片质量】保存
		 * @param gczlYdxjGPZLXJInfo
		 * @param user
		 * @return
		 */
		String saveGPZLXJInfo(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user);
		
		/**
		 * 【管片质量】上报
		 * @param id 
		 * @param user
		 * @return
		 */
		String sbGPZLXJInfo(String id,User user);
		
		/**
		 * 【管片质量】审核
		 * @param gczlYdxjGPZLXJInfo
		 * @param user
		 * @return
		 */
		String shGPZLXJInfo(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user);
		
		/**
		 * 【管片质量】获取
		 * @param id
		 * @return
		 */
		GczlYdxjGPZLXJInfoDto getGPZLXJInfo(String id);
		
		/*******************************************************************************/
		
		/**
		 * 【施工质量】保存
		 * @param gczlYdxjSGZLJtwzDto
		 * @param user
		 * @return
		 */
		String saveSGZLXJInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto,User user);
		
		/**
		 * 【施工质量】上报
		 * @param id 
		 * @param user
		 * @return
		 */
		String sbSGZLXJInfo(String id,User user);
		
		/**
		 * 【施工质量】审核
		 * @param gczlYdxjSGZLJtwzDto
		 * @param user
		 * @return
		 */
		String shSGZLXJInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto,User user);
		
		/**
		 * 【施工质量】获取
		 * @param id
		 * @return
		 */
		GczlYdxjSGZLXJInfoDto getSGZLXJInfo(String id);
		
		/**
		 * 通过业务表主键获取资源附件获取
		 * @param id
		 * @param backupOne 备用字段一，非必填字段
		 * @param backupTwo 备用字段二，非必填字段
		 * @return
		 */
		List<SysFujianDto> getGPZLXJInfoPic(String id,String backupOne,String backupTwo);
		
		
		/**
		* 保存文件
		 * @param id
		 * @param sfdtos
		 * @param user
		 * @return 若保成功，则返回""; 否则返回错误信息
		 */
		String saveGPZLXJInfoPic(List<SysFujianDto> sfdtos,User user);
}
