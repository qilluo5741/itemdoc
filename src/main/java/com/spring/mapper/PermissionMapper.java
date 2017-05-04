package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.entity.User;
import com.spring.falseentity.ChildUserInfo;
import com.spring.falseentity.MenusDto;
import com.spring.falseentity.RoleInfo;
import com.spring.falseentity.UserDto;

public interface PermissionMapper {
	//����userId��ѯȫ����ɫ
	public List<RoleInfo> getRolesByUserId(@Param("userId")String userId);
	public User getUserByUserId(@Param("userId")String userId);
	//���ݽ�ɫ�������������û����õ���ɫ���ڵĸ���
	public int getRoleCount(@Param("userId")String userId,@Param("roleName")String roleName);
	//��ӽ�ɫ
	public int addRole(RoleInfo role);
	//ͨ����ɫId�õ��û��ĸ���
	public int getUserCountByRoleId(@Param("roleId")String roleId);
	//ͨ����ɫ�����޸Ľ�ɫ��״̬
	public int updateRoleIsDeleteByRoleId(@Param("roleId")String roleId);
	//�޸Ľ�ɫ����
	public int updateRoleNameByroleId(@Param("roleId")String roleId,@Param("roleName")String roleName);
	//����userId�ͽ�ɫId��֤�Ƿ���� 
	public int valRoleUserIsExists(@Param("roleId")String roleId,@Param("userId")String userId);
	//���ݽ�ɫ��ѯӵ�еĲ˵� 
	public List<MenusDto> getExistsMenusByRoleId(@Param("roleId")String roleId);
	//���ݽ�ɫ��ѯû��ӵ�еĲ˵� -->
	public List<MenusDto>  getNotExistsMenusByRoleId(@Param("roleId")String roleId,@Param("userId")String userId);
	//��ѯ�˵��Ƿ��ܹ�����
	public int getMenuIsFicedByMenuId(@Param("menuId")String menuId);
	//���ݽ�ɫId�Ͳ˵�Id��֤�Ƿ��Ѿ�����
	public int getRoleMenuIsExistsByRoleIdAndMenuId(@Param("roleId")String roleId,@Param("menuId")String menuId);
	//��Ӳ˵��ǽ�ɫ����
	public int addRolemeun(@Param("roleId")String roleId,@Param("menuId")String menuId);
	//�Ƴ���ɫ�еĲ˵�
	public int removeRolemeunByRoleIdAndMenuId(@Param("roleId")String roleId,@Param("menuId")String menuId);
	//�����û�
	public int addcreateUser(UserDto user);
	//��֤�û��˺��Ƿ��Ѿ�����
	public int valUserAccountIsExists(@Param("userAccount")String userAccount);
	//��ѯ���û�  
	public List<ChildUserInfo>  getChildUserInfoByUserId(@Param("parentUserId")String parentUserId); 
	//��֤�û��Ƿ����ڵ�ǰ�������û� -->
	public int valThisUserIsUser(@Param("parentUserId")String parentUserId,@Param("userId")String userId);
	//�޸��û���ɫ
	public int updateUserRole(@Param("userId")String userId,@Param("roleId")String roleId);
	//ɾ���û�
	public int updateUserIsDeleteByuserId(@Param("userId")String userId);
	//�����û�����
	public int resetPwdByuserId(@Param("userId")String userId,@Param("pwd")String pwd);
}
