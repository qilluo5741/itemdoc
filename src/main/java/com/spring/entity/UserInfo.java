package com.spring.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String roleId;//��ɫ����
	private String userAccount;//�û��˺�
	private int isProperty;//�Ƿ����Ա(0������1:��)  ����ǳ�������Ա�������ܷ���ȫ���˵�  ���򣺲��ս�ɫ
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
