package com.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.falseentity.MenuTypeInfo;
import com.spring.mapper.UserMapper;
import com.spring.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;
	@Override
	public User getUser(String userAccount, String userPassword) {
		return mapper.getUser(userAccount, userPassword);
	}
	@Override
	public List<MenuTypeInfo> getAllMenu() {
		return mapper.getAllMenu();
	}
	@Override
	public List<MenuTypeInfo> getAllMenuByRoleId(String roleId) {
		return mapper.getAllMenuByRoleId(roleId);
	}
}
