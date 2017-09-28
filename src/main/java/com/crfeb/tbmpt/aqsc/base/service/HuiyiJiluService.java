package com.crfeb.tbmpt.aqsc.base.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.aqsc.base.model.dto.HuiyiJiluDto;
import com.crfeb.tbmpt.aqsc.base.model.HuiyiJilu;

/**
 * <p>培训记录Service接口</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-21</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface HuiyiJiluService extends ICommonService<HuiyiJilu>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增培训记录
     * @param huiyiJilu 要保存的实体
     */
    String save(HuiyiJiluDto huiyiJiluDto,User user) throws Exception;

    /**
     * 通过集合删除培训记录
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改培训记录
     * @param huiyiJilu 要修改的实体
     */
    String update(HuiyiJiluDto huiyiJiluDto,User user) throws Exception;

   /**
     * 通过ID查找培训记录
     * @param id 数据ID
     */
    HuiyiJilu findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param huiyiJilu 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(HuiyiJiluDto huiyiJiluDto, String[] clumNames) throws Exception;

}