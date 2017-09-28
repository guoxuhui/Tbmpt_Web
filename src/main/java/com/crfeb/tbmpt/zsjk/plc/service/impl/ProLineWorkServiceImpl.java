package com.crfeb.tbmpt.zsjk.plc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.zsjk.plc.mapper.ProLineWorkMapper;
import com.crfeb.tbmpt.zsjk.plc.model.ProLineWork;
import com.crfeb.tbmpt.zsjk.plc.service.IProLineWorkService;
import com.baomidou.framework.service.impl.CommonServiceImpl;

@Service
public class ProLineWorkServiceImpl extends CommonServiceImpl<ProLineWorkMapper, ProLineWork> implements IProLineWorkService {

	@Autowired
    private ProLineWorkMapper proLineWorkMapper;

	@Override
	public List<ProLineWork> selectByLineId(String lineId) throws Exception {
		return proLineWorkMapper.selectByLineId(lineId);
	}
}