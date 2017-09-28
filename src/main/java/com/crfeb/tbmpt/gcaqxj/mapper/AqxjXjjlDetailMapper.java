package com.crfeb.tbmpt.gcaqxj.mapper;

import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlDetail;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * AqxjXjjlDetail 表数据库控制层接口
 *
 */
public interface AqxjXjjlDetailMapper extends CommonMapper<AqxjXjjlDetail> {

    /**
     * 根据巡检记录id查看巡检详细内容
     * @param id
     * @return
     */
    List<AqxjXjjlDetail> selectAllById(@Param("detailId")String id);

}