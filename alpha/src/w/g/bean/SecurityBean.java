package w.g.bean;

public class SecurityBean {
	// 唯一标识符id
	private String id;
	// 用户类型
	private String userType;
	// 激活码
	private String activeCode;
	// 激活时间
	private String activeTime;
	// 可查询次数
	private Integer queryTimes;
	// 已查询次数
	private Integer queriedTimes;
	// 可重置次数
	private Integer resetTimes;
	// 已重置次数
	private Integer resetedTimes;
	// 是否有效
	private Boolean valid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public Integer getQueryTimes() {
		return queryTimes;
	}

	public void setQueryTimes(Integer queryTimes) {
		this.queryTimes = queryTimes;
	}

	public Integer getQueriedTimes() {
		return queriedTimes;
	}

	public void setQueriedTimes(Integer queriedTimes) {
		this.queriedTimes = queriedTimes;
	}

	public Integer getResetedTimes() {
		return resetedTimes;
	}

	public void setResetedTimes(Integer resetedTimes) {
		this.resetedTimes = resetedTimes;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Integer getResetTimes() {
		return resetTimes;
	}

	public void setResetTimes(Integer resetTimes) {
		this.resetTimes = resetTimes;
	}

}
