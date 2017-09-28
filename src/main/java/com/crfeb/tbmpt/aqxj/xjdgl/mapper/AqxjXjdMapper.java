package com.crfeb.tbmpt.aqxj.xjdgl.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjd;

/**
 * <p>安全巡检点Mapper</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：巡检点管理</p>
 * <p>日期：2017-05-26</p>
 * @version 1.0
 * @author zhuyabing
 */
public interface AqxjXjdMapper extends CommonMapper<AqxjXjd>{

    List<AqxjXjd> selectAqxjXjdList(Page<AqxjXjd> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    List<AqxjXjd> getAll();
    
    List<AqxjXjd> selectByProName(@Param("proName") String proName);
    
    AqxjXjd selectByGcAndMc(@Param("mingCheng") String mingCheng,@Param("proName") String proName);
    
    AqxjXjd selectByMengcheng(@Param("mingCheng") String mc);
    
    List<AqxjXjd> selectByTypeId(@Param("currId") String currId);
    
    AqxjXjd selectByTypIdAndMc(@Param("newId") String newId,@Param("mc") String mc);
    
}