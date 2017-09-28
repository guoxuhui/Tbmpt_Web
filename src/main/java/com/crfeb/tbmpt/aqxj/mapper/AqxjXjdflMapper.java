package com.crfeb.tbmpt.aqxj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.sys.model.Orgz;




public interface AqxjXjdflMapper extends CommonMapper<AqxjXjdfl>{

	 List<AqxjXjdfl> selectAqxjXjdflList(Page<AqxjXjdfl> page,@Param("condition")Map<String, Object> condition);
	 
	 List<AqxjXjdfl> selectAll(@Param("condition")Map<String, Object> condition);
	 


		/**
		 * 2查询 最外层  的地区
		 * 查询 父级id 为空  的 地区
		 * @return
		 */
		List<AqxjXjdfl> selectNull();
		
		
		List<AqxjXjdfl> selectAllFlmc();
		
		
		List<AqxjXjdfl> flmcList(@Param("gcmc") String gcmc);
		
		List<AqxjXjdfl> flList(@Param("proName") String proName);
		
		
		AqxjXjdfl selectFather(@Param("fenleiMc") String fenleiMc, @Param("proName") String proName);
		
		
		List<AqxjXjdfl> selectFlNullPid(@Param("flMc") String fenleiMc, @Param("currentProName") String proName);
		
		AqxjXjdfl selectFlByNullPid(@Param("flMc") String fenleiMc, @Param("currentProName") String proName);
		
		AqxjXjdfl seledtByPidAndMc(@Param("currPid") String currPid, @Param("currFengL") String currFengL);
		
		/***
		 * 3根据 父级 查询  子地区信息
		 */
		List<AqxjXjdfl> selectAllByPId(@Param("id") String pid);
		
		
		List<AqxjXjdfl> selectByGcMc(@Param("gcmc") String gcmc);
	
}
