package com.crfeb.tbmpt.zsjk.jt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtLjCzXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtLjCzXx;

/**
 * <p>当前所有项目总累计产值信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtLjCzXxService extends ICommonService<ZsJkJtLjCzXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增当前所有项目总累计产值信息
     * @param zsJkJtLjCzXx 要保存的实体
     */
    String save(ZsJkJtLjCzXxDto zsJkJtLjCzXxDto,User user) throws Exception;

    /**
     * 通过集合删除当前所有项目总累计产值信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改当前所有项目总累计产值信息
     * @param zsJkJtLjCzXx 要修改的实体
     */
    String update(ZsJkJtLjCzXxDto zsJkJtLjCzXxDto,User user) throws Exception;

   /**
     * 通过ID查找当前所有项目总累计产值信息
     * @param id 数据ID
     */
    ZsJkJtLjCzXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtLjCzXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkJtLjCzXxDto zsJkJtLjCzXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取所有项目总累计产值
	 * @param strTime 起始时间
	 * @param endTime 截止时间
	 * 注意：（起止时间不传，则查出所有;否则按时间区间查询）
	 * @return  返回对象信息
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月9日 下午6:45:40
	 */
	public ZsJkJtLjCzXxDto getAllXmljcz(String strTime,String endTime) throws Exception;;
	
	/**
	 * 查询数据表格
	 * @author wl_zjh
	 * @param pageInfo
	 * @throws Exception
	 */
	void selectLjCzXxDataGrid(PageInfo pageInfo) throws Exception;
	
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