package com.crfeb.tbmpt.project.mapper;

import com.crfeb.tbmpt.project.model.ProPqInfo;
import com.crfeb.tbmpt.project.model.vo.ProPqInfoVo;
import com.crfeb.tbmpt.project.model.vo.ProjectinfoVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPqInfo 表数据库控制层接口
 *
 */
public interface ProPqInfoMapper extends CommonMapper<ProPqInfo> {

	List<ProPqInfoVo> selectVoList(Page<ProPqInfoVo> page,@Param("params") Map<String, Object> params, @Param("sort") String sort,
			@Param("order")String order);

	List<ProPqInfoVo> selectByName();


}