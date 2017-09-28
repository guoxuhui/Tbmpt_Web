package com.crfeb.tbmpt.gpztcl.base.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclQxys;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;

/**
 * <p>设置曲线要素 Service接口</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：线路中心线信息管理模块</p>
 * <p>日期：2016-12-26</p>
 * @version 1.0
 * @author wpg
 */
public interface GpztclQxysService extends ICommonService<GpztclQxys>{

	/**
	 * 通过id查找线路信息    wpg
	 */
	SectionLineVo selectSectionLineVoByXlId(String xlId);
	/**
	 * @author wpg 
     * 作用：根据线路编号查询“曲线要素”信息；
     * @param xlbh
     * @return List<GpztclQxys>
     * @throws Exception
     */
	public List<GpztclQxys> selectByFIdQxysList(String xlbh);
	
	
	
}