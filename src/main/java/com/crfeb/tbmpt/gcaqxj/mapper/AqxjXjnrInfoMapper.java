package com.crfeb.tbmpt.gcaqxj.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjnrInfo 表数据库控制层接口
 *
 */
public interface AqxjXjnrInfoMapper extends CommonMapper<AqxjXjnrInfo> {

    /**
     * 根据条件查询
     * @param page
     * @param condition
     * @return
     */
    List<AqxjXjnrInfo> selectAqxjXjdGLList(Page<AqxjXjnrInfo> page,@Param("condition") Map<String, Object> condition);

    List<AqxjXjnrInfo>  selectAll(@Param("nrid")String nrid);

}