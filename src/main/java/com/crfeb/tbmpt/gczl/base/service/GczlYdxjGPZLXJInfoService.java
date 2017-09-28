package com.crfeb.tbmpt.gczl.base.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>盾构施工质量巡检信息Service接口</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjGPZLXJInfoService extends ICommonService<GczlYdxjGPZLXJInfo>{

    void selectDataGrid(PageInfo pageInfo);

    /**
     * 新增盾构施工质量巡检信息
     * @param gczlYdxjGPZLXJInfo 要保存的实体
     */
    void save(GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo);

    /**
     * 通过集合删除盾构施工质量巡检信息
     * @param ids String字符串，中间用“,”间隔开
     */
    void del(String[] ids);

    /**     * 修改盾构施工质量巡检信息
     * @param gczlYdxjGPZLXJInfo 要修改的实体
     */
    void update(GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo);

   /**
     * 通过ID查找盾构施工质量巡检信息
     * @param id 数据ID
     */
    GczlYdxjGPZLXJInfo findById(String id);
    
    /**
     * 通过ID查找盾构施工质量巡检信息Dto
     * @param id
     * @return
     */
    GczlYdxjGPZLXJInfoDto findDtoById(String id);

	/**
	 * 校验当前字段是否已存在
	 * @param gczlYdxjGPZLXJInfo 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto, String[] clumNames);
	
	/**
	 * 将实体对象拷贝进dto
	 * @param gczlYdxjGPZLXJInfo
	 * @param gczlYdxjGPZLXJInfoDto
	 */
//	void EntityToDto(GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo,GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto);

	/**
	 * 通过dto新增实体
	 * @param gczlYdxjGPZLXJInfoDto
	 */
	void insertDto(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user);
	
	/**
	 * 上报
	 * @param id
	 */
	void sendUp(String id,User user);
	
	/**
	 * 上报（需填写整改人和整改截止时间）
	 * 2017年2月19日12:11:27
	 * @param user
	 * @param gczlYdxjGPZLXJInfoDto
	 */
	void sendUp(User user, GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto);
	
	
	/**
	 * 审核
	 * @param id
	 */
	String shengHe(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user);
	/**
	 * 整改
	 * @param id
	 */
	String zhengGai(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user);

	/**
	 * 更新实体信息
	 * @param gczlYdxjGPZLXJInfoDto 需更新的实体
	 * @param currentUser 当前登录用户
	 * @return 若为""则数据更新成功， 否则返回错误信息字符串
	 */
	String updateDto(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto, User currentUser);

	/**
	 * 校验当前数据是否过期
	 * @param id
	 * @param time 页面上的修改时间
	 * @return
	 */
	String checkIfguoQi(String id, String time);
	
	/**
	 * 查询当前工程、当前状态有多少条数据
	 * @param proId 工程ID
	 * @param typeName 状态名称： "未上报"、"已上报"、"未审核"、"已打回"、"已审核"、"未整改"、"已整改";<br>
	 * 					若为空则返回0
	 * @return 数据条数
	 */
	int findStateNum(String proId,String typeName);

	
	

}