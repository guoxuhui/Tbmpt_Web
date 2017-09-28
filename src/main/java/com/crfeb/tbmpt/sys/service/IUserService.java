package com.crfeb.tbmpt.sys.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.vo.UserVo;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends ICommonService<User> {

    User selectByLoginName(String loginName);

    void insertByVo(UserVo userVo);

    UserVo selectVoById(String id);

    void updateByVo(UserVo userVo);

    void updatePwdByUserId(String userId, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(String id);
    
    List<UserVo> getDic(String code);
    
    List<UserVo> selectAll();
    
    /**
     * 方法说明：查询用户对应的cid<br>
     * --根据【资源路径、用户id】，<br>
		--1、通过【用户id】查找当前用户所在项目，<br>
		--2、根据所在项目查找其责任部门<br>
		--3、根据责任部门查找其下及其子部门下所有用户 users1<br>
		--4、通过【资源路径】获取所有有当前菜单权限的 users2<br>
		--5、获取users1及users2的交集并返回用户的cid<br>
		--6、所获取的用户，不包含当前用户<br>
     * @param resUrl 菜单url
     * @param userId 用户id
     * @return  返回用户cid集合
     * @author:YangYj
     * @Time: 2016年12月8日 上午11:31:10
     */
    public List<String> selectUserCId(String resUrl,String userId);
    
    public List<User> selectUserByRes(String resUrl,String userId);
    
    /**
     * 方法说明：根据业务资源URL以及部门获取 当前用户CID列表信息
     * @param resUrl 菜单url
     * @param orgzId 部门id
     * @return 返回用户cid集合
     * @author smxg
     * @Time: 2016年12月8日 上午11:31:10
     */
    public List<User> selectUserCIdByUrlOrgz(String resUrl,String orgzId);

    /**
     * 根据部门id 查询用户信息列表
     * @param orgzId
     * @return
     */
    public List<User> selectUserByOrgz(String orgzId);
}