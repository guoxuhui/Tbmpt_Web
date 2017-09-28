package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtZySbTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtZySbTrXx;

/**
 * <p>集团角度 主要设备投入情况信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtZySbTrXxService extends ICommonService<ZsJkJtZySbTrXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增集团角度 主要设备投入情况信息
     * @param zsJkJtZySbTrXx 要保存的实体
     */
    String save(ZsJkJtZySbTrXxDto zsJkJtZySbTrXxDto,User user) throws Exception;

    /**
     * 通过集合删除集团角度 主要设备投入情况信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改集团角度 主要设备投入情况信息
     * @param zsJkJtZySbTrXx 要修改的实体
     */
    String update(ZsJkJtZySbTrXxDto zsJkJtZySbTrXxDto,User user) throws Exception;

    /**     
     * 修改集团角度 主要设备投入情况信息
     * @param zsJkJtZySbTrXx 要修改的实体
     */
    String updateSbTrXx(ZsJkJtZySbTrXx zsJkJtZySbTrXx) throws Exception;

   /**
     * 通过ID查找集团角度 主要设备投入情况信息
     * @param id 数据ID
     */
    ZsJkJtZySbTrXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtZySbTrXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtZySbTrXxDto zsJkJtZySbTrXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取盾构机、电瓶车、龙门吊等主要设备的运行与投入情况
	 * @return 盾构机数量、电瓶车数量、龙门吊（租入、租出、检修、运行、运输）（以分类进行分组，每组传5个状态的数量值）
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午9:08:17
	 */
	public Map<String, HashMap<String, String>> getZysbtr() throws Exception;

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