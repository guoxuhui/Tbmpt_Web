package com.crfeb.tbmpt.open.service;

import java.util.List;

import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.open.model.DicInfo;

/**
 * <p>移动端工程质量巡检模块API数据服务Service接口</p>
 * <p>系统：移动端接口</p>
 * <p>模块：工程质量巡检模块</p>
 * <p>日期：2016-12-05</p>
 * @version 1.0
 * @author smxg
 */
public interface IOpenGczlService {
	
	/**
	 * 保存管片质量上报信息
	 * @param gczlYdxjGPZLXJInfoDto
	 * @return
	 */
	int saveGpzl(GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo);
    
	/**
	 * 管片质量分类字典以及子项字典获取
	 * @return
	 */
    List<DicInfo> getGpzlDDInfo();
    
    /**
     * 管片历史查看
     * @param gcbh 工程编号
     * @param type 状态类型 全部、待上报、待审核、待整改
     * @param page 当前第几页
     * @param rows 每页多少条
     * @return
     */
    Object getGpzlList(String gcbh,String type,int page,int rows);
    
    /**
     * 获取管片质量详细信息
     * 质量详细信息（包括：上报、审核、整改）
     * @param id
     * @return
     */
    GczlYdxjGPZLXJInfoDto getGpzlInfo(String id);
    
	/**
	 * 保存施工质量上报信息
	 * @param gczlYdxjSGZLXJInfoDto
	 * @return
	 */
	int saveSgzl(GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfo);
	
	/**
	 * 获取全部施工内容字典（一个主表两个子表）
	 * @return
	 */
    List<DicInfo> getSgzlDDInfo();
    
    /**
     * 施工历史查看
     * @param gcbh 工程编号
     * @param type 状态类型 全部、待上报、待审核、待整改
     * @param page 当前第几页
     * @param rows 每页多少条
     * @return
     */
    Object getSgzlList(String gcbh,String type,int page,int rows);
    
    /**
     * 获取施工质量详细信息
     * 质量详细信息（包括：上报、审核、整改）
     * @param id
     * @return
     */
    GczlYdxjSGZLXJInfoDto getSgzlInfo(String id);
    
    /**
     * 获取工程质量消息--个推消息列表
     * @param page当前多少页
     * @param rows 每页多少条
     * @return
     */
    Object getGczlMessageList (int page,int rows,String userId);	
}