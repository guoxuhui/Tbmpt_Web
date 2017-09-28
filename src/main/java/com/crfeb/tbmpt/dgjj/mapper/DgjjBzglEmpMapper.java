package com.crfeb.tbmpt.dgjj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.dgjj.model.DgjjBzglEmp;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglEmpDto;

public interface DgjjBzglEmpMapper extends CommonMapper<DgjjBzglEmp>{
	
	/**
	 * 员工数据表格查询方法
	 * @param bzId
	 * @return
	 */
	List<DgjjBzglEmpDto> selectVoList(@Param("bzId")String bzId);

}
