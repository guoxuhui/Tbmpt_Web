package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtZyClXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtDjXhZTrXx;

/**
 * <p>集团角度刀具消耗总投入信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtDjXhZTrXxService extends ICommonService<ZsJkJtDjXhZTrXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增集团角度刀具消耗总投入信息
     * @param zsJkJtDjXhZTrXx 要保存的实体
     */
    String save(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto,User user) throws Exception;

    /**
     * 通过集合删除集团角度刀具消耗总投入信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改集团角度刀具消耗总投入信息
     * @param zsJkJtDjXhZTrXx 要修改的实体
     */
    String update(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto,User user) throws Exception;

   /**
     * 通过ID查找集团角度刀具消耗总投入信息
     * @param id 数据ID
     */
    ZsJkJtDjXhZTrXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtDjXhZTrXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto, String[] clumNames) throws Exception;
	
	
	/**
	 * 方法说明：获取本年度刀具的材料消耗总投入
	 * @param nd 年度（不传默认当年）
	 * @return 返回年度刀具的材料消耗总投入
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午9:01:06
	 */
	public ZsJkJtDjXhZTrXxDto djxhztr(String nd) throws Exception;
	
	/**
	 * 查询数据表格
	 * @author wl_zjh
	 * @param pageInfo
	 * @throws Exception
	 */
	void selectdjXhZTrXxDataGrid(PageInfo pageInfo) throws Exception;
	
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