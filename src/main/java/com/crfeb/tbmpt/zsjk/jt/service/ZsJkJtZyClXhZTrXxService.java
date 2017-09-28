package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtZyClXhZTrXx;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtZyClXhZTrXxDto;

/**
 * <p>集团角度主要材料消耗总投入信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtZyClXhZTrXxService extends ICommonService<ZsJkJtZyClXhZTrXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增集团角度主要材料消耗总投入信息
     * @param zsJkJtZyClXhZTrXx 要保存的实体
     */
    String save(ZsJkJtZyClXhZTrXxDto zsJkJtZyClXhZTrXxDto,User user) throws Exception;

    /**
     * 通过集合删除集团角度主要材料消耗总投入信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改集团角度主要材料消耗总投入信息
     * @param zsJkJtZyClXhZTrXx 要修改的实体
     */
    String update(ZsJkJtZyClXhZTrXxDto zsJkJtZyClXhZTrXxDto,User user) throws Exception;

    /**     
     * 修改集团角度主要材料消耗总投入信息
     * @param zsJkJtZyClXhZTrXx 要修改的实体
     */
    String updateZyClXhZTrXx(ZsJkJtZyClXhZTrXx zsJkJtZySbTrXx) throws Exception;

   /**
     * 通过ID查找集团角度主要材料消耗总投入信息
     * @param id 数据ID
     */
    ZsJkJtZyClXhZTrXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtZyClXhZTrXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtZyClXhZTrXxDto zsJkJtZyClXhZTrXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取本年度所有项目的材料消耗总投入
	 * @param nd 年度（不传默认当年）
	 * @return  返回年度所有项目的材料消耗总投入
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午8:59:29
	 */
	public ZsJkJtZyClXhZTrXxDto getclxhztr(String nd) throws Exception;
	
	
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