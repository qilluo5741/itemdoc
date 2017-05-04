package com.spring.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String roleId;//角色主键
	private String userAccount;//用户账号
	private int isProperty;//是否管理员(0：不是1:是)  如果是超级管理员：代表能访问全部菜单  否则：参照角色
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public int getIsProperty() {
		return isProperty;
	}
	public void setIsProperty(int isProperty) {
		this.isProperty = isProperty;
	}
}
