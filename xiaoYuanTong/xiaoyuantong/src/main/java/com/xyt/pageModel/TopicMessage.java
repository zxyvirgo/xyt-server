package com.xyt.pageModel;

import java.sql.Timestamp;

import com.xyt.model.Usertbl;

//与插入数据库的表能直接映射的类
public class TopicMessage {
	
	private String messageid;
	private String userid;
	private String topicid;
	private String message;
	private Integer committime;
	private Integer transmit;
	private Timestamp createtime;
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getCommittime() {
		return committime;
	}
	public void setCommittime(Integer committime) {
		this.committime = committime;
	}
	public Integer getTransmit() {
		return transmit;
	}
	public void setTransmit(Integer transmit) {
		this.transmit = transmit;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	

}
