package com.wg.cms.bean;

import java.math.BigDecimal;

import com.wg.cms.util.PaginationJs;

public class GoodsCondition extends PaginationJs {
	/** 唯一标识 */
	private Integer id;
	/** 商品名称 **/
	private String name;
	/** 商品价格 **/
	private String price;
	/** 商品原价 **/
	private String originalPrice;
	/** 商品描述 **/
	private String description;
	/** 商品图片 **/
	private String picture;
	/** 商品优惠开始时间 **/
	private String discountStartTime;
	/** 商品优惠结束时间 **/
	private String discountEndTime;
	/** 数据更新时间 */
	private String sjgxsj;
	/** 数据更新人id */
	private Integer sjgxr;
	/** 数据创建时间 */
	private String sjcjsj;
	/** 数据创建人id */
	private Integer sjcjr;
	/** 是否过期 */
	private Integer sfgq;

	private String type;
	private BigDecimal salenum;

	private BigDecimal getnum;
	/**
	 * 排序条件
	 */
	private String order;

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
		this.name = name;
	}

	public String getSjgxsj() {
		return sjgxsj;
	}

	public void setSjgxsj(String sjgxsj) {
		this.sjgxsj = sjgxsj;
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
		this.sjcjsj = sjcjsj;
	}

	public Integer getSjcjr() {
		return sjcjr;
	}

	public void setSjcjr(Integer sjcjr) {
		this.sjcjr = sjcjr;
	}

	public Integer getSfgq() {
		return sfgq;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setSfgq(Integer sfgq) {
		this.sfgq = sfgq;
	}

	public String getDiscountStartTime() {
		return discountStartTime;
	}

	public void setDiscountStartTime(String discountStartTime) {
		this.discountStartTime = discountStartTime;
	}

	public String getDiscountEndTime() {
		return discountEndTime;
	}

	public void setDiscountEndTime(String discountEndTime) {
		this.discountEndTime = discountEndTime;
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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
