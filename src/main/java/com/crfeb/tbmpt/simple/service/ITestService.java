package com.crfeb.tbmpt.simple.service;

import com.baomidou.framework.service.ISuperService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.simple.model.Test;

public interface ITestService extends ISuperService<Test> {

    void selectDataGrid(PageInfo pageInfo);

}