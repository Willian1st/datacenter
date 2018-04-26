package com.wg.cms.util;

import javax.xml.bind.DataBindingException;

/**
 * Ajax异步请求结果封装类
 * 
 *
 */
public class AjaxResultContext {
	/**
	 * 执行成功的标志
	 */
	private Boolean success = true;
	/**
	 * 返回结果
	 */
	private Object result;

	/**
	 * 返回对象
	 * 
	 * @param result
	 */
	public AjaxResultContext(Object result) {
		if (result instanceof DataBindingException) {
			// 有异常则执行失败
			setSuccess(false);
		}
		setResult(result);
	}

	/**
	 * 返回文本
	 * 
	 * @param result
	 */
	public AjaxResultContext(String result) {
		setResult(result);
	}

	public AjaxResultContext() {
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		if (result instanceof DataBindingException) {
			// 有异常则执行失败
			setSuccess(false);
		}
		this.result = result;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
