package com.crfeb.tbmpt.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.crfeb.tbmpt.project.model.ProProjectjindu;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduQueryVo;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduVo;

public interface ProProjectjinduMapper extends CommonMapper<ProProjectjindu> {

//	List<ProProjectjinduVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
//
//	List<ProProjectjindu> getLineBySectionId(@Param("sectionId") String sectionId);
	
	List<ProProjectjinduVo> selectInfoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	//调用接口获取用户所拥有的项目信息
	List<ProProjectjinduVo> selectInfoList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);

	List<ProProjectjinduQueryVo> selectDetails(@Param("rq")String rq, @Param("proId")String proId);

	void deleteByTime(@Param("time")String time);

}
