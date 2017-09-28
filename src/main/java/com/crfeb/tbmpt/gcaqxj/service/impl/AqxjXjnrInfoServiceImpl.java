package com.crfeb.tbmpt.gcaqxj.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjnrInfoMapper;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjnrInfoService;
import com.baomidou.framework.service.impl.CommonServiceImpl;

import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjnrInfo 表数据服务层接口实现类
 *
 */
@Service
public class AqxjXjnrInfoServiceImpl extends CommonServiceImpl<AqxjXjnrInfoMapper, AqxjXjnrInfo> implements IAqxjXjnrInfoService {


    @Autowired
    AqxjXjnrInfoMapper aqxjXjnrInfoMapper;
    /**
     * 根据巡检点id 查询巡检信息列表
     *
     * @param pageInfo
     */
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<AqxjXjnrInfo> page = new Page<AqxjXjnrInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<AqxjXjnrInfo> list = aqxjXjnrInfoMapper.selectAqxjXjdGLList(page,condition);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    /**
     * 添加
     *
     * @param aqxjXjnrInfo
     * @param user
     * @return
     */
    @Override
    public int insert(AqxjXjnrInfo aqxjXjnrInfo, User user) {
        BaseService.operatorMessage(aqxjXjnrInfo, null, user);  //存储
        return this.aqxjXjnrInfoMapper.insert(aqxjXjnrInfo);
    }

    /**
     * 更新
     *
     * @param aqxjXjnrInfo
     * @param user
     * @return
     */
    @Override
    public String update(AqxjXjnrInfo aqxjXjnrInfo, User user) {
        AqxjXjnrInfo oldEntity = this.aqxjXjnrInfoMapper.selectById(aqxjXjnrInfo.getId());
        String errorMessage = BaseService.operatorMessage(oldEntity, aqxjXjnrInfo, user);
        if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
        BeanUtils.copyProperties(aqxjXjnrInfo, oldEntity,"itemid");
        int i = aqxjXjnrInfoMapper.updateById(oldEntity);
        if(i<1){
            return "数据更新失败,请重试!";
        }
        return "";
    }

    @Override
    public List<AqxjXjnrInfo> selectAll(String nrid) {
        return aqxjXjnrInfoMapper.selectAll(nrid);
    }
}