package com.crfeb.tbmpt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.sys.model.Res;

/**
 *
 * Resource 表数据库控制层接口
 *
 */
public interface ResMapper extends CommonMapper<Res> {

    List<Res> selectAllByTypeAndPIdNull(@Param("resourceType")Integer resourceType);

    List<Res> selectAllByTypeAndPId(@Param("resourceType")Integer resourceType, @Param("pId")String pId);

    List<Res> selectAll();
    
    List<Res> selectResList(Pagination page, @Param("sort") String sort, @Param("order") String order);
}