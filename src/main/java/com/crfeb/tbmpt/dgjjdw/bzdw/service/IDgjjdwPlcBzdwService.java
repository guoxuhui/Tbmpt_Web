package com.crfeb.tbmpt.dgjjdw.bzdw.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjjdw.bzdw.model.DgjjdwPlcBzdw;
import com.crfeb.tbmpt.dgjjdw.bzdw.model.dto.DgjjdwPlcBzdwDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>盾构掘进点位管理：标准点位字典信息管理 Service接口</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface IDgjjdwPlcBzdwService extends ICommonService<DgjjdwPlcBzdw> {


	 /**
     * 查询 标准点位字典信息
     * @param pageInfo 分页公用类
     */
    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增 标准点位字典信息
     * @param dgjjdwPlcBzdwDto 要保存的实体
     */
    String save(DgjjdwPlcBzdwDto dgjjdwPlcBzdwDto,User user) throws Exception;

    /**
     * 通过集合删除 标准点位字典信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(List<String> idList) throws Exception;

   
    /**     
     * 修改 标准点位字典信息
     * @param dgjjdwPlcBzdw 要修改的实体
     */
    String update(DgjjdwPlcBzdw dgjjdwPlcBzdw) throws Exception;

    
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
    String transformationBingPreservation(List<List<Map<String, String>>> list);
    
    
    

}