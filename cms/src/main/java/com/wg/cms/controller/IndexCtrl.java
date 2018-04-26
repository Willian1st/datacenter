package com.wg.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wg.cms.bean.User;
import com.wg.cms.service.IUserService;
import com.wg.cms.util.AjaxResultContext;
import com.wg.cms.util.DataBindingException;
import com.wg.cms.util.GoodsType;
import com.wg.cms.util.StringUtil;

@Controller
public class IndexCtrl extends BaseController {
	@Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(value = { "/index", "/" })
	public ModelAndView index(Model model) {
		ModelAndView mv = CreateModelAndView("/goods/index");
		mv.addObject("goodsType", GoodsType.getGoodsType());
		return mv;
	}

	@RequestMapping(value = { "/signin" })
	public ModelAndView signin(Model model) {
		ModelAndView mv = CreateModelAndView("/signin");
		return mv;
	}

	@RequestMapping(value = { "/signout" })
	public ModelAndView signout(Model model) {
		ModelAndView mv = CreateModelAndView("/signin");
		SecurityUtils.getSubject().logout();
		return mv;
	}

	@RequestMapping(value = "/signin", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxResultContext signin(@RequestBody @Valid User record, BindingResult result, HttpServletRequest request) {
		AjaxResultContext ajaxResult = new AjaxResultContext();
		if (result.hasErrors()) {
			ajaxResult.setResult(new DataBindingException(result));
		} else {
			User user = userService.selectByUserName(record.getUsername());
			if (user == null) {
				ajaxResult.setResult(-100);
			} else {
				String userName = record.getUsername();
				String password = record.getPassword();
				if (StringUtil.isNotNull(user.getPassword()) && StringUtil.isNotNull(password)
						&& password.equals(user.getPassword())) {
					UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
					Subject currentUser = SecurityUtils.getSubject();
					currentUser.login(token);
					ajaxResult.setSuccess(true);
				} else {
					ajaxResult.setResult(-200);
				}
			}
		}
		return ajaxResult;
	}

	@RequestMapping(value = { "/robot.txt" })
	@ResponseBody
	public String robot(Model model) {
		return "b444dea7a1029fdabf6b7ffac8cd17a2";
	}

}
