package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.entity.User;
import com.spring.falseentity.MenuTypeInfo;

public interface UserMapper {
	//查询用户信息
	public User getUser(@Param("userAccount")String userAccount,@Param("userPassword")String userPassword);
	//获取全部菜单
	public List<MenuTypeInfo> getAllMenu();
	//根据角色Id查询全部菜单
	public List<MenuTypeInfo> getAllMenuByRoleId(@Param("roleId")String roleId);
}
