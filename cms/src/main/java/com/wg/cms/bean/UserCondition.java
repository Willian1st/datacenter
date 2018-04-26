package com.wg.cms.bean;

import com.wg.cms.util.PaginationJs;

public class UserCondition extends PaginationJs {
	private String username;
	private String password;
	/**
	 * 排序条件
	 */
	private String order;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
