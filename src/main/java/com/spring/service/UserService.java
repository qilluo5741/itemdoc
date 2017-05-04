package com.spring.service;

import java.util.List;

import com.spring.entity.User;
import com.spring.falseentity.MenuTypeInfo;

public interface UserService {
	//查询用户信息
	public User getUser(String userAccount,String userPassword);
	//获取全部菜单
	public List<MenuTypeInfo> getAllMenu();
	//根据角色Id查询全部菜单
	public List<MenuTypeInfo> getAllMenuByRoleId(String roleId);
}
