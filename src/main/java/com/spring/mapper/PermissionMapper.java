package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.entity.User;
import com.spring.falseentity.ChildUserInfo;
import com.spring.falseentity.MenusDto;
import com.spring.falseentity.RoleInfo;
import com.spring.falseentity.UserDto;

public interface PermissionMapper {
	//根据userId查询全部角色
	public List<RoleInfo> getRolesByUserId(@Param("userId")String userId);
	public User getUserByUserId(@Param("userId")String userId);
	//根据角色名，超级管理用户名得到角色存在的个数
	public int getRoleCount(@Param("userId")String userId,@Param("roleName")String roleName);
	//添加角色
	public int addRole(RoleInfo role);
	//通过角色Id得到用户的个数
	public int getUserCountByRoleId(@Param("roleId")String roleId);
	//通过角色主键修改角色的状态
	public int updateRoleIsDeleteByRoleId(@Param("roleId")String roleId);
	//修改角色名字
	public int updateRoleNameByroleId(@Param("roleId")String roleId,@Param("roleName")String roleName);
	//根据userId和角色Id验证是否存在 
	public int valRoleUserIsExists(@Param("roleId")String roleId,@Param("userId")String userId);
	//根据角色查询拥有的菜单 
	public List<MenusDto> getExistsMenusByRoleId(@Param("roleId")String roleId);
	//根据角色查询没有拥有的菜单 -->
	public List<MenusDto>  getNotExistsMenusByRoleId(@Param("roleId")String roleId,@Param("userId")String userId);
	//查询菜单是否能够分配
	public int getMenuIsFicedByMenuId(@Param("menuId")String menuId);
	//根据角色Id和菜单Id验证是否已经存在
	public int getRoleMenuIsExistsByRoleIdAndMenuId(@Param("roleId")String roleId,@Param("menuId")String menuId);
	//添加菜单那角色分配
	public int addRolemeun(@Param("roleId")String roleId,@Param("menuId")String menuId);
	//移除角色中的菜单
	public int removeRolemeunByRoleIdAndMenuId(@Param("roleId")String roleId,@Param("menuId")String menuId);
	//创建用户
	public int addcreateUser(UserDto user);
	//验证用户账号是否已经存在
	public int valUserAccountIsExists(@Param("userAccount")String userAccount);
	//查询子用户  
	public List<ChildUserInfo>  getChildUserInfoByUserId(@Param("parentUserId")String parentUserId); 
	//验证用户是否属于当前操作的用户 -->
	public int valThisUserIsUser(@Param("parentUserId")String parentUserId,@Param("userId")String userId);
	//修改用户角色
	public int updateUserRole(@Param("userId")String userId,@Param("roleId")String roleId);
	//删除用户
	public int updateUserIsDeleteByuserId(@Param("userId")String userId);
	//重置用户密码
	public int resetPwdByuserId(@Param("userId")String userId,@Param("pwd")String pwd);
}
