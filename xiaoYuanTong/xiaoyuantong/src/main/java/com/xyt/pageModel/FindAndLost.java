package com.xyt.pageModel;

import java.sql.Timestamp;


public class FindAndLost {
	private String loastandfindId;
	private String name;
	private String userid; //不是一一对应，要读取的时候，间接连接了表，然后
	private String flag;
	private String state;
	private String category;
	private String content;
	private Timestamp createTime;
	public String getLoastandfindId() {
		return loastandfindId;
	}
	public void setLoastandfindId(String loastandfindId) {
		this.loastandfindId = loastandfindId;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	

}
