package com.crfeb.tbmpt.sys.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.scan.LogOperate;
import com.crfeb.tbmpt.commons.utils.DigestUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;

/**
 * @description：登录退出
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "welcome";
    }
    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "index";
    }
    
    /**
     * home
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home")
    public String home(Model model) {
        return "home";
    }
    
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/loginv2", method = RequestMethod.GET)
    public String loginv2(String u,String p) {
    	LOGGER.info("POST请求登录");

        if (StringUtils.isBlank(u)) {
        	return "redirect:/login";
        }
        if (StringUtils.isBlank(p)) {
        	return "redirect:/login";
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(u, p.toCharArray());
        // 默认设置为记住密码，你可以自己在表单中加一个参数来控制
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (Exception e) {
            LOGGER.error("登陆异常！", e);
            return "redirect:/login";
        }
        return "redirect:/index";
    }

    /**
     * GET 登录
     * @return {String}
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        LOGGER.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object loginPost(String username, String password) {
        LOGGER.info("POST请求登录");

        if (StringUtils.isBlank(username)) {
            return renderError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return renderError("密码不能为空");
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        // 默认设置为记住密码，你可以自己在表单中加一个参数来控制
        token.setRememberMe(true);
        try {
            user.login(token);
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
        logService.log("系统模块","用户登陆","登陆","成功", "用户 "+username+" 登陆");
        return renderSuccess();
    }

    /**
     * 未授权
     * @return {String}
     */
    @RequestMapping(value = "/unauth")
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "unauth";
    }

    /**
     * 退出
     * @return {Result}
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    @LogOperate(value="用户{0}退出登陆！",param="123")
    public Object logout() {
        LOGGER.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return renderSuccess();
    }

}
