package com.crfeb.tbmpt.gcaqxj.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjjlInfoMapper;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlInfo;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlInfoService;
import com.baomidou.framework.service.impl.CommonServiceImpl;

import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjjlInfo 表数据服务层接口实现类
 *
 */
@Service
public class AqxjXjjlInfoServiceImpl extends CommonServiceImpl<AqxjXjjlInfoMapper, AqxjXjjlInfo> implements IAqxjXjjlInfoService {


    @Autowired
    AqxjXjjlInfoMapper aqxjXjjlInfoMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<AqxjXjjlInfo> page = new Page<AqxjXjjlInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<AqxjXjjlInfo> list = aqxjXjjlInfoMapper.selectAqxjXjdGLList(page,condition);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    /**
     * 查询推送给我的信息
     *
     * @param pageInfo
     */
    @Override
    public void selectPushDataGrid(PageInfo pageInfo) {
        Page<AqxjXjjlInfo> page = new Page<AqxjXjjlInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<AqxjXjjlInfo> list = aqxjXjjlInfoMapper.selectAqxjXjdJLPushList(page,condition);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    /**
     * 更新查看状态
     *
     * @param id
     * @param user
     */
    @Override
    public String update(String id, User user) {
//        AqxjXjjlInfo oldEntity = this.aqxjXjjlInfoMapper.selectById(id);
//        AqxjXjjlInfo aqxjXjnrInfo = new AqxjXjjlInfo();
//        BeanUtils.copyProperties(oldEntity, aqxjXjnrInfo);
//        aqxjXjnrInfo.setIsView("1");
//        String errorMessage = BaseService.operatorMessage(oldEntity, aqxjXjnrInfo, user);
        int i = aqxjXjjlInfoMapper.updateViewStatusById(id);
        if(i<1){
            return "数据更新失败,请重试!";
        }
        return "";
    }

    /**
     * 责任人不合格统计
     *
     * @param condition
     * @return
     */
    @Override
    public List<Map<String, Object>> selectReportByZerenr(Map<String, Object> condition) {
        return aqxjXjjlInfoMapper.selectReportByZerenr(condition);
    }

    /**
     * 巡检点不合格统计
     *
     * @param condition
     * @return
     */
    @Override
    public List<Map<String, Object>> selectReportByXunjiandian(Map<String, Object> condition) {
        return  aqxjXjjlInfoMapper.selectReportByXunjiandian(condition);
    }

    /**
     * 巡检点热度统计
     *
     * @param condition
     * @return
     */
    @Override
    public List<Map<String, Object>> selectReportByproject(Map<String, Object> condition) {
        return  aqxjXjjlInfoMapper.selectReportByproject(condition);
    }

    /**
     * 项目部日巡检次数巡检统计
     *
     * @param dayCondition
     * @return
     */
    @Override
    public List<Map<String, Object>> selectReportByDay(Map<String, Object> dayCondition) {
        return aqxjXjjlInfoMapper.selectReportByDay(dayCondition);
    }

    /**
     * 根据巡检点名称获取最后一次检查时间
     *
     * @param mingcheng
     * @return
     */
    @Override
    public String selectByMingcheng(String mingcheng) {
        List<AqxjXjjlInfo> aqxjXjjlInfos = aqxjXjjlInfoMapper.selectByMingcheng(mingcheng);
        if(aqxjXjjlInfos.size()==0){
            return "";
        }else{
            String jianchaTime=aqxjXjjlInfos.get(0).getJianchatime();
            return jianchaTime;
        }

    }

    /**
     * 添加
     *
     * @param aqxjXjjlInfo
     * @param user
     * @return
     */
    @Override
    public int insert(AqxjXjjlInfo aqxjXjjlInfo, User user) {
        BaseService.operatorMessage(aqxjXjjlInfo, null, user);  //存储
        return this.aqxjXjjlInfoMapper.insert(aqxjXjjlInfo);
    }

    /**
     * 根据巡检点设置
     *
     * @param dayCondition
     * @return
     */
    @Override
    public List<Map<String, Object>> selectReportByxunjiandian(Map<String, Object> dayCondition) {
        return aqxjXjjlInfoMapper.selectReportByxunjiandian(dayCondition);
    }

    @Override
    public AqxjXjjlInfo selectByPKId(String id) {
        return aqxjXjjlInfoMapper.selectByPKId(id);
    }
}