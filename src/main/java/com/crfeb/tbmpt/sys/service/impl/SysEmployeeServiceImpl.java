package com.crfeb.tbmpt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.mapper.SysEmployeeMapper;
import com.crfeb.tbmpt.sys.mapper.UserMapper;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.vo.SysEmployeeVo;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * SysEmployee 表数据服务层接口实现类
 *
 */
@Service
public class SysEmployeeServiceImpl extends CommonServiceImpl<SysEmployeeMapper, SysEmployee> implements ISysEmployeeService {
	@Autowired
    private UserMapper userMapper;
    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;
	
    public void selectDataGrid(PageInfo pageInfo) {
        Page<SysEmployeeVo> page = new Page<SysEmployeeVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<SysEmployeeVo> list = sysEmployeeMapper.selectSysEmployeeList(page, pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
    
	public List<SysEmployeeVo> selectAll() {
		return sysEmployeeMapper.selectAll();
	}

	public int edit(SysEmployee emp) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("emp_id", emp.getId());
        List<User> users = userMapper.selectByMap(map);
        if(users != null && users.size() >= 1){
        	User user = users.get(0);
        	user.setName(emp.getName());
        	user.setOrgzId(emp.getOrgzId());
        	userMapper.updateById(user);
        }
		return sysEmployeeMapper.updateSelectiveById(emp);
	}
}