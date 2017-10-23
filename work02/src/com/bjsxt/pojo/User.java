package com.bjsxt.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable{
	private Integer id;
	private String name;
	private String loginName;
	private String loginPswd;
	private Date birthday;
	private Date lastLoginTime;
	private int loginHits;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPswd() {
		return loginPswd;
	}
	public void setLoginPswd(String loginPswd) {
		this.loginPswd = loginPswd;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getLoginHits() {
		return loginHits;
	}
	public void setLoginHits(int loginHits) {
		this.loginHits = loginHits;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastLoginTime == null) ? 0 : lastLoginTime.hashCode());
		result = prime * result + loginHits;
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((loginPswd == null) ? 0 : loginPswd.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastLoginTime == null) {
			if (other.lastLoginTime != null)
				return false;
		} else if (!lastLoginTime.equals(other.lastLoginTime))
			return false;
		if (loginHits != other.loginHits)
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (loginPswd == null) {
			if (other.loginPswd != null)
				return false;
		} else if (!loginPswd.equals(other.loginPswd))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", loginName=" + loginName + ", loginPswd=" + loginPswd
				+ ", birthday=" + birthday+ ", lastLoginTime=" + lastLoginTime + ", loginHits=" + loginHits + "]";
	}
	
	
	
	
}
