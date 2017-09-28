package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlXx;

/**
 * <p>安全质量信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtAqZlXxService extends ICommonService<ZsJkJtAqZlXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增安全质量信息
     * @param zsJkJtAqZlXx 要保存的实体
     */
    String save(ZsJkJtAqZlXxDto zsJkJtAqZlXxDto,User user) throws Exception;

    /**
     * 通过集合删除安全质量信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改安全质量信息
     * @param zsJkJtAqZlXx 要修改的实体
     */
    String update(ZsJkJtAqZlXxDto zsJkJtAqZlXxDto,User user) throws Exception;

   /**
     * 通过ID查找安全质量信息
     * @param id 数据ID
     */
    ZsJkJtAqZlXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtAqZlXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtAqZlXxDto zsJkJtAqZlXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取指定时间段内上报上来的重大安全、质量、隐患数，用于展现其发展趋势
	 * @param strTime 起始时间
	 * @param endTime 截止时间
	 * @return 返回指定时间段内上报上来的重大安全、质量、隐患数等信息集合
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午8:36:05
	 */
	public List<ZsJkJtAqZlXxDto> getAQZL(String strTime,String endTime) throws Exception;
	
	/**
	 * 查询数据表格
	 * @param pageInfo
	 * @throws Exception
	 */
	void selectAqZlXxDataGrid(PageInfo pageInfo) throws Exception;
	
	/**
	 * 生成excel模板
	 * @author wl_zjh
	 * @return
	 */
	HSSFWorkbook generateExcelTemplate();
	
	
	/**
	 * 将集合转换成对象
	 * @author wl_zjh
	 * @param list
	 * @return
	 */
	String transformationBingPreservation(List<List<Map<String, String>>> list);

}