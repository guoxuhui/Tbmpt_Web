package com.crfeb.tbmpt.gcaqxj.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlInfo;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjjlInfo 表数据库控制层接口
 *
 */
public interface AqxjXjjlInfoMapper extends CommonMapper<AqxjXjjlInfo> {

    List<AqxjXjjlInfo> selectAqxjXjdGLList(Page<AqxjXjjlInfo> page, @Param("condition")Map<String,Object> condition);

    /**
     * 查询推送信息
     * @param page
     * @param condition
     * @return
     */
    List<AqxjXjjlInfo> selectAqxjXjdJLPushList(Pagination page, @Param("condition")Map<String,Object> condition);

    /**
     * 更新查看状态
     * @param id
     * @return
     */
   int  updateViewStatusById(String id);

    /**
     * 责任人不合格统计
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectReportByZerenr(@Param("condition")Map<String, Object> condition);

    /**
     * 巡检点不合格统计
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectReportByXunjiandian(@Param("condition")Map<String, Object> condition);

    /**
     * 巡检热度统计
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectReportByproject(@Param("condition")Map<String, Object> condition);

    /**
     * 项目日巡检次数统计
     * @param dayCondition
     * @return
     */
    List<Map<String, Object>> selectReportByDay(@Param("dayCondition")Map<String, Object> dayCondition);


    /**
     * 根据巡检点设置
     * @param dayCondition
     * @return
     */
    List<Map<String, Object>> selectReportByxunjiandian(@Param("xunjianCondition")Map<String, Object> dayCondition);
    /**
     *  根据巡检点名称获取最后一次检查时间
     * @param mingcheng
     * @return
     */
    List<AqxjXjjlInfo> selectByMingcheng(String mingcheng);

    AqxjXjjlInfo selectByPKId(@Param("jlid")String id);

}