package com.wg.cms.bean;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

public class Goods {
	private Integer id;
	/* @NotBlank(message = "商品名称不能为空") */
	private String name;
	/* @NotNull(message = "商品原价不能为空") */
	private BigDecimal originalprice;
	/* @NotNull(message = "优惠券不能为空") */
	private BigDecimal coupon;

	private BigDecimal price;

	private String discountstarttime;

	private String discountendtime;

	private String type;

	private BigDecimal salenum;

	private BigDecimal getnum;

	private String flag;

	private String sjgxsj;

	private Integer sjgxr;

	private String sjcjsj;

	private Integer sjcjr;
	@NotBlank(message = "商品描述不能为空")
	private String description;

	private String picture;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public BigDecimal getOriginalprice() {
		return originalprice;
	}

	public void setOriginalprice(BigDecimal originalprice) {
		this.originalprice = originalprice;
	}

	public BigDecimal getCoupon() {
		return coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDiscountstarttime() {
		return discountstarttime;
	}

	public void setDiscountstarttime(String discountstarttime) {
		this.discountstarttime = discountstarttime == null ? null : discountstarttime.trim();
	}

	public String getDiscountendtime() {
		return discountendtime;
	}

	public void setDiscountendtime(String discountendtime) {
		this.discountendtime = discountendtime == null ? null : discountendtime.trim();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}

	public String getSjgxsj() {
		return sjgxsj;
	}

	public void setSjgxsj(String sjgxsj) {
		this.sjgxsj = sjgxsj == null ? null : sjgxsj.trim();
	}

	public Integer getSjgxr() {
		return sjgxr;
	}

	public void setSjgxr(Integer sjgxr) {
		this.sjgxr = sjgxr;
	}

	public String getSjcjsj() {
		return sjcjsj;
	}

	public void setSjcjsj(String sjcjsj) {
		this.sjcjsj = sjcjsj == null ? null : sjcjsj.trim();
	}

	public Integer getSjcjr() {
		return sjcjr;
	}

	public void setSjcjr(Integer sjcjr) {
		this.sjcjr = sjcjr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture == null ? null : picture.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getSalenum() {
		return salenum;
	}

	public void setSalenum(BigDecimal salenum) {
		this.salenum = salenum;
	}

	public BigDecimal getGetnum() {
		return getnum;
	}

	public void setGetnum(BigDecimal getnum) {
		this.getnum = getnum;
	}

}