package com.crfeb.tbmpt.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.simple.model.Test;

public interface TestMapper extends AutoMapper<Test> {

    List<Test> selectAll();
    List<Test> selectRoleList(Pagination page, @Param("sort") String sort, @Param("order") String order);
}