package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlMxXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlMxXx;

/**
 * <p>集团角度安全质量明细信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtAqZlMxXxService extends ICommonService<ZsJkJtAqZlMxXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增集团角度安全质量明细信息
     * @param zsJkJtAqZlMxXx 要保存的实体
     */
    String save(ZsJkJtAqZlMxXxDto zsJkJtAqZlMxXxDto,User user) throws Exception;

    /**
     * 通过集合删除集团角度安全质量明细信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改集团角度安全质量明细信息
     * @param zsJkJtAqZlMxXx 要修改的实体
     */
    String update(ZsJkJtAqZlMxXxDto zsJkJtAqZlMxXxDto,User user) throws Exception;

   /**
     * 通过ID查找集团角度安全质量明细信息
     * @param id 数据ID
     */
    ZsJkJtAqZlMxXx findById(String id) throws Exception;
	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtAqZlMxXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtAqZlMxXxDto zsJkJtAqZlMxXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取安全、质量、隐患各自的明细数据
	 * @param strTime 开始时间
	 * @param endTime 结束时间
	 * @param xxfl 信息分类（安全、质量、隐患）
	 * @return 返回安全、质量、隐患各自的明细数据
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午8:39:56
	 */
	public List<ZsJkJtAqZlMxXxDto> getAQZLMX(String strTime,String endTime,String xxfl) throws Exception;

	HSSFWorkbook generateExcelTemplate();

	String transformationBingPreservation(List<List<Map<String, String>>> list);

}