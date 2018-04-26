package com.wg.cms.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.wg.cms.bean.Setting;
import com.wg.cms.service.ISettingService;
import com.wg.cms.util.SpringContextUtil;

public abstract class BaseController {

	private final Log logger = LogFactory.getLog(getClass());
	private static ISettingService settingService = (ISettingService) SpringContextUtil.getBean("settingService");

	/**
	 * 获得request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		return request;
	}

	/**
	 * 获取appName,具体参见web.xml中的appName定义
	 * 
	 * @return
	 */
	public String getAppName() {
		ServletContext context = getRequest().getSession().getServletContext();
		String appName = context.getInitParameter("appName");
		logger.info("appName:" + appName);
		return appName;
	}

	public static Setting getSetting() {
		return settingService.selectByFlag("01");
	}

	protected ModelAndView CreateModelAndView(String url) {
		ModelAndView mv = new ModelAndView(url);
		mv.addObject("setting", getSetting());
		mv.addObject("appName", getAppName());
		return mv;
	}

}
