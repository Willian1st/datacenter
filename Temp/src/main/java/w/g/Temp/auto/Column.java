package w.g.Temp.auto;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Column {

	/**
	 * 字段名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 数据类型
	 */
	private String type;
	/**
	 * 类
	 */
	private String className;
	/**
	 * 长度
	 */
	private String length;
	/**
	 * select枚举
	 */
	private String select;
	/**
	 * 是否必填0，1
	 */
	private int nullable;

	/**
	 * 字段名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 数据类型
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 类
	 */
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * select枚举
	 */
	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	/**
	 * 是否必填0，1
	 */
	public int getNullable() {
		return nullable;
	}

	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

}
