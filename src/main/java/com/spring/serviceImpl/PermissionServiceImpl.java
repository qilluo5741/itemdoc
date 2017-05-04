package com.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.falseentity.ChildUserInfo;
import com.spring.falseentity.MenusDto;
import com.spring.falseentity.RoleInfo;
import com.spring.falseentity.UserDto;
import com.spring.mapper.PermissionMapper;
import com.spring.service.PermissionService;
/**
 * 权限管理服务
 * @author Administrator
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper mapper;
	@Override
	public List<RoleInfo> getRolesByUserId(String userId) {
		return mapper.getRolesByUserId(userId);
	}
	@Override
	public int getRoleCount(String userId, String roleName) {
		return mapper.getRoleCount(userId, roleName);
	}
	@Override
	public int addRole(RoleInfo role) {
		return mapper.addRole(role);
	}
	@Override
	public int getUserCountByRoleId(String roleId) {
		return mapper.getUserCountByRoleId(roleId);
	}
	@Override
	public int updateRoleIsDeleteByRoleId(String roleId) {
		return mapper.updateRoleIsDeleteByRoleId(roleId);
	}
	@Override
	public int updateRoleNameByroleId(String roleId, String roleName) {
		return mapper.updateRoleNameByroleId(roleId, roleName);
	}
	@Override
	public int valRoleUserIsExists(String roleId, String userId) {
		return mapper.valRoleUserIsExists(roleId, userId);
	}
	@Override
	public List<MenusDto> getExistsMenusByRoleId(String roleId) {
		return mapper.getExistsMenusByRoleId(roleId);
	}
	@Override
	public List<MenusDto> getNotExistsMenusByRoleId(String roleId,String userId) {
		return mapper.getNotExistsMenusByRoleId(roleId,userId);
	}
	@Override
	public int getMenuIsFicedByMenuId(String menuId) {
		return mapper.getMenuIsFicedByMenuId(menuId);
	}
	@Override
	public int getRoleMenuIsExistsByRoleIdAndMenuId(String roleId, String menuId) {
		return mapper.getRoleMenuIsExistsByRoleIdAndMenuId(roleId, menuId);
	}
	@Override
	public int addRolemeun(String roleId, String menuId) {
		return mapper.addRolemeun(roleId, menuId);
	}
	@Override
	public int removeRolemeunByRoleIdAndMenuId(String roleId, String menuId) {
		return mapper.removeRolemeunByRoleIdAndMenuId(roleId, menuId);
	}
	@Override
	public int addcreateUser(UserDto user) {
		return mapper.addcreateUser(user);
	}
	@Override
	public int valUserAccountIsExists(String userAccount) {
		return mapper.valUserAccountIsExists(userAccount);
	}
	@Override
	public List<ChildUserInfo> getChildUserInfoByUserId(String parentUserId) {
		return mapper.getChildUserInfoByUserId(parentUserId);
	}
	@Override
	public int valThisUserIsUser(String parentUserId, String userId) {
		return mapper.valThisUserIsUser(parentUserId, userId);
	}
	@Override
	public int updateUserRole(String userId, String roleId) {
		return mapper.updateUserRole(userId, roleId);
	}
	@Override
	public int updateUserIsDeleteByuserId(String userId) {
		return mapper.updateUserIsDeleteByuserId(userId);
	}
	@Override
	public int resetPwdByuserId(String userId, String pwd) {
		return mapper.resetPwdByuserId(userId, pwd);
	}
	@Override
	public User getUserByUserId(String userId) {
		return mapper.getUserByUserId(userId);
	}
}
