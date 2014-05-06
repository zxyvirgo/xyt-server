package com.xyt.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Transmittbl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "transmittbl", catalog = "xiaoyuantong")
public class Transmittbl implements java.io.Serializable {

	// Fields

	private String transmitid;
	private String userid;
	private String messageid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public Transmittbl() {
	}

	/** minimal constructor */
	public Transmittbl(String transmitid) {
		this.transmitid = transmitid;
	}

	/** full constructor */
	public Transmittbl(String transmitid, String userid, String messageid,
			Timestamp createtime) {
		this.transmitid = transmitid;
		this.userid = userid;
		this.messageid = messageid;
		this.createtime = createtime;
	}

	// Property accessors
	@Id
	@Column(name = "transmitid", unique = true, nullable = false, length = 100)
	public String getTransmitid() {
		return this.transmitid;
	}

	public void setTransmitid(String transmitid) {
		this.transmitid = transmitid;
	}

	@Column(name = "userid", length = 100)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "messageid", length = 100)
	public String getMessageid() {
		return this.messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	@Column(name = "createtime", length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}