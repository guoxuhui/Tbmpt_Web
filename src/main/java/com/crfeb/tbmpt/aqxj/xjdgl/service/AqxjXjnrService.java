package com.crfeb.tbmpt.aqxj.xjdgl.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjnr;
import com.crfeb.tbmpt.aqxj.xjdgl.model.dto.AqxjXjnrDto;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>安全巡检内容Service接口</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：编辑巡检内容</p>
 * <p>日期：2017-05-27</p>
 * @version 1.0
 * @author zhuyabing
 */
public interface AqxjXjnrService extends ICommonService<AqxjXjnr>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;
    
    Object copyAqxjxjnr(String currentProName,String proName);
    
    Object copyFlAqxjxjnr(String id, String currId,String currproName);

    /**
     * 新增安全巡检内容
     * @param aqxjXjnr 要保存的实体
     */
    String save(AqxjXjnrDto aqxjXjnrDto,User user) throws Exception;

    /**
     * 通过集合删除安全巡检内容
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改安全巡检内容
     * @param aqxjXjnr 要修改的实体
     */
    String update(AqxjXjnrDto aqxjXjnrDto,User user) throws Exception;

   /**
     * 通过ID查找安全巡检内容
     * @param id 数据ID
     */
    AqxjXjnr findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param aqxjXjnr 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(AqxjXjnrDto aqxjXjnrDto, String[] clumNames) throws Exception;
	
	/**
	 * 根据巡检点查询巡检内容
	 * */
	String selectXjnrByItems(String[] ids);
	
	//【复制巡检点】巡检内容复制
	String copyAqxjxjdXjnr(List<String> idlist,String currId);
	

}