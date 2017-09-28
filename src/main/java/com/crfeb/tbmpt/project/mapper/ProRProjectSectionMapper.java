package com.crfeb.tbmpt.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.vo.ProjectSectionVo;

/**
 *
 * ProRProjectSection 表数据库控制层接口
 *
 */
public interface ProRProjectSectionMapper extends CommonMapper<ProRProjectSection> {

	ProRProjectSection selectBySectionId(@Param("id") String id);
	
	int deleteBySectionId(@Param("id") String id);
	/**
	 * 根据项目查询区间信息
	 * @param proId
	 * @return
	 */
	List<ProRProjectSection> getSectionByProId(@Param("proId") String proId);
	
	
	/**
	 * 查询区间信息列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<ProjectSectionVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/***
	 * wpg
	 * 批量添加点号时 根据区间名称获取区间信息
	 * @param sectionName 区间名称
	 * @return
	 */
	String getSectionBySectionName(@Param("sectionName") String sectionName);
	
	/**
	 * 根据接口查询用户拥有哪些项目的区间
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<ProjectSectionVo> selectVoList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);

}