package com.crfeb.tbmpt.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoParentDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;

/**
 *
 * ProRSectionLine 表数据库控制层接口
 *
 */
public interface ProRSectionLineMapper extends CommonMapper<ProRSectionLine> {

	ProRSectionLine selectByLineId(@Param("id") String id);
	
	int deleteByLineId(@Param("id") String id);
	
	/**
	 * 获取所有线路信息
	 * @return List
	 */
	List<ProRSectionLine> getLineList();
	
	/**
	 * 根据区间查询线路信息
	 * @param proId
	 * @return
	 */
	List<ProRSectionLine> getLineBySectionId(@Param("sectionId") String sectionId);
	
	/***
	 * wpg
	 * 根据区间 、线路 名称  查询线路Id
	 * @param sectionId 区间Id
	 * @param lineName 线路名称
	 * @return
	 */
	String getLineBySectionIdLineName(@Param("sectionId") String sectionId,@Param("lineName") String lineName);
	
	/**
	 * 查询线路信息列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<SectionLineVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);

	/**
	 * 
	 * @param page
	 * @param condition
	 * @return
	 */
	List<ProRSectionLine> selectGpztclSjzxInfoParentDtoList(Page<GpztclSjzxInfoParentDto> page,Map<String, Object> condition);
	
	/**
	 * 通过id查找线路信息
	 */
	List<SectionLineVo> selectByXlId(@Param("xlId")String xlId);

	//根据接口查询用户拥有的项目的线路
	List<SectionLineVo> selectVoList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
}