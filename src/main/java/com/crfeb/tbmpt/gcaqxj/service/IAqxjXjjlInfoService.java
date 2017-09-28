package com.crfeb.tbmpt.gcaqxj.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlInfo;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjjlInfo 表数据服务层接口
 *
 */
public interface IAqxjXjjlInfoService extends ICommonService<AqxjXjjlInfo> {

    void selectDataGrid(PageInfo pageInfo);

    /**
     * 查询推送给我的信息
     * @param pageInfo
     */
    void selectPushDataGrid(PageInfo pageInfo);

    /**
     * 更新查看状态
     * @param id
     * @param user
     */
    String update(String id,User user);

    /**
     * 责任人不合格统计
     * @param condition
     * @return
     */
    List<Map<String,Object>> selectReportByZerenr( Map<String,Object> condition);

    /**
     * 巡检点不合格统计
     * @param condition
     * @return
     */
    List<Map<String,Object>> selectReportByXunjiandian( Map<String,Object> condition);

    /**
     * 巡检点热度统计
     * @param condition
     * @return
     */
    List<Map<String,Object>> selectReportByproject(Map<String,Object> condition);

    /**
     * 项目部日巡检次数巡检统计
     * @param condition
     * @return
     */
    List<Map<String,Object>> selectReportByDay(Map<String,Object> condition);

    /**
     * 根据巡检点名称获取最后一次检查时间
     * @param mingcheng
     * @return
     */
    String  selectByMingcheng(String mingcheng);

    /**
     * 添加
     * @param aqxjXjjlInfo
     * @param user
     * @return
     */
    int insert(AqxjXjjlInfo aqxjXjjlInfo,User user);


    /**
     * 根据巡检点设置
     * @param dayCondition
     * @return
     */
    List<Map<String, Object>> selectReportByxunjiandian(Map<String, Object> dayCondition);

    AqxjXjjlInfo  selectByPKId(String id);

}