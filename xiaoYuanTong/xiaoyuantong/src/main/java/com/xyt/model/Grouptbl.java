package com.xyt.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Grouptbl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "grouptbl", catalog = "xiaoyuantong")
public class Grouptbl implements java.io.Serializable {

	// Fields

	private String groupId;
	private String groupName;
	private Timestamp createTime;
	private Set<Usergrouptbl> usergrouptbls = new HashSet<Usergrouptbl>(0);
	private Set<Groupfriendstbl> groupfriendstbls = new HashSet<Groupfriendstbl>(
			0);

	// Constructors

	/** default constructor */
	public Grouptbl() {
	}

	/** minimal constructor */
	public Grouptbl(String groupId) {
		this.groupId = groupId;
	}

	/** full constructor */
	public Grouptbl(String groupId, String groupName, Timestamp createTime,
			Set<Usergrouptbl> usergrouptbls,
			Set<Groupfriendstbl> groupfriendstbls) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.createTime = createTime;
		this.usergrouptbls = usergrouptbls;
		this.groupfriendstbls = groupfriendstbls;
	}

	// Property accessors
	@Id
	@Column(name = "groupId", unique = true, nullable = false, length = 100)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "groupName", length = 100)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "createTime", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grouptbl")
	public Set<Usergrouptbl> getUsergrouptbls() {
		return this.usergrouptbls;
	}

	public void setUsergrouptbls(Set<Usergrouptbl> usergrouptbls) {
		this.usergrouptbls = usergrouptbls;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grouptbl")
	public Set<Groupfriendstbl> getGroupfriendstbls() {
		return this.groupfriendstbls;
	}

	public void setGroupfriendstbls(Set<Groupfriendstbl> groupfriendstbls) {
		this.groupfriendstbls = groupfriendstbls;
	}

}