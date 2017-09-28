package com.crfeb.tbmpt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.DigestUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.mapper.UserMapper;
import com.crfeb.tbmpt.sys.mapper.User_roleMapper;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.User_role;
import com.crfeb.tbmpt.sys.model.vo.UserVo;
import com.crfeb.tbmpt.sys.service.IUserService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends CommonServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User_roleMapper userRoleMapper;
    
    
    public User selectByLoginName(String loginName) {
        User user = new User();
        user.setLoginName(loginName);
        return this.selectOne(user);
    }

    
    public void insertByVo(UserVo userVo) {
        User user = BeanUtils.copy(userVo, User.class);
        user.setCreateTime(DateUtils.getToday());
        user.setPassword(DigestUtils.md5Hex("123456"));
        this.insert(user);
        
        String id = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        User_role userRole = new User_role();

        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(string);
            userRoleMapper.insert(userRole);
        }
    }

    
    public UserVo selectVoById(String id) {
        return userMapper.selectUserVoById(id);
    }

    public void updateByVo(UserVo userVo) {
        User user = BeanUtils.copy(userVo, User.class);
        this.updateById(user);
        
        String id = userVo.getId();
        List<User_role> userRoles = userRoleMapper.selectByUserId(id);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (User_role userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }

        String[] roles = userVo.getRoleIds().split(",");
        for (String string : roles) {
        	User_role userRole = new User_role();
            userRole.setUserId(id);
            userRole.setRoleId(string);
            userRoleMapper.insert(userRole);
        }
    }

    
    public void updatePwdByUserId(String userId, String md5Hex) {
    	User user = this.selectById(userId);
        user.setPassword(md5Hex);
        this.updateById(user);
    }
    
    public void selectDataGrid(PageInfo pageInfo) {
        Page<UserVo> page = new Page<UserVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<UserVo> list = userMapper.selectUserVoPage(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
    
    public void deleteUserById(String id) {
        this.deleteById(id);
        List<User_role> userRoles = userRoleMapper.selectByUserId(id);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (User_role userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
    }

    public List<UserVo> getDic(String code){
    	return userMapper.getDic(code);
    }
    
    public List<UserVo> selectAll(){
    	return userMapper.selectAll();
    }


	@Override
	public List<String> selectUserCId(String resUrl, String userId) {
		 Map<String, Object> condition = new HashMap<String, Object>();
		 condition.put("url", resUrl);
		 condition.put("userId", userId);
		 List<String> list = this.userMapper.selectUserCId(condition);
		 return list;
	}
	
	@Override
	public List<User> selectUserByRes(String resUrl, String userId) {
		 Map<String, Object> condition = new HashMap<String, Object>();
		 condition.put("url", resUrl);
		 condition.put("userId", userId);
		 List<User> list = this.userMapper.selectUserByRes(condition);
		 return list;
	}
    
	public List<User> selectUserCIdByUrlOrgz(String resUrl,String orgzId){
		Map<String, Object> condition = new HashMap<String, Object>();
		 condition.put("resUrl", resUrl);
		 condition.put("orgzId", orgzId);
		 List<User> list = this.userMapper.selectUserCIdByUrlOrgz(condition);
		 return list;
	}

    /**
     * 根据部门id 查询用户信息列表
     *
     * @param orgzId
     * @return
     */
    @Override
    public List<User> selectUserByOrgz(String orgzId) {
        return userMapper.selectUserByOrgz(orgzId);
    }
}