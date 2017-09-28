package com.crfeb.tbmpt.open.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.aqsc.base.service.SpecialManService;
import com.crfeb.tbmpt.aqsc.base.service.impl.PeiXunRenYuanServiceImpl;
import com.crfeb.tbmpt.aqsc.base.service.impl.WorklogServiceImpl;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.open.service.IOpenAqyhService;
import com.crfeb.tbmpt.sys.model.User;

@Service
public class OpenAqyhServiceImpl implements IOpenAqyhService {

	@Autowired
	private SpecialManService specialManService;
	@Autowired
	private PeiXunRenYuanServiceImpl peiXunRenYuanService;
	@Autowired
	private WorklogServiceImpl WorklogService;
	
	@Override
	public Object getSpecialManList(int page, int rows, String smName,User user) {

		PageInfo pageInfo = new PageInfo(page, rows, "fushengriqi", "asc");
        Map<String, Object> condition = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(smName)){
        	condition.put("name", smName);
        }
        pageInfo.setCondition(condition);
        try {
        	specialManService.selectDataGrid(pageInfo,user);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
	}

	@Override
	public Object getPeiXunRenYuanList(int page, int rows, String pyName,User user) {
		PageInfo pageInfo = new PageInfo(page, rows, "birthday", "asc");
        Map<String, Object> condition = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(pyName)){
        	condition.put("name", pyName);
        }
        pageInfo.setCondition(condition);
        try {
        	peiXunRenYuanService.selectDataGrid(pageInfo,user);
        	
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
	}

	@Override
	public Object getWorklogList(int page, int rows, String userId,User user) {
		PageInfo pageInfo = new PageInfo(page, rows, "workDay", "desc");
        Map<String, Object> condition = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(userId)){
        	//condition.put("create_User", userId);
        	condition.put("createUser", userId);
        }
        pageInfo.setCondition(condition);
        try {
        	WorklogService.selectDataGrid(pageInfo,user);
        	
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
	}

}
