package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtdgSgRyTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtdgSgRyTrXx;

/**
 * <p>集团角度项目的盾构施工人员投入信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtdgSgRyTrXxService extends ICommonService<ZsJkJtdgSgRyTrXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增集团角度项目的盾构施工人员投入信息
     * @param zsJkJtdgSgRyTrXx 要保存的实体
     */
    String save(ZsJkJtdgSgRyTrXxDto zsJkJtdgSgRyTrXxDto,User user) throws Exception;

    /**
     * 通过集合删除集团角度项目的盾构施工人员投入信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改集团角度项目的盾构施工人员投入信息
     * @param zsJkJtdgSgRyTrXx 要修改的实体
     */
    String update(ZsJkJtdgSgRyTrXxDto zsJkJtdgSgRyTrXxDto,User user) throws Exception;

   /**
     * 通过ID查找集团角度项目的盾构施工人员投入信息
     * @param id 数据ID
     */
    ZsJkJtdgSgRyTrXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtdgSgRyTrXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtdgSgRyTrXxDto zsJkJtdgSgRyTrXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取所有项目的盾构施工人员投入
	 * @return 返回所有项目的盾构施工人员投入情况信息
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午9:03:48
	 */
	public ZsJkJtdgSgRyTrXxDto getAllxmrytr() throws Exception;
	
	/**
	 * 查询数据表格
	 * @author wl_zjh
	 * @param pageInfo
	 * @throws Exception
	 */
	void selectdgSgRyTrXxDataGrid(PageInfo pageInfo) throws Exception;
	
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