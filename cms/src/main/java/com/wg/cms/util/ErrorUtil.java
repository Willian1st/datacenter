package com.wg.cms.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 打印BindingResult的错误信息
 * 
 *
 */
public class ErrorUtil {
	/**
	 * 打印BindingResult的错误信息
	 * 
	 * @param result
	 * @param logger
	 */
	public static void printErrors(BindingResult result, Log logger) {
		List<ObjectError> errorList = result.getAllErrors();
		for (ObjectError error : errorList) {
			logger.error("操作失败：" + error.getDefaultMessage());
		}
	}
}
