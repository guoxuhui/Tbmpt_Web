package com.crfeb.tbmpt.open.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.shiro.ShiroUser;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.DigestUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.open.model.DicInfo;
import com.crfeb.tbmpt.open.model.ProInfo;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;

/**
 * @description：登录退出
 * @author：smxg @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/open")
public class OpenController extends BaseController {
	private static final Logger LOGGER = LogManager.getLogger(OpenController.class);
	private String imagePath = "/upload/user/head/" + DateUtils.format(new Date(), "yyyyMMdd");
	@Autowired
	IOpenCommService openCommServiceImpl;

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object loginPost(String username, String password, String cid) {
		LOGGER.info("POST请求登录");
		long startTime = System.currentTimeMillis();
		if (StringUtils.isBlank(username)) {
			return renderError("用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return renderError("密码不能为空");
		}
		Subject suser = SecurityUtils.getSubject();
		username = username.trim();
		UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
		// 默认设置为记住密码，你可以自己在表单中加一个参数来控制
		token.setRememberMe(true);
		try {
			suser.login(token);
			if (!StringUtils.isEmpty(cid)) {
				openCommServiceImpl.bindClientId(getCurrentUser(), cid);
			}
		} catch (UnknownAccountException e) {
			LOGGER.error("账号不存在！", e);
			return renderError("账号不存在");
		} catch (DisabledAccountException e) {
			LOGGER.error("账号未启用！", e);
			return renderError("账号未启用");
		} catch (IncorrectCredentialsException e) {
			LOGGER.error("密码错误！", e);
			return renderError("密码错误");
		} catch (RuntimeException e) {
			LOGGER.error("未知错误,请联系管理员！", e);
			return renderError("未知错误,请联系管理员");
		}
		logService.log("移动端模块", "用户登陆", "登陆", "成功", "用户 " + username + " 登陆");
		User user = getCurrentUser();
		SysEmployee emp = openCommServiceImpl.getSysEmployeeById(user.getEmpId());
		Orgz orgz=openCommServiceImpl.getOrgzByUserId(getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("emp", emp);
		map.put("orgz", orgz);
		long endTime = System.currentTimeMillis();
		System.out.println(
				"loginPost Session ID =" + this.request.getSession().getId() + " == " + "执行=" + (endTime - startTime));
		return renderSuccess(map);
	}

	@RequestMapping(value = "/bindClientId")
	@ResponseBody
	public Object bindClientId(String cid) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		openCommServiceImpl.bindClientId(getCurrentUser(), cid);
		return renderSuccess("绑定成功");
	}

	/**
	 * 注销用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Object logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		ShiroUser user = (ShiroUser) subject.getPrincipal();
		if (user != null) {
			openCommServiceImpl.bindClientId(getCurrentUser(), "");
			logService.log("移动端模块", "用户注销", "注销", "成功", "用户 " + user.getName() + " 注销");
		}
		return renderSuccess("注销成功！");
	}

	/**
	 * 获取用户基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserById")
	@ResponseBody
	public Object getUserById() {
		long startTime = System.currentTimeMillis();
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		SysEmployee emp = openCommServiceImpl.getSysEmployeeById(user.getEmpId());
		Map<String, Object> map = new HashMap<String, Object>();
		Orgz orgz=openCommServiceImpl.getOrgzByUserId(user.getId());
		map.put("user", user);
		map.put("emp", emp);
		map.put("orgz", orgz);
		long endTime = System.currentTimeMillis();
		System.out.println("getUserById Session ID =" + this.request.getSession().getId() + " == " + "执行="
				+ (endTime - startTime));
		return renderSuccess(map);
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUserInfo")
	@ResponseBody
	public Object updateUserInfo(String address, String birthday, String email, String phone, String sex,
			String nation) {
		User user = getCurrentUser();
		SysEmployee emp = openCommServiceImpl.getSysEmployeeById(user.getEmpId());
		if (address != null && address.length() != 0) {
			emp.setAddress(address);
		}
		if (birthday != null && birthday.length() != 0) {
			emp.setBirthday(birthday);
		}
		if (email != null && email.length() != 0) {
			emp.setEmail(email);
		}
		if (phone != null && phone.length() != 0) {
			emp.setPhone(phone);
		}
		if (sex != null && sex.length() != 0) {
			emp.setSex(sex);
		}
		if (nation != null && nation.length() != 0) {
			emp.setNation(nation);
		}

		openCommServiceImpl.updateEmpInfo(emp);
		openCommServiceImpl.updateUserInfo(user);
		return renderSuccess("修改成功！");
	}

	/**
	 * 更新用户头像
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUserHead")
	@ResponseBody
	public Object updateUserHead(String userId, @RequestParam(value = "file", required = false) MultipartFile file) {
		User user = getCurrentUser();
		SysEmployee emp = openCommServiceImpl.getSysEmployeeById(user.getEmpId());
		String fileName = "";
		if (file != null) {
			fileName = file.getOriginalFilename();
		}

		if (!StringUtils.isEmpty(fileName)) {
			String type = fileName.substring(fileName.lastIndexOf(".") + 1);
			String tempUid = UUID.randomUUID().toString();
			String originalFileName = "Y" + tempUid;// 原始图片保存文件名
			String compressFileNewName = "S" + tempUid;// 缩略图片保存文件名
			UploadUtil uploadutil = new UploadUtil();
			String errorMessage = uploadutil.uploadImage(request, file, originalFileName, compressFileNewName,
					imagePath);// 上传原始图片并生成对应的缩略图
			if (StringUtils.isNotBlank(errorMessage)) {
				return renderError(errorMessage);
			} else {
				emp.setHeadUrl(imagePath + "/" + originalFileName + "." + type);
				openCommServiceImpl.updateEmpInfo(emp);
			}
		}
		return renderSuccess("修改成功！");
	}

	/**
	 * 获取用户项目信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProsByUserId")
	@ResponseBody
	public Object getProsByUserId() {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		List<ProProjectinfo> u = openCommServiceImpl.getProsByUserId(user.getId());
		return renderSuccess(u);
	}

	/**
	 * 获取用户项目信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProByProId")
	@ResponseBody
	public Object getProByProId(String proId) {
		ProProjectinfo pro = openCommServiceImpl.getProByProId(proId);
		return renderSuccess(pro);
	}

	/**
	 * 获取项目信息封装，包括区间、线路信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProInfoByProId")
	@ResponseBody
	public Object getProInfoByProId(String id) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		ProInfo u = openCommServiceImpl.getProInfoByProId(id);
		if (u == null) {
			return renderError("项目编号不存在");
		} else {
			return renderSuccess(u);
		}

	}

	/**
	 * 获取分部工程信息封装，包括项目、区间、分部工程信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getFbgcInfoByProId")
	@ResponseBody
	public Object getFbgcInfoByProId(String id) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		DicInfo u = openCommServiceImpl.getDwgcInfoByProId(id);
		if (u == null) {
			return renderError("项目编号不存在");
		} else {
			return renderSuccess(u);
		}

	}

	/**
	 * 获取用户中心页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sysUserinfoPage", method = RequestMethod.GET)
	public String sysUserinfoPage(Model model, String token) {
		if (!StringUtils.isEmpty(token)) {
			User user = openCommServiceImpl.getUserByToken(token);
			Subject suser = SecurityUtils.getSubject();
			UsernamePasswordToken tt = new UsernamePasswordToken(user.getLoginName(), user.getPassword().toCharArray());
			// 默认设置为记住密码，你可以自己在表单中加一个参数来控制
			tt.setRememberMe(true);
			suser.login(tt);
		}
		User user = getCurrentUser();
		Orgz orgz = openCommServiceImpl.getOrgzByUserId(getUserId());
		SysEmployee emp = openCommServiceImpl.getSysEmployeeById(user.getEmpId());
		model.addAttribute("user", user);
		model.addAttribute("orgz", orgz);
		model.addAttribute("emp", emp);
		return "open/sys/sysUserinfo";
	}

	/**
	 * 获取系统消息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sysMassagePage", method = RequestMethod.GET)
	public String sysMassagePage() {
		return "open/sys/sysMassage";
	}

	/**
	 * 用户反馈页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sysFacebackPage", method = RequestMethod.GET)
	public String sysFacebackPage() {
		return "open/sys/sysFaceback";
	}

	/**
	 * 用户手册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sysManualPage", method = RequestMethod.GET)
	public String sysManualPage() {
		return "open/sys/sysManual";
	}

	/**
	 * 判断登陆状况
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
	@ResponseBody
	public Object checkLogin(String userId, String jessionId, String token) {
		HashMap<String, String> map = new HashMap<>();
		HttpSession session = request.getSession();
		if (jessionId.equals(session.getId())) {
			map.put("jessionId", "true");
		} else {
			map.put("jessionId", session.getId());
		}
		return map;

	}
}
