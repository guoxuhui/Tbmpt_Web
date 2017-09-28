package com.crfeb.tbmpt.open.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.open.model.DicInfo;
import com.crfeb.tbmpt.open.model.ProInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.vo.SysEmployeeVo;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoVo;

/**
 * <p>
 * 移动端基础模块API数据服务Service接口
 * </p>
 * <p>
 * 系统：移动端接口
 * </p>
 * <p>
 * 模块：基础模块
 * </p>
 * <p>
 * 日期：2016-12-05
 * </p>
 * 
 * @version 1.0
 * @author smxg
 */
public interface IOpenCommService {

	/**
	 * app根据token登录
	 * 
	 * @param token
	 * @return
	 */
	String relogin(String token);

	/**
	 * 绑定cid
	 * 
	 * @param user
	 * @param cid
	 * @return
	 */
	int bindClientId(User user, String cid);

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(String id);

	/**
	 * 根据用户token获取用户信息
	 * 
	 * @param token
	 * @return
	 */
	User getUserByToken(String token);

	/**
	 * 获取员工信息
	 * 
	 * @param id
	 * @return
	 */
	SysEmployee getSysEmployeeById(String id);

	/**
	 * 获取用户部门信息
	 * 
	 * @param id
	 * @return
	 */
	Orgz getOrgzByUserId(String id);

	/**
	 * 获取用户项目信息
	 * 
	 * @param id
	 * @return
	 */
	ProProjectinfo getProByUserId(String id);

	/**
	 * 获取用户项目信息（项目列表）
	 * 
	 * @param id
	 * @return
	 */
	List<ProProjectinfo> getProsByUserId(String id);

	/**
	 * 获取用户所在项目部下属人员信息
	 * 
	 * @param id
	 * @return
	 */
	// List<SysEmployee> getEmpsByUserId(String id);

	/**
	 * 获取用户所在项目部下属账号信息
	 * 
	 * @param id
	 * @return
	 */
	// List<User> getUsersByUserId(String id);

	/**
	 * 根据项目获取区间信息
	 * 
	 * @param id
	 * @return
	 */
	List<ProRProjectSection> getSectionByProId(String id);

	/**
	 * 根据项目获取线路信息
	 * 
	 * @param id
	 * @return
	 */
	List<ProRSectionLine> getLineBySecId(String id);

	/**
	 * 根据项目获取项目区间路线全部简要信息
	 * 
	 * @param id
	 * @return
	 */
	ProInfo getProInfoByProId(String id);

	/**
	 * 根据项目获取当前的项目信息区间信息以及分部工程信息
	 * 
	 * @param id
	 * @return
	 */
	DicInfo getDwgcInfoByProId(String id);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	void updateUserInfo(User user);

	/**
	 * 更新员工信息
	 * 
	 * @param emp
	 */
	void updateEmpInfo(SysEmployee emp);

	/**
	 * 根据项目id获得项目信息
	 * 
	 * @param id
	 *            项目id
	 * @return
	 */
	ProProjectinfo getProByProId(String id);

	/********************************************** 上报接口 *********************************************/

	/**
	 * 添加风险上报
	 * 
	 * @param t
	 * @return
	 */
	int addRiskUp(RiskInfo t);

	/**
	 * 查询风险上报
	 * 
	 * @param pageInfo
	 */
	void listRiskUp(PageInfo pageInfo);

	/**
	 * 风险上报_根据项目Id、上报人、等级、时间获取行数
	 * 
	 * @param time
	 *            时间
	 * @param proId
	 *            项目Id
	 * @param upUser
	 *            上报人
	 * @param rikeLevel
	 *            等级
	 * @return long 总数
	 */
	long selectRiskInfoProIdUpUserRikeLevelUpTimeConut(String proId, String upUser, String rikeLevel, String[] time);

	/**
	 * 管片上报
	 * 
	 * @param t
	 * @return
	 */
	int addQualityUp(QualityInfo t);

	/**
	 * 管片上报列表
	 * 
	 * @param pageInfo
	 */
	void listQualityUp(PageInfo pageInfo);

	/**
	 * 管片上报_根据项目Id、上报人、时间获取行数
	 * 
	 * @param proId
	 *            项目Id
	 * @param upUser
	 *            上报人
	 * @param time
	 *            上报日期
	 * @return long 总数
	 */
	long selectQualityUpProIdUpUserUpDateConut(String proName, String upUser, String[] time);

	/**
	 * 管片上报_根据项目Id、质量类型、时间获取行数
	 * 
	 * @param proId
	 *            项目Id
	 * @param type
	 *            质量类型
	 * @param time
	 *            上报日期
	 * @return long 总数
	 */
	long selectQualityUpProIdTypeUpDateConut(String proName, String type, String[] time);
	
	/**
	 * 获取风险上报等级
	 * 
	 * @param riskLevel
	 * @return List<RiskLevel>
	 */
	List<RiskLevel> queryRiskLevelList(EntityWrapper<RiskLevel> riskLevel);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<SysEmployeeVo> selectSysEmployeeAll();

	/**
	 * 获取一个
	 * 
	 * @param empId
	 * @return
	 */
	SysEmployee selectSysEmployeeById(String empId);

	/**
	 * 获取树
	 * 
	 * @return
	 */
	List<Tree> selectTreeByUser(User user);

	/**
	 * 获取项目信息
	 * 
	 * @param proId
	 * @return
	 */
	ProProjectinfo getProProjectinfoByProId(String proId);

	List<Orgz> selectOrgzList(User user);

	/**
	 * 根据人员获取对应的人
	 * 
	 * @param user
	 * @return
	 */
	List<Tree> selectUserTreeByUser(User user);

	/**
	 * 根据项目Id获取上报信息
	 * 
	 * @param proId
	 * @param userId
	 * @return
	 */
	List<RiskInfoVo> selectVoRiskInfoByProId(String proId, String userId);

	/**
	 * 根据项目名称 获取观片质量上报列表
	 * 
	 * @param proId
	 * @param userId
	 * @return
	 */
	List<QualityInfoVo> selectVoQualityInfoByProId(String proId, String userId);

	/**
	 * 根据id删除风险上报记录
	 * 
	 * @param id
	 * @return
	 */
	Object delectRiskInfoById(String id);

	Object editRiskInfoById(RiskInfo t);

	Object delectQualityInfoById(String id);

	Object editQualityInfoById(QualityInfo t);

	QualityInfo selectQualityInfoByLineIdNo(String lineId, String cycNo);
}