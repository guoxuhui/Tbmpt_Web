package com.crfeb.tbmpt.sys.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.base.mapper.SysUserMsgMapper;
import com.crfeb.tbmpt.sys.base.model.SysUserMsg;
import com.crfeb.tbmpt.sys.base.model.dto.SysUserMsgDto;
import com.crfeb.tbmpt.sys.base.service.ISysUserMsgService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.mapper.UserMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;


/**
 * <p>用户消息信息 业务层 ServiceImpl</p>
 * <p>模块：系统消息模块</p>
 * <p>日期：2017-06-03</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class SysUserMsgServiceImpl extends CommonServiceImpl<SysUserMsgMapper, SysUserMsg> implements ISysUserMsgService {

	
	@Autowired
    private SysUserMsgMapper sysUserMsgMapper;
	@Autowired
    private OrgzMapper orgzMapper;
    @Autowired
    private UserMapper userMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, User user) {
		Page<SysUserMsgDto> page = new Page<SysUserMsgDto>(pageInfo.getNowpage(), pageInfo.getSize());
		List<SysUserMsgDto> list = new ArrayList<SysUserMsgDto>();
		if(user !=null && StringUtils.isNotBlank(user.getOrgzId()) ){
			
			Orgz orgz = orgzMapper.selectById(user.getOrgzId());
			if(orgz !=null && StringUtils.isNotBlank(orgz.getId())){
				
				pageInfo.getCondition().put("userid", user.getId());
				pageInfo.getCondition().put("orgzid", orgz.getId());
				list = sysUserMsgMapper.selectDtoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
			}
		}
		
		pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
		
	}

	@Override
	public void updateMsgState(String userid, String msgtype) {
		if(!StringUtils.isBlank(msgtype)){
			if("all".equals(msgtype.toLowerCase())){
				msgtype = null;
			}
		}
		sysUserMsgMapper.updateMsgState(userid, msgtype);
	}

	@Override
	public List<Map<String, String>> getMsgStats(String userid) {
		return sysUserMsgMapper.getMsgStats(userid);
	}



}