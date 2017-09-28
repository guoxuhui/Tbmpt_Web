package com.crfeb.tbmpt.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.vo.ProjectinfoVo;

/**
 *
 * ProProjectinfo 表数据库控制层接口
 *
 */
public interface ProProjectinfoMapper extends CommonMapper<ProProjectinfo> {

	void insertPro(ProProjectinfo pro);
	
	List<ProProjectinfo> selectList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);

	List<ProjectinfoVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	//根据接口查询用户拥有哪些项目
	List<ProjectinfoVo> selectVoList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	ProProjectinfo selectByProId(@Param("id") String id);
	
	int deleteByProId(@Param("id") String id);
	
	List<ProProjectinfo> selectListByOrgzCode(@Param("code") String code);
	
	/**
	 * 根据用户ID，查找其所能查看的项目信息
	 * 1、当前用户只能看到其所在组织机构或父机构为项目部的项目信息
	 * 2、当前用户可以看到其所在组织机构下有项目部的所有项目信息
	 * @param userId 用户ID
	 * @return 返回用户所能看到的项目信息
	 */
	List<ProProjectinfo> selectByUserId(@Param("userId") String userId);

	List<ProProjectinfo> selectAllByNull();
	
	/**
	 * 根据项目名称查找项目
	 */
	String selectByProName(@Param("proName")String proName);
	
	/**
	 *  获取所有项目
	 * @return
	 */
	List<ProProjectinfo> getProjectInfosBylist();
}