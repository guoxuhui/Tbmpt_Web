/**
 * 
 */
package com.crfeb.tbmpt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.sys.model.Region;

/**
 * @author Administrator
 *
 */
public interface RegionMapper extends CommonMapper<Region> {
 
	
	/**
	 * 2查询 最外层  的地区
	 * 查询 父级id 为空  的 地区
	 * @return
	 */
	List<Region> selectByPIdNull();
	
	/***
	 * 3根据 父级 查询  子地区信息
	 */
	List<Region> selectAllByPId(@Param("pId") String pid);
	
	
	/**
	 * 4根据 编号 查询地区 树形集合
	 */
	List<Region> selectAllBycode(String code);
	
	/***
	 * 5查询 所有 地区 
	 */
	List<Region> selectAll();
	
	
}
