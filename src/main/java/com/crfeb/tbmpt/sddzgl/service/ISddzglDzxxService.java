package com.crfeb.tbmpt.sddzgl.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sddzgl.model.SddzglDzxx;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglDzxxDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>地质信息 业务接口 Service</p>
 * <p>模块：隧道地质管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ISddzglDzxxService extends ICommonService<SddzglDzxx>{

	/**
     * 分页查询 easyUi列表
     * @param pageInfo 分页公用类
     */
    void selectDataGrid(PageInfo pageInfo,User user) throws Exception;
    
    /**
     * 新增
     * @param 要保存的实体
     */
    String save(SddzglDzxxDto sddzglDzxxDto,User user) throws Exception;

    
    /**     
     * 修改
     * @param sddzglDzxx 要修改的实体
     */
    String update(SddzglDzxx sddzglDzxx) throws Exception;

    
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
    
    /**
     * @author wl_zjh
     * 查找地层信息
     */
    List<SddzglDzxxDto> selectDzxx();
    
}
