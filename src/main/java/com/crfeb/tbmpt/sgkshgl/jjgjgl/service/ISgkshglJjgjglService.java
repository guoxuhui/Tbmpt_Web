package com.crfeb.tbmpt.sgkshgl.jjgjgl.service;



import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.SgkshglJjgjgl;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.dto.SgkshglJjgjglDto;


import java.util.List;
import java.util.concurrent.CompletionService;

import com.baomidou.framework.service.ICommonService;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * ProGeoLines 表数据服务层接口
 *
 */
public interface ISgkshglJjgjglService extends ICommonService<SgkshglJjgjgl>{

	List<SgkshglJjgjglDto> selectBylineId(String lineId);

	String saveAll(String points, String lineId);


}