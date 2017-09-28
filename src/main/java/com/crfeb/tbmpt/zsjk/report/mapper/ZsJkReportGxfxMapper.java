package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportGxfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;

/**
 * <p>安全质量信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkReportGxfxMapper extends CommonMapper<ZsJkReportGxfx>{

    List<ZsJkReportGxfx> selectZsJkReportGxfxList(Page<ZsJkReportGxfx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据所传参数查询数据集合
     * @param condition 参数对象集合
     * @return 返回安全质量信息
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 上午9:24:36
     */
    List<ZsJkReportGxfxDto> selectZsJkReportGxfxListByMap(@Param("condition")Map<String, Object> condition) throws Exception;

}
