package com.wg.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wg.cms.bean.Setting;
import com.wg.cms.service.ISettingService;
import com.wg.cms.util.AjaxResultContext;
import com.wg.cms.util.DataBindingException;

@Controller
@RequestMapping("/setting")
public class SettingCtrl {
	@Resource(name = "settingService")
	private ISettingService settingService;

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView("/setting/index");
		mv.addObject("manage", true);
		mv.addObject("setting", settingService.selectByFlag("01"));
		return mv;
	}

	/**
	 * 修改
	 * 
	 * @param record
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	@ResponseBody
	public AjaxResultContext update(@RequestBody @Valid Setting record, BindingResult result,
			HttpServletRequest request) {
		AjaxResultContext ajaxResult = new AjaxResultContext();
		if (result.hasErrors()) {
			ajaxResult.setResult(new DataBindingException(result));
		} else {
			ajaxResult.setResult(settingService.updateByPrimaryKeySelective(record));
		}
		return ajaxResult;
	}
}
