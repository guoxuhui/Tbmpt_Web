package com.crfeb.tbmpt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.mapper.RoleMapper;
import com.crfeb.tbmpt.sys.mapper.Role_resMapper;
import com.crfeb.tbmpt.sys.mapper.User_roleMapper;
import com.crfeb.tbmpt.sys.model.Role;
import com.crfeb.tbmpt.sys.model.Role_res;
import com.crfeb.tbmpt.sys.service.IRoleService;

/**
 *
 * Role 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends CommonServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private User_roleMapper userRoleMapper;
    @Autowired
    private Role_resMapper roleResourceMapper;
    
    
    public List<String> selectRoleIdListByUserId(String userId) {
        return userRoleMapper.selectRoleIdListByUserId(userId);
    }

    
    public List<Map<String, String>> selectRoleResourceListByRoleId(String roleId) {
        return roleMapper.selectResourceListByRoleId(roleId);
    }

    
    public void selectDataGrid(PageInfo pageInfo) {
        Page<Role> page = new Page<Role>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Role> list = roleMapper.selectRoleList(page, pageInfo.getCondition(),pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    
    public Object selectTree() {
        List<Tree> trees = new ArrayList<Tree>();
        List<Role> roles = roleMapper.selectAll();
        for (Role role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
    }

    
    public List<String> selectResourceIdListByRoleId(String id) {
        return roleMapper.selectResourceIdListByRoleId(id);
    }

    
    public void updateRoleResource(String id, String resourceIds) {
        // 先删除后添加,有点爆力
        List<String> roleResourceIdList = roleResourceMapper.selectIdListByRoleId(id);
        if (roleResourceIdList != null && (!roleResourceIdList.isEmpty())) {
            for (String roleResourceId : roleResourceIdList) {
                roleResourceMapper.deleteById(roleResourceId);
            }
        }
        String[] resources = resourceIds.split(",");
        for (String string : resources) {
        	Role_res roleResource = new Role_res();
            roleResource.setRoleId(id);
            roleResource.setResourceId(string);
            roleResourceMapper.insert(roleResource);
        }
    }


	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}
	
	public void deleteRoles(String ids){
    	String[] idss = ids.split(",");
    	for(String id:idss){
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("role_id", id);
    		roleResourceMapper.deleteByMap(map);
    		roleMapper.deleteById(id);
    	}
	}

}