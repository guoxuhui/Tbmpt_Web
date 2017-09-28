package com.crfeb.tbmpt.gpztcl.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclQxysMapper;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclQxys;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclQxysService;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;

/**
 * <p>设置曲线要素 Service实现类</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：线路中心线信息管理模块</p>
 * <p>日期：2016-12-26</p>
 * @version 1.0
 * @author wpg
 */
@Service
public class GpztclQxysServiceImpl extends CommonServiceImpl<GpztclQxysMapper, GpztclQxys> implements GpztclQxysService{

	@Autowired
	GpztclQxysMapper qxysMapper;
	
	@Autowired
	ProRSectionLineMapper proRSectionLineMapper;
	
	/**
	 * 通过id查找线路信息    wpg
	 */
	
	@Override
	public SectionLineVo selectSectionLineVoByXlId(String xlId) {
		List<SectionLineVo> sectionLineVoList =proRSectionLineMapper.selectByXlId(xlId);
		if(sectionLineVoList!=null){
			for (SectionLineVo sectionLineVo :sectionLineVoList) {
				if(sectionLineVo.getId().equals(xlId)){
					return sectionLineVo;
				}
			}
		}
	    return null;
		
	}
	
	/**
	 * @author wpg 
     * 作用：根据线路编号查询“曲线要素”信息；
     * @param xlbh
     * @return List<GpztclQxys>
     */
	@Override
	public List<GpztclQxys> selectByFIdQxysList(String xlbh) {
		return qxysMapper.selectQxysListByXlbh(xlbh);
	}
	

}