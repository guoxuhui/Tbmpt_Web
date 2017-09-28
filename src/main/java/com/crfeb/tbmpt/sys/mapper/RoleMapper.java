package com.crfeb.tbmpt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.Role;

/**
 *
 * Role 表数据库控制层接口
 *
 */
public interface RoleMapper extends CommonMapper<Role> {

    List<Role> selectAll();

    List<String> selectResourceIdListByRoleId(@Param("id") String id);

    List<Res> selectResourceIdListByRoleIdAndType(@Param("id") String id);

    List<Map<String, String>> selectResourceListByRoleId(@Param("id") String id);

    List<Role> selectRoleList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);

}