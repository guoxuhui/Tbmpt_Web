package com.crfeb.tbmpt.gcaqxj.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjdInfo;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjdInfo 表数据库控制层接口
 *
 */
public interface AqxjXjdInfoMapper extends CommonMapper<AqxjXjdInfo> {

    /**
     * 按照条件分页查询巡检点信息
     * @param pageInfo
     * @param map
     * @return
     */
    List<AqxjXjdInfo> selectAqxjXjdGLList( Page<AqxjXjdInfo> pageInfo,@Param("condition")Map<String, Object> map);

    /**
     * 根据条件查询巡检点信息
     * @param map
     * @return
     */
    List<AqxjXjdInfo>  selectListByCondition(@Param("condition")Map<String, Object> map);

}