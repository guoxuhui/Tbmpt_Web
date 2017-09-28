package com.crfeb.tbmpt.tbmmg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbmUseInfoMapper;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbmUseInfoMapper;
import com.crfeb.tbmpt.tbmmg.model.ProTbmUseInfo;
import com.crfeb.tbmpt.tbmmg.model.dto.TbmUseInfoDto;
import com.crfeb.tbmpt.tbmmg.model.ProTbmUseInfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbmUseInfoService;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlXx;

/**
 *
 * ProTbmUseInfo 表数据服务层接口实现类
 *
 */
@Service
public class ProTbmUseInfoServiceImpl extends CommonServiceImpl<ProTbmUseInfoMapper, ProTbmUseInfo> implements IProTbmUseInfoService {

    @Autowired
    private ProTbmUseInfoMapper proTbmUseInfoMapper;
    @Autowired
    private OrgzMapper orgzMapper;
    
	public void selectDataGrid(PageInfo pageInfo,User user) {
//		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
//    	String code = "-1";
//    	if(orgz != null){
//    		if(orgz.getType() <= 1){
//    			code = orgz.getCode();
//    		}else{
//    			Orgz orgz2 = orgzMapper.selectById(orgz.getPid());
//    			code = orgz2.getCode();
//    		}
//    	}
//    	pageInfo.getCondition().put("code", code);
        Page<ProTbmUseInfo> page = new Page<ProTbmUseInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<TbmUseInfoDto> list = proTbmUseInfoMapper.selectList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	/**
	 * 获取全部盾构机信息
	 * @return
	 */
	public List<ProTbmUseInfo> selectAllList(){
		return proTbmUseInfoMapper.selectAllList();
	}

	@Override
	public String save(TbmUseInfoDto tbmUseInfoDto, User user) throws Exception {
		String errorMessage = "";
		ProTbmUseInfo tbmUseInfo = new ProTbmUseInfo();
        BeanUtils.copyProperties(tbmUseInfoDto, tbmUseInfo);
        proTbmUseInfoMapper.insert(tbmUseInfo);
    	return errorMessage;
	}

	@Override
	public String update(TbmUseInfoDto tbmUseInfoDto, User currentUser) {
		String errorMessage = "";
		ProTbmUseInfo tbmUseInfo = new ProTbmUseInfo();
        BeanUtils.copyProperties(tbmUseInfoDto, tbmUseInfo);	         
        int a=proTbmUseInfoMapper.updateById(tbmUseInfo);
        if(a==1){
		  return errorMessage;
        }else{
          return errorMessage+"失败";
	  }
	}

	@Override
	public TbmUseInfoDto selectBytbmid(String tbmid) {
		TbmUseInfoDto tbmUseInfoDto=proTbmUseInfoMapper.selectByTbmid(tbmid);
		return tbmUseInfoDto;
	}

}