package com.wg.cms.util;

/**
 * PaginationJs分页参数
 * 
 *
 */
public class PaginationJs extends DataTablesPage {
	/**
	 * 页面显示数量-PaginationJs分页
	 */
	private Integer pageSize;
	/**
	 * 记录位置-PaginationJs分页
	 */
	private Integer pageNumber;

	/**
	 * 总记录数-PaginationJs分页
	 */
	private Long totalNumber;

	private Long totalPage;

	public Long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
		this.setRecordsTotal(totalNumber);
		double num = Math.ceil((totalNumber / this.getPageSize()));
		this.setTotalPage((long) num);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		this.pageSize = pageSize;
		this.setLength(pageSize);
		this.setStart((pageNumber - 1) * pageSize);
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

}
