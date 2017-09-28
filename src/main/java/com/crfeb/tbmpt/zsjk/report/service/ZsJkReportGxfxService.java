package com.crfeb.tbmpt.zsjk.report.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportGxfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;


public interface ZsJkReportGxfxService extends ICommonService<ZsJkReportGxfx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增安全质量信息
     * @param zsJkJtAqZlXx 要保存的实体
     */
    String save(ZsJkReportGxfxDto zsJkReportGxfxDto,User user) throws Exception;

    /**
     * 通过集合删除安全质量信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改安全质量信息
     * @param zsJkJtAqZlXx 要修改的实体
     */
    String update(ZsJkReportGxfxDto zsJkReportGxfxDto,User user) throws Exception;

   /**
     * 通过ID查找安全质量信息
     * @param id 数据ID
     */
    ZsJkReportGxfx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkJtAqZlXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkReportGxfxDto zsJkReportGxfx, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取指定时间段内上报上来的重大安全、质量、隐患数，用于展现其发展趋势
	 * @param strTime 起始时间
	 * @param endTime 截止时间
	 * @return 返回指定时间段内上报上来的重大安全、质量、隐患数等信息集合
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午8:36:05
	 */
	public List<ZsJkReportGxfxDto> gxfx(String kssj, String jssj,String kshh,String jshh,String proId,String lineId) throws Exception;

}
