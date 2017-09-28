package com.crfeb.tbmpt.dgjj.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjj.model.DgjjBzglEmp;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglEmpDto;
import com.crfeb.tbmpt.sys.model.User;

public interface DgjjBzglEmpService extends ICommonService<DgjjBzglEmp>{
	List<DgjjBzglEmpDto> selectDataGrid(String bzId);

}
