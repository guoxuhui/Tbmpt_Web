package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmLjCzMxXx;

/**
 * <p>项目部角度  当前项目的累计产值明细信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbXmLjCzMxXxMapper extends CommonMapper<ZsJkXmbXmLjCzMxXx>{

    List<ZsJkXmbXmLjCzMxXx> selectZsJkXmbXmLjCzMxXxList(Page<ZsJkXmbXmLjCzMxXx> page,@Param("condition")Map<String, Object> condition) throws Exception;

}