package com.crfeb.tbmpt.aqsc.base.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqsc.base.model.HuiyiJilu;

/**
 * <p>培训记录Mapper</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-21</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface HuiyiJiluMapper extends CommonMapper<HuiyiJilu>{

    List<HuiyiJilu> selectHuiyiJiluList(Page<HuiyiJilu> page,@Param("condition")Map<String, Object> condition, @Param("sort") String sort, @Param("order") String order) throws Exception;

}