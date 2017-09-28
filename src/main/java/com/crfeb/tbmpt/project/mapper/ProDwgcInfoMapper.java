package com.crfeb.tbmpt.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.project.model.ProDwgcInfo;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.vo.ProDwgcVo;
import com.crfeb.tbmpt.project.model.vo.ProFbgcVo;

public interface ProDwgcInfoMapper extends CommonMapper<ProDwgcInfo>{

	List<ProDwgcVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	//调用接口查找用户拥有的项目权限
	List<ProDwgcVo> selectVoList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
}
