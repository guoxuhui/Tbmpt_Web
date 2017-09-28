package com.crfeb.tbmpt.aqsc.base.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.aqsc.base.model.dto.SpecialManDto;
import com.crfeb.tbmpt.aqsc.base.model.SpecialMan;

/**
 * <p>特种人员管理Service接口</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface SpecialManService extends ICommonService<SpecialMan>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;
    
    void selectDataGrid(PageInfo pageInfo,User user) throws Exception;

    /**
     * 新增特种人员管理
     * @param specialMan 要保存的实体
     */
    String save(SpecialManDto specialManDto,User user) throws Exception;

    /**
     * 通过集合删除特种人员管理
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改特种人员管理
     * @param specialMan 要修改的实体
     */
    String update(SpecialManDto specialManDto,User user) throws Exception;

   /**
     * 通过ID查找特种人员管理
     * @param id 数据ID
     */
    SpecialMan findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param specialMan 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(SpecialManDto specialManDto, String[] clumNames) throws Exception;

	
	/**     
     * 生成 Excel导入 模版
     * @param
     */
    HSSFWorkbook generateExcelTemplate();
    
    /**
     * 把 Excel表格数据集合 转成  对象  并  保存
     * @param list
     * @return
     */
    String transformationBingPreservation(List<List<Map<String, String>>> list,User user);
	
	
}