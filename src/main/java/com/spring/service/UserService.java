package com.spring.service;

import java.util.List;

import com.spring.entity.User;
import com.spring.falseentity.MenuTypeInfo;

public interface UserService {
	//��ѯ�û���Ϣ
	public User getUser(String userAccount,String userPassword);
	//��ȡȫ���˵�
	public List<MenuTypeInfo> getAllMenu();
	//���ݽ�ɫId��ѯȫ���˵�
	public List<MenuTypeInfo> getAllMenuByRoleId(String roleId);
}
