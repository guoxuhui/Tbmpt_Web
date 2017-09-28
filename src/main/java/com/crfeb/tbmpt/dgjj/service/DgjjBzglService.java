package com.crfeb.tbmpt.dgjj.service;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjj.model.DgjjBzgl;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.sys.model.User;

public interface DgjjBzglService extends ICommonService<DgjjBzgl>{
	void selectDataGrid(PageInfo pageInfo,User user);

}
