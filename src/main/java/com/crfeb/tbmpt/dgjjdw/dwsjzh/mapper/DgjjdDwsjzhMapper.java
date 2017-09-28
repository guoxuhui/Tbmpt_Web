package com.crfeb.tbmpt.dgjjdw.dwsjzh.mapper;

import com.crfeb.tbmpt.dgjjdw.dwsjzh.model.DgjjdDwsjzh; 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param; 
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcTbmdw 表数据库控制层接口
 *
 */
public interface DgjjdDwsjzhMapper extends CommonMapper<DgjjdDwsjzh> {

	List<DgjjdDwsjzh> selectDataGridList(Page<DgjjdDwsjzh> page, @Param("condition")Map<String, Object> condition,@Param("sort") String sort, @Param("order") String order);
	List<DgjjdDwsjzh> selectList(@Param("dgjNameT")String dgjName,@Param("dwNameT")String dwName);
 
}