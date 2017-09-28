package com.crfeb.tbmpt.dgjjdw.real.service;


import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjjdw.real.model.DgjjdwPlcReal;

/**
 * <p>盾构掘进点位管理：盾构机机器数据实时信息管理 Service</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface IDgjjdwPlcRealService extends ICommonService<DgjjdwPlcReal> {



   /**
    * 查询 盾构机机器数据实时信息
    * @param pageInfo 分页公用类
    */
   void selectDataGrid(PageInfo pageInfo) throws Exception;

  
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