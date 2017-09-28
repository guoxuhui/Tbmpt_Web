package com.crfeb.tbmpt.dgjj.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjj.mapper.DgjjBzglEmpMapper;
import com.crfeb.tbmpt.dgjj.mapper.DgjjBzglMapper;
import com.crfeb.tbmpt.dgjj.model.DgjjBzglEmp;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglEmpDto;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglEmpService;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglService;

@Service
public class DgjjBzglEmpServiceImp extends CommonServiceImpl<DgjjBzglEmpMapper, DgjjBzglEmp> implements DgjjBzglEmpService  {
	
	@Autowired
    private DgjjBzglEmpMapper bzglEmpMapper;
	
	/**
	 * 员工数据表格查询方法
	 */
	@Override
	public List<DgjjBzglEmpDto> selectDataGrid(String bzId) {
		
        List<DgjjBzglEmpDto> list = bzglEmpMapper.selectVoList(bzId);
        
		return list;
	}

}
