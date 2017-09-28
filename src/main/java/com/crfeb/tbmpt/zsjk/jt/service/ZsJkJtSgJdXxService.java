package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtSgJdXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtSgJdXx;

/**
 * <p>所有项目总的施工进度信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtSgJdXxService extends ICommonService<ZsJkJtSgJdXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增所有项目总的施工进度信息
     * @param zsJkJtSgJdXx 要保存的实体
     */
    String save(ZsJkJtSgJdXxDto zsJkJtSgJdXxDto,User user) throws Exception;

    /**
     * 通过集合删除所有项目总的施工进度信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改所有项目总的施工进度信息
     * @param zsJkJtSgJdXx 要修改的实体
     */
    String update(ZsJkJtSgJdXxDto zsJkJtSgJdXxDto,User user) throws Exception;

   /**
     * 通过ID查找所有项目总的施工进度信息
     * @param id 数据ID
     */
    ZsJkJtSgJdXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtSgJdXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtSgJdXxDto zsJkJtSgJdXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取所有项目总的施工进度
	 * @param year 年度（不传默认当年）
	 * @return 返回施工进度信息对象
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月9日 下午7:57:58
	 */
	public ZsJkJtSgJdXxDto getAllXMjd(String year) throws Exception;
	
	
	/**
	 * 查询数据表格
	 * @author wl_zjh
	 * @param pageInfo
	 * @throws Exception
	 */
	void selectSgJdXxDataGrid(PageInfo pageInfo) throws Exception;
	
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