package com.crfeb.tbmpt.open.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.open.model.DicInfo;
import com.crfeb.tbmpt.open.model.ProInfo;
import com.crfeb.tbmpt.open.model.ProSec;
import com.crfeb.tbmpt.open.model.SecLine;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.project.model.ProDwgcInfo;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProDwgcInfoService;
import com.crfeb.tbmpt.project.service.IProFbgcInfoService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;
import com.crfeb.tbmpt.risk.service.IRiskInfoService;
import com.crfeb.tbmpt.risk.service.IRiskLevelService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.vo.SysEmployeeVo;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.sys.service.IUserService;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoVo;
import com.crfeb.tbmpt.zl.service.IQualityInfoService;

@Service
public class OpenCommServiceImpl implements IOpenCommService {
	@Autowired
	private IUserService userService;
	@Autowired
	private ISysEmployeeService sysEmployeeService;
	@Autowired
	private IOrgzService orgzService;
	@Autowired
	private IProRSectionLineService proRSectionLineService;
	@Autowired
	private IProRProjectSectionService proRProjectSectionService;
	@Autowired
	private IProProjectinfoService proProjectinfoService;

	@Autowired
	private IProDwgcInfoService proDwgcInfoService;

	@Autowired
	private IProFbgcInfoService proFbgcInfoService;

	@Autowired
	private IRiskInfoService iRiskInfoService;

	@Autowired
	private IQualityInfoService iQualityInfoService;

	@Autowired
	private IRiskLevelService iRiskLevelService;

	@Autowired
	private ISysEmployeeService iSysEmployeeService;

	@Override
	public int bindClientId(User user, String cid) {
		// 第一 更新解绑设备Clientid
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", cid);
		List<User> listUser = userService.selectByMap(map);
		for (User u : listUser) {
			u.setCid("");
			userService.updateSelectiveById(u);
		}
		// 第二 绑定新的设备Clientid
		User u = userService.selectById(user.getId());
		u.setCid(cid);
		if (userService.updateSelectiveById(u)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public User getUserById(String id) {
		return userService.selectById(id);
	}

	@Override
	public Orgz getOrgzByUserId(String id) {
		return orgzService.getOrgzInfoByUserId(id);
	}

	@Override
	public ProProjectinfo getProByUserId(String id) {
		return proProjectinfoService.getProjectInfoByUserId(id);
	}

	@Override
	public ProProjectinfo getProByProId(String id) {
		return proProjectinfoService.selectById(id);
	}

	@Override
	public List<ProProjectinfo> getProsByUserId(String id) {
		return proProjectinfoService.getProjectInfosByUserId(id);
	}

	@Override
	public List<ProRProjectSection> getSectionByProId(String id) {
		return proRProjectSectionService.getSectionByProId(id);
	}

	@Override
	public List<ProRSectionLine> getLineBySecId(String id) {
		return proRSectionLineService.getLineBySectionId(id);
	}

	@Override
	public ProInfo getProInfoByProId(String id) {
		ProInfo proinfo = new ProInfo();
		ProProjectinfo pro = proProjectinfoService.selectById(id);
		if (pro != null) {
			proinfo.setId(id);
			proinfo.setName(pro.getProName());
			SysEmployee emp = sysEmployeeService.selectById(pro.getEmpId());
			if (emp != null) {
				proinfo.setEmpName(emp.getName());
			}

			List<ProSec> secList = new ArrayList<ProSec>();
			List<ProRProjectSection> secs = proRProjectSectionService.getSectionByProId(id);
			for (ProRProjectSection sec : secs) {
				ProSec secinfo = new ProSec();
				secinfo.setId(sec.getId());
				secinfo.setName(sec.getSectionName());
				List<SecLine> lineList = new ArrayList<SecLine>();
				List<ProRSectionLine> lines = proRSectionLineService.getLineBySectionId(sec.getId());
				for (ProRSectionLine line : lines) {
					SecLine lineinfo = new SecLine();
					lineinfo.setId(line.getId());
					lineinfo.setName(line.getLineName());
					lineList.add(lineinfo);
				}
				secinfo.setSecLines(lineList);
				secList.add(secinfo);
			}
			proinfo.setProSecs(secList);
			return proinfo;
		} else {
			return null;
		}
	}

	@Override
	public DicInfo getDwgcInfoByProId(String id) {
		DicInfo proinfo = new DicInfo();
		ProProjectinfo pro = proProjectinfoService.selectById(id);
		if (pro != null) {
			proinfo.setId(id);
			proinfo.setName(pro.getProName());
			List<DicInfo> secList = new ArrayList<DicInfo>();
			List<ProDwgcInfo> secs = proDwgcInfoService.getDwgcInfoByProId(id);
			for (ProDwgcInfo sec : secs) {
				DicInfo secinfo = new DicInfo();
				secinfo.setId(sec.getId());
				secinfo.setName(sec.getDwgcname());
				List<DicInfo> lineList = new ArrayList<DicInfo>();
				List<ProFbgcInfo> lines = proFbgcInfoService.getFbgcBySid(sec.getId());
				for (ProFbgcInfo line : lines) {
					DicInfo lineinfo = new DicInfo();
					lineinfo.setId(line.getId());
					lineinfo.setName(line.getFbgcname());
					lineList.add(lineinfo);
				}
				secinfo.setFbgcs(lineList);
				secList.add(secinfo);
			}
			proinfo.setDwgcs(secList);
			return proinfo;
		} else {
			return null;
		}
	}

	@Override
	public SysEmployee getSysEmployeeById(String id) {
		return sysEmployeeService.selectById(id);
	}

	@Override
	public User getUserByToken(String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", token);
		List<User> listUser = userService.selectByMap(map);
		if (listUser != null && listUser.size() > 0) {
			return listUser.get(0);
		} else {
			return null;
		}
	}

	@Override
	public String relogin(String token) {

		User user = getUserByToken(token);
		if (user != null) {
			Subject suser = SecurityUtils.getSubject();
			UsernamePasswordToken tt = new UsernamePasswordToken(user.getLoginName(), user.getPassword().toCharArray());
			// 默认设置为记住密码，你可以自己在表单中加一个参数来控制
			tt.setRememberMe(true);
			suser.login(tt);
		}

		return "";
	}

	@Override
	public void updateUserInfo(User user) {
		userService.updateById(user);

	}

	@Override
	public void updateEmpInfo(SysEmployee emp) {
		sysEmployeeService.updateById(emp);
	}

	/**
	 * 添加风险上报
	 * 
	 * @param t
	 * @return
	 */
	public int addRiskUp(RiskInfo t) {
		return iRiskInfoService.save(t);
	}

	/**
	 * 查询风险上报
	 * 
	 * @param pageInfo
	 * @param user
	 */
	public void listRiskUp(PageInfo pageInfo) {
		iRiskInfoService.selectDataGridApp(pageInfo);
	}

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
	public long selectRiskInfoProIdUpUserRikeLevelUpTimeConut(String proName, String upUser, String rikeLevel,
			String[] time) {
		return iRiskInfoService.selectProIdUpUserRikeLevelUpTimeConut(proName, upUser, rikeLevel, time);
	}

	/**
	 * 管片上报
	 * 
	 * @param t
	 * @return
	 */
	public int addQualityUp(QualityInfo t) {
		return iQualityInfoService.save(t);
	}

	/**
	 * 管片上报列表
	 * 
	 * @param pageInfo
	 */
	public void listQualityUp(PageInfo pageInfo) {
		iQualityInfoService.selectDataGridApp(pageInfo);
	}

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
	public long selectQualityUpProIdUpUserUpDateConut(String proId, String upUser, String[] time) {
		return iQualityInfoService.selectProIdUpUserUpDateConut(proId, upUser, time);
	}
	
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
	public long selectQualityUpProIdTypeUpDateConut(String proId, String type, String[] time) {
		return iQualityInfoService.selectProIdTypeUpDateConut(proId, type, time);
	}
	/**
	 * 获取风险上报等级
	 * 
	 * @param riskLevel
	 * @return List<RiskLevel>
	 */
	public List<RiskLevel> queryRiskLevelList(EntityWrapper<RiskLevel> riskLevel) {
		return iRiskLevelService.queryList(riskLevel);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<SysEmployeeVo> selectSysEmployeeAll() {
		return iSysEmployeeService.selectAll();
	}

	/**
	 * 获取一个
	 * 
	 * @param empId
	 * @return
	 */
	public SysEmployee selectSysEmployeeById(String empId) {
		return iSysEmployeeService.selectById(empId);
	}

	/**
	 * 获取树
	 * 
	 * @return
	 */
	public List<Tree> selectTreeByUser(User user) {
//		return orgzService.selectTreeByUser(user);
		return orgzService.selectTreeByOrgzPid(user);
	}

	public List<Orgz> selectOrgzList(User user) {
		return orgzService.selectTreeGridByUser(user);
	}

	/**
	 * 获取项目信息
	 * 
	 * @param proId
	 * @return
	 */
	public ProProjectinfo getProProjectinfoByProId(String proId) {
		return proProjectinfoService.selectByProId(proId);
	}

	/**
	 * 根据人员获取对应的人
	 * 
	 * @param user
	 * @return
	 */
	public List<Tree> selectUserTreeByUser(User user) {
//		return orgzService.selectUserTreeByUser(user);
		return orgzService.selectUserTreeByOrgzPid(user);
	}

	/**
	 * 根据项目Id获取上报信息
	 * 
	 * @param proId
	 * @param userId
	 * @return
	 */
	public List<RiskInfoVo> selectVoRiskInfoByProId(String proId, String userId) {
		return iRiskInfoService.selectVoRiskInfoByProId(proId, userId);
	}

	/**
	 * 根据项目名称 获取观片质量上报列表
	 * 
	 * @param proId
	 * @param userId
	 * @return
	 */
	public List<QualityInfoVo> selectVoQualityInfoByProId(String proId, String userId) {
		return iQualityInfoService.selectVoQualityInfoByProId(proId, userId);
	}

	/**
	 * 根据id删除风险上报记录
	 * 
	 * @param id
	 * @return
	 */
	public Object delectRiskInfoById(String id) {
		return iRiskInfoService.deleteById(id);
	}
	
	/**
	 * 更新风险上报
	 */
	@Override
	public Object editRiskInfoById(RiskInfo t) {
		// TODO Auto-generated method stub
		t.setRikeDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRikeDesc()));
		t.setMainControlMethod(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getMainControlMethod()));
		t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
		return iRiskInfoService.updateById(t);
	}

	@Override
	public Object delectQualityInfoById(String id) {
		// TODO Auto-generated method stub
		return iQualityInfoService.deleteById(id);
	}

	@Override
	public Object editQualityInfoById(QualityInfo t) {
		// TODO Auto-generated method stub
    	t.setProblemDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getProblemDesc()));
    	t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
		return iQualityInfoService.updateById(t);
	}
	
	/**
	 * 根据线路id，环号查询管片质量问题
	 */
	@Override
	public QualityInfo selectQualityInfoByLineIdNo(String lineId, String cycNo) {
		// TODO Auto-generated method stub
		QualityInfo q = iQualityInfoService.selectQualityInfoByCycleNo(lineId, cycNo);
		return q;
	}

}
