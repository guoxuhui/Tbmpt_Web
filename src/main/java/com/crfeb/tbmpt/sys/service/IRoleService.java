package com.crfeb.tbmpt.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.Role;

/**
 *
 * Role 表数据服务层接口
 *
 */
public interface IRoleService extends ICommonService<Role> {

    List<String> selectRoleIdListByUserId(String userId);

    List<Map<String, String>> selectRoleResourceListByRoleId(String roleId);

    void selectDataGrid(PageInfo pageInfo);

    Object selectTree();

    List<String> selectResourceIdListByRoleId(String id);

    void updateRoleResource(String id, String resourceIds);
    
    List<Role> selectAll();
    
    void deleteRoles(String ids);

}