package com.crfeb.tbmpt.gpztcl.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclQxys;
/**
 * <p>设置曲线要素 Mapper</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：线路中心线信息管理模块</p>
 * <p>日期：2016-12-26</p>
 * @version 1.0
 * @author wpg
 */
public interface GpztclQxysMapper extends CommonMapper<GpztclQxys>{

  
    /**
	 * @author wpg 
     * 作用：根据线路编号查询“曲线要素”信息；
     * @param xlbh
     * @return List<GpztclQxys>
     * @throws Exception
     */
    List<GpztclQxys> selectQxysListByXlbh(@Param("xlbh")String xlbh);
}