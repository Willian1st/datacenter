package com.wg.cms.util;

/**
 * DataTable结果
 * 
 * @author gaowei
 * @param <T>
 *
 */
public class DataTablesPage {
	/**
	 * 表示请求次数
	 */
	private Integer draw;
	/**
	 * 总记录数
	 */
	private Long recordsTotal;
	/**
	 * 过滤后的总记录数
	 */
	private Long recordsFiltered;
	/**
	 * 记录
	 */
	private Object data;
	/**
	 * 记录位置
	 */
	private Integer start;
	/**
	 * 页面显示数量
	 */
	private Integer length;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
