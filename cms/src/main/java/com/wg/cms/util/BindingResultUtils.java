package com.wg.cms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * 数据绑定异常工具类
 * 
 *
 */
public class BindingResultUtils {

	private BindingResultUtils() {
	}

	public static Object getErrorMessages(BindingResult bindingResult) {
		if (bindingResult == null) {
			return null;
		}

		Map<String, Object> root = new HashMap<String, Object>();
		if (bindingResult.hasErrors()) {
			root.put("success", false);
			root.put("globalErrors", getGlobalErrors(bindingResult));
			root.put("fieldErrors", getFieldErros(bindingResult));
		} else {
			root.put("success", true);
		}

		return root;
	}

	/**
	 * 设置全局错误
	 * 
	 * @param bindingResult
	 * @return
	 */
	public static List<String> getGlobalErrors(BindingResult bindingResult) {
		List<String> globalErrors = new ArrayList<String>();
		for (ObjectError oe : bindingResult.getGlobalErrors()) {
			globalErrors.add(oe.getDefaultMessage());
		}
		return globalErrors;
	}

	/**
	 * 设置字段属性错误
	 * 
	 * @param bindingResult
	 * @return
	 */
	public static Map<String, List<String>> getFieldErros(BindingResult bindingResult) {
		Map<String, List<String>> fieldErrors = new HashMap<String, List<String>>();
		for (FieldError fe : bindingResult.getFieldErrors()) {
			String f = fe.getField();

			if (fieldErrors.get(f) != null) {
				fieldErrors.get(f).add(fe.getDefaultMessage());
			} else {
				List<String> list = new LinkedList<String>();
				list.add(fe.getDefaultMessage());
				fieldErrors.put(f, list);
			}
		}
		return fieldErrors;
	}
}
