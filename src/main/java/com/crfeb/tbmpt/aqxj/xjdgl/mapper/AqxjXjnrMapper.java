package com.crfeb.tbmpt.aqxj.xjdgl.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjnr;

/**
 * <p>安全巡检内容Mapper</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：编辑巡检内容</p>
 * <p>日期：2017-05-27</p>
 * @version 1.0
 * @author zhuyabing
 */
public interface AqxjXjnrMapper extends CommonMapper<AqxjXjnr>{

    List<AqxjXjnr> selectAqxjXjnrList(Page<AqxjXjnr> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    List<AqxjXjnr> selectByItemId(@Param("itemId")String itemId);
    
    AqxjXjnr selectByMcAndItemId(@Param("nMingcheng")String nMingcheng,@Param("itemId")String itemId);

}