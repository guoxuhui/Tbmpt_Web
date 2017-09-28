package com.crfeb.tbmpt.gcaqxj.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjdInfo;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * AqxjXjdInfo 表数据服务层接口
 *
 */
public interface IAqxjXjdInfoService extends ICommonService<AqxjXjdInfo> {

    /**
     * 获取巡检点分页列表信息
     * @param pageInfo
     * @return
     */
    void  selectDataGrid(PageInfo pageInfo);

    /**
     * 添加
     * @param aqxjXjdInfo
     * @param user
     * @return
     */
    int insert(AqxjXjdInfo aqxjXjdInfo,User user);

    /**
     * 更新
     * @param aqxjXjdInfo
     * @param user
     * @return
     */
    String update( AqxjXjdInfo aqxjXjdInfo,User user);

    String  addCopyXunJianDian(String ids,String typeName,String typeId,ProProjectinfo project);

    /**
     * 检查是否存在
     * @param aqxjXjdInfo
     * @param clumNames
     * @return
     */
    String checkClumIfexits(AqxjXjdInfo aqxjXjdInfo, String[] clumNames);

}