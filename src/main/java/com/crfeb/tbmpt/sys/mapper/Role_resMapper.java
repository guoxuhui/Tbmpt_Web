package com.crfeb.tbmpt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.sys.model.Role_res;

/**
 *
 * RoleResource 表数据库控制层接口
 *
 */
public interface Role_resMapper extends CommonMapper<Role_res> {

    List<String> selectIdListByRoleId(@Param("id") String id);

}