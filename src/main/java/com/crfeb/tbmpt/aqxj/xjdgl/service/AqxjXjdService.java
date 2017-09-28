package com.crfeb.tbmpt.aqxj.xjdgl.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjd;
import com.crfeb.tbmpt.aqxj.xjdgl.model.dto.AqxjXjdDto;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>安全巡检点Service接口</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：巡检点管理</p>
 * <p>日期：2017-05-26</p>
 * @version 1.0
 * @author zhuyabing
 */
public interface AqxjXjdService extends ICommonService<AqxjXjd>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;
    
    List<AqxjXjd> getAqxjXjd();

    /**
     * 新增安全巡检点
     * @param aqxjXjd 要保存的实体
     */
    String save(AqxjXjdDto aqxjXjdDto,User user) throws Exception;

    /**
     * 通过集合删除安全巡检点
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;
    
    
    Object copyFlXjd(String id,String currId,String currProName,String fengL);

    /**     * 修改安全巡检点
     * @param aqxjXjd 要修改的实体
     */
    String update(AqxjXjdDto aqxjXjdDto,User user) throws Exception;
    
    /*
     * 复制巡检点
     * */
    Object copyAqxjxjd(String currentProName,String proName);
    
   /**
     * 通过ID查找安全巡检点
     * @param id 数据ID
     */
    AqxjXjd findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param aqxjXjd 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(AqxjXjdDto aqxjXjdDto, String[] clumNames) throws Exception;
	
	/**
	 * 【巡检点复制】复制巡检点
	 * */
	String copyAqxjxjdXjd(List<String> list,String currid,String currProName,String currFengL);
	
	
	
	
}