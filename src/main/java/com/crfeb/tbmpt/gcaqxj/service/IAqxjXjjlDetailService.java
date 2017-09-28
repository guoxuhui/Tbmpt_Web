package com.crfeb.tbmpt.gcaqxj.service;

import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlDetail;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;

/**
 *
 * AqxjXjjlDetail 表数据服务层接口
 *
 */
public interface IAqxjXjjlDetailService extends ICommonService<AqxjXjjlDetail> {

    /**
     * 根据巡检记录id查看巡检详细内容
     * @param id
     * @return
     */
    List<AqxjXjjlDetail> selectAll(String  id);

    /**
     * 添加
     * @param aqxjXjjlDetail
     * @param user
     * @return
     */
    int insert(AqxjXjjlDetail aqxjXjjlDetail,User user);

}