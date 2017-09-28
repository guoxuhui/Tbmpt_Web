package com.crfeb.tbmpt.simple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.simple.mapper.TestMapper;
import com.crfeb.tbmpt.simple.model.Test;
import com.crfeb.tbmpt.simple.service.ITestService;

@Service
public class TestServiceImpl extends SuperServiceImpl<TestMapper, Test> implements ITestService {

    @Autowired
    private TestMapper testMapper;

    
    public void selectDataGrid(PageInfo pageInfo) {
        Page<Test> page = new Page<Test>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Test> list = testMapper.selectRoleList(page, pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
    }

}