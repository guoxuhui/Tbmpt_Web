package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXlJdXx;

/**
 * <p>项目部角度 项目各线路进度信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbXlJdXxMapper extends CommonMapper<ZsJkXmbXlJdXx>{

    List<ZsJkXmbXlJdXx> selectZsJkXmbXlJdXxList(Page<ZsJkXmbXlJdXx> page,@Param("condition")Map<String, Object> condition) throws Exception;

}