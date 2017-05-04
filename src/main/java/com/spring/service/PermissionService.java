package com.spring.service;

import java.util.List;

import com.spring.entity.User;
import com.spring.falseentity.ChildUserInfo;
import com.spring.falseentity.MenusDto;
import com.spring.falseentity.RoleInfo;
import com.spring.falseentity.UserDto;

public interface PermissionService {
	// 根据userId查询全部角色
	public List<RoleInfo> getRolesByUserId(String userId);
	public User getUserByUserId(String userId);
	// 根据角色名，超级管理用户名得到角色存在的个数
	public int getRoleCount(String userId, String roleName);

	// 添加角色
	public int addRole(RoleInfo role);

	// 通过角色Id得到用户的个数
	public int getUserCountByRoleId(String roleId);

	// 通过角色主键修改角色的状态
	public int updateRoleIsDeleteByRoleId(String roleId);

	// 修改角色名字
	public int updateRoleNameByroleId(String roleId, String roleName);

	// 根据userId和角色Id验证是否存在
	public int valRoleUserIsExists(String roleId, String userId);

	// 根据角色查询拥有的菜单
	public List<MenusDto> getExistsMenusByRoleId(String roleId);

	// 根据角色查询没有拥有的菜单 -->
	public List<MenusDto> getNotExistsMenusByRoleId(String roleId, String userId);

	// 查询菜单是否能够分配
	public int getMenuIsFicedByMenuId(String menuId);

	// 根据角色Id和菜单Id验证是否已经存在
	public int getRoleMenuIsExistsByRoleIdAndMenuId(String roleId, String menuId);

	// 添加菜单那角色分配
	public int addRolemeun(String roleId, String menuId);

	// 移除角色中的菜单
	public int removeRolemeunByRoleIdAndMenuId(String roleId, String menuId);

	// 创建用户
	public int addcreateUser(UserDto user);

	// 验证用户账号是否已经存在
	public int valUserAccountIsExists(String userAccount);

	// 查询子用户
	public List<ChildUserInfo> getChildUserInfoByUserId(String parentUserId);

	// 验证用户是否属于当前操作的用户 -->
	public int valThisUserIsUser(String parentUserId, String userId);

	// 修改用户角色
	public int updateUserRole(String userId, String roleId);

	// 删除用户
	public int updateUserIsDeleteByuserId(String userId);

	// 重置用户密码
	public int resetPwdByuserId(String userId, String pwd);
}
