package com.crfeb.tbmpt.project.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProProjectjindu;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduQueryVo;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduVo;
import com.crfeb.tbmpt.sys.model.User;

public interface IProProjectjinduService extends ICommonService<ProProjectjindu>{

	void selectDataGrid(PageInfo pageInfo,User user);

	List<ProProjectjinduQueryVo> selectaddDetails(String rq, String proId);

	void deleteByTime(String time);

}
