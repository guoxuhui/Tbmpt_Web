package com.crfeb.tbmpt.gcaqxj.service.impl;

import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjjlDetailMapper;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlDetail;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlDetailService;
import com.baomidou.framework.service.impl.CommonServiceImpl;

import java.util.List;

/**
 *
 * AqxjXjjlDetail 表数据服务层接口实现类
 *
 */
@Service
public class AqxjXjjlDetailServiceImpl extends CommonServiceImpl<AqxjXjjlDetailMapper, AqxjXjjlDetail> implements IAqxjXjjlDetailService {

    @Autowired
    AqxjXjjlDetailMapper aqxjXjjlDetailMapper;

    /**
     * 根据巡检记录id查看巡检详细内容
     * @param id
     * @return
     */
    @Override
    public List<AqxjXjjlDetail> selectAll(String id) {
        return aqxjXjjlDetailMapper.selectAllById(id);
    }

    /**
     * 添加
     *
     * @param aqxjXjjlDetail
     * @param user
     * @return
     */
    @Override
    public int insert(AqxjXjjlDetail aqxjXjjlDetail, User user) {
        BaseService.operatorMessage(aqxjXjjlDetail, null, user);  //存储
        return this.aqxjXjjlDetailMapper.insert(aqxjXjjlDetail);
    }
}