package com.wg.cms.bean;

import org.hibernate.validator.constraints.NotBlank;

public class Setting {
	private Integer id;
	@NotBlank(message = "图片不能为空")
	private String picture;
	@NotBlank(message = "名称不能为空")
	private String name;
	@NotBlank(message = "描述不能为空")
	private String description;
	private String flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}