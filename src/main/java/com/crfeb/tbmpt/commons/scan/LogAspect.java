package com.crfeb.tbmpt.commons.scan;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.StopWatch;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.crfeb.tbmpt.commons.shiro.ShiroUser;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.Log;
import com.crfeb.tbmpt.sys.service.ILogService;
//@Aspect
//@Component
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	@Autowired  
    private HttpServletRequest request;
	@Autowired
	private ILogService logService;

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void cutController() {}
	/**
	 * 标注该方法体为环绕通知，当目标方法执行时执行该方法体
	 */
    @Around("cutController()")
	public Object recordSysLog(ProceedingJoinPoint jp) throws Throwable {
		StopWatch stopWatch = new StopWatch(); // 记录方法执行耗时
		stopWatch.start();
		try {
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			stopWatch.stop();
			insertOpertLog(jp, stopWatch);
		}
		return jp.proceed();
	}

	private void insertOpertLog(ProceedingJoinPoint jp, StopWatch stopWatch) throws NoSuchMethodException, SecurityException {
		
		Class<? extends Object> classD=jp.getTarget().getClass();
		String className = classD.getName();
		String methodName = jp.getSignature().getName();
		
		Object[] args = jp.getArgs();
		@SuppressWarnings("unchecked")
		Class<? extends Object>[] argsClass = new Class[args.length];
	    for (int i = 0, j = args.length; i < j; i++) {   
	         argsClass[i] = args[i].getClass();   
	    }

        Method method = null;
        if(args.length>0){
        	method = classD.getMethod(methodName,argsClass);  
        }else{
        	method = classD.getMethod(methodName);
        }

		if(jp.getTarget().getClass().isAnnotationPresent(LogFunction.class)&&
				method.isAnnotationPresent(LogOperate.class)){
			
			String params = parseParames(); //解析目标方法体的参数
			Log log=new Log();
			log.setClientIp(request.getRemoteAddr());
			log.setOptUrl(request.getRequestURI());
			log.setOptTime(String.valueOf(stopWatch.getTime()));
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			if(shiroUser!=null){
				log.setLoginId(shiroUser.getId());
				log.setLoginName(shiroUser.getName());
			}
			LogFunction fd = null;
			LogOperate md = null;
			if(classD.isAnnotationPresent(LogFunction.class)){
			    fd = classD.getAnnotation(LogFunction.class);
			}
			if(method.isAnnotationPresent(LogOperate.class)){
			    md =method.getAnnotation(LogOperate.class);
			}
			if(fd!=null){
				log.setSysName(fd.sysName());
				log.setModuleName(fd.moduleName());
			}
			if(md!=null){
				String mdValue = md.value();
				String mdParam = md.param();
				String value = MessageFormat.format(mdValue, mdParam);  
				log.setOptContent(value);
			}
			log.setCreateTime(DateUtils.getToday());
			logger.info("业务处理"+",模块：["+(fd!=null?fd.moduleName():"")+"],操作：["+(md!=null?md.value():"")+"]，调用类名称：["+className+"]，调用方法名称：["+methodName+"]，参数为：" + params + ";耗时" + stopWatch.getTime() + "毫秒");
			logService.insert(log);
			logger.info("执行完毕=======");
		}
	}
	
	private String parseParames(){
		StringBuffer bfParams = new StringBuffer();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Enumeration<String> paraNames = request.getParameterNames();
        String key;
        String value;
        while (paraNames.hasMoreElements()) {
            key = paraNames.nextElement();
            value = request.getParameter(key);
            bfParams.append(key).append("=").append(value).append("&");
        }
        if (StringUtils.isBlank(bfParams)) {
            bfParams.append(request.getQueryString());
        }
        return bfParams.toString();
	}
}