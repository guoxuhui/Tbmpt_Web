package com.crfeb.tbmpt.tbmmg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sddzgl.mapper.SddzglDzxxMapper;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglDzxxDto;
import com.crfeb.tbmpt.sddzgl.service.ISddzglDzxxService;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbminfoMapper;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;

/**
 *
 * ProTbminfo 表数据服务层接口实现类
 *
 */
@Service
public class ProTbminfoServiceImpl extends CommonServiceImpl<ProTbminfoMapper, ProTbminfo> implements IProTbminfoService {

    @Autowired
    private ProTbminfoMapper proTbminfoMapper;
    @Autowired
    private SddzglDzxxMapper sddzglDzxxMapper;
    
	public void selectDataGrid(PageInfo pageInfo) {
        Page<ProTbminfo> page = new Page<ProTbminfo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProTbminfo> list = proTbminfoMapper.selectList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	/**
	 * 获取全部盾构机信息
	 * @return
	 */
	public List<ProTbminfo> selectAllList(){
		return proTbminfoMapper.selectAllList();
	}

	@Override
	public List<SddzglDzxxDto> selectDzxx() {
		List<SddzglDzxxDto> list=sddzglDzxxMapper.listDzxx();
		return list;
	}

}