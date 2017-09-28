package com.crfeb.tbmpt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.sys.model.User_role;

/**
 *
 * UserRole 表数据库控制层接口
 *
 */
public interface User_roleMapper extends CommonMapper<User_role> {

    List<User_role> selectByUserId(@Param("userId") String userId);

    List<String> selectRoleIdListByUserId(@Param("userId") String userId);

}