package com.wg.cms.util;

public enum GoodsType {
	WOMEN("女装"),
	/**
	 * 男装
	 */
	MEN("男装"),
	/**
	 * 内衣
	 */
	UNDERWEAR("内衣"),
	/**
	 * 母婴
	 */
	MUMANDBABY("母婴"),
	/**
	 * 化妆品
	 */
	MAKEUP("化妆品"),
	/**
	 * 居家
	 */
	HOME("居家"),
	/**
	 * 鞋包配饰
	 */
	SHOSE_AND_BAG("鞋包配饰"),
	/**
	 * 美食
	 */
	FOOD("美食"),
	/**
	 * 文体车品
	 */
	SPORTS_AND_CAR("文体车品"),
	/**
	 * 数码家电
	 */
	DIGITAL("数码家电");
	private String value;
	private String key;

	/**
	 * 页面及页面内部权限控制
	 */
	GoodsType(String value) {
		this.setKey(this.name());
		this.setValue(value);
	};

	/**
	 * 获取分类列表
	 * 
	 * @return
	 */
	public static GoodsType[] getGoodsType() {
		return GoodsType.values();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
