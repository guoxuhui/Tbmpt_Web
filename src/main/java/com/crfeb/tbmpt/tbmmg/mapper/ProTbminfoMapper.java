package com.crfeb.tbmpt.tbmmg.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;

/**
 *
 * ProTbminfo 表数据库控制层接口
 *
 */
public interface ProTbminfoMapper extends CommonMapper<ProTbminfo> {

	/**
	 * 分页查询盾构机信息
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<ProTbminfo> selectList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 获取全部盾构机信息
	 * @return
	 */
	List<ProTbminfo> selectAllList();
	
	/**
	 * 根据盾构机名称查询盾构机
	 */
	ProTbminfo selectByName(@Param("tbmname")String tbmname);
}