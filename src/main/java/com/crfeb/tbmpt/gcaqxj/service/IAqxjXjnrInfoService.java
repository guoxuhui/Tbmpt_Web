package com.crfeb.tbmpt.gcaqxj.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;

/**
 *
 * AqxjXjnrInfo 表数据服务层接口
 *
 */
public interface IAqxjXjnrInfoService extends ICommonService<AqxjXjnrInfo> {

    /**
     * 根据巡检点id 查询巡检信息列表
     * @param pageInfo
     */
    void selectDataGrid(PageInfo pageInfo);

    /**
     * 添加
     * @param aqxjXjnrInfo
     * @param user
     * @return
     */
   int insert(AqxjXjnrInfo aqxjXjnrInfo,User user);

    /**
     * 更新
     * @param aqxjXjnrInfo
     * @return
     */
    String update(AqxjXjnrInfo aqxjXjnrInfo,User user);


    List<AqxjXjnrInfo> selectAll(String id);
}