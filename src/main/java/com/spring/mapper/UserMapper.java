package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.entity.User;
import com.spring.falseentity.MenuTypeInfo;

public interface UserMapper {
	//��ѯ�û���Ϣ
	public User getUser(@Param("userAccount")String userAccount,@Param("userPassword")String userPassword);
	//��ȡȫ���˵�
	public List<MenuTypeInfo> getAllMenu();
	//���ݽ�ɫId��ѯȫ���˵�
	public List<MenuTypeInfo> getAllMenuByRoleId(@Param("roleId")String roleId);
}
