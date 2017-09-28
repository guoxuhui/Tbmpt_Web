package com.crfeb.tbmpt.gpztcl.base.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxpcDto;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxpc;

/**
 * <p>实测中线信息管理Service接口</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
public interface GpztclSczxpcService extends ICommonService<GpztclSczxpc>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增实测中线信息管理
     * @param gpztclSczxpc 要保存的实体
     */
    String save(GpztclSczxpcDto gpztclSczxpcDto,User user) throws Exception;

    /**
     * 通过集合删除实测中线信息管理
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改实测中线信息管理
     * @param gpztclSczxpc 要修改的实体
     */
    String update(GpztclSczxpcDto gpztclSczxpcDto,User user) throws Exception;

   /**
     * 通过ID查找实测中线信息管理
     * @param id 数据ID
     */
    GpztclSczxpc findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param gpztclSczxpc 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GpztclSczxpcDto gpztclSczxpcDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：更新上报状态字段
	 * @param idss 更新记录物理主键串,以逗号隔开
	 * @param sbState  更新状态值
	 * @return 若失败则返回错误信息 否则返回空值
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月24日 下午1:28:48
	 */
	public String ifSb(String idss,String sbState) throws Exception;
	
	 /**
     * 方法说明：查询一条dto
     * @param id 主键值
     * @return 返回实测中线一条dto数据对象
     * @throws Exception
     * @author:YangYj
     * @Time: 2016年12月24日 下午9:33:00
     */
    public GpztclSczxpcDto selectDtoById(String id) throws Exception;

}