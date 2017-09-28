package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtXmxxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtXmxx;

/**
 * <p>展示接口集团角度项目信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtXmxxService extends ICommonService<ZsJkJtXmxx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增展示接口集团角度项目信息
     * @param zsJkJtXmxx 要保存的实体
     */
    String save(ZsJkJtXmxxDto zsJkJtXmxxDto,User user) throws Exception;

    /**
     * 通过集合删除展示接口集团角度项目信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改展示接口集团角度项目信息
     * @param zsJkJtXmxx 要修改的实体
     */
    String update(ZsJkJtXmxxDto zsJkJtXmxxDto,User user) throws Exception;

    /**     
     * 修改展示接口集团角度项目信息
     * @param zsJkJtXmxx 要修改的实体
     */
    String updateXmxx(ZsJkJtXmxx zsJkJtXmxx) throws Exception;

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
     * 通过ID查找展示接口集团角度项目信息
     * @param id 数据ID
     */
    ZsJkJtXmxx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtXmxx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtXmxxDto zsJkJtXmxxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取项目信息列表
	 * @return 返回所有项目信息列表集合
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月9日 下午7:13:41
	 */
	public List<ZsJkJtXmxxDto> getXMXXList() throws Exception;

}