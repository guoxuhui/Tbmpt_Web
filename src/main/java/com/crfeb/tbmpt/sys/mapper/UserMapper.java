package com.crfeb.tbmpt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.vo.UserVo;

/**
 *
 * User 表数据库控制层接口
 *
 */
public interface UserMapper extends CommonMapper<User> {

	List<UserVo> selectAll();
	
    UserVo selectUserVoById(@Param("id") String id);

    List<UserVo> selectUserVoPage(Pagination page, @Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
    
    List<UserVo> getDic(@Param("code") String code);
    
    List<User> selectByOrgzId(@Param("orgzId") String orgzId);
    
    List<String> selectUserCId(@Param("params") Map<String,Object> prams);
    
    List<User> selectUserByRes(@Param("params") Map<String,Object> prams);
    
    /**
     * 方法说明：根据业务资源URL以及部门获取 当前用户CID列表信息
     * @return 返回数据CID String列表
     * @Time: 2017年1月10日 下午2:31:15
     */
    List<User> selectUserCIdByUrlOrgz(@Param("params") Map<String,Object> prams);

    List<User> selectUserByOrgz(@Param("code")String orgzId);

}