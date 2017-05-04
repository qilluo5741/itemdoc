package com.spring.service;

import java.util.List;

import com.spring.entity.User;
import com.spring.falseentity.ChildUserInfo;
import com.spring.falseentity.MenusDto;
import com.spring.falseentity.RoleInfo;
import com.spring.falseentity.UserDto;

public interface PermissionService {
	// ����userId��ѯȫ����ɫ
	public List<RoleInfo> getRolesByUserId(String userId);
	public User getUserByUserId(String userId);
	// ���ݽ�ɫ�������������û����õ���ɫ���ڵĸ���
	public int getRoleCount(String userId, String roleName);

	// ��ӽ�ɫ
	public int addRole(RoleInfo role);

	// ͨ����ɫId�õ��û��ĸ���
	public int getUserCountByRoleId(String roleId);

	// ͨ����ɫ�����޸Ľ�ɫ��״̬
	public int updateRoleIsDeleteByRoleId(String roleId);

	// �޸Ľ�ɫ����
	public int updateRoleNameByroleId(String roleId, String roleName);

	// ����userId�ͽ�ɫId��֤�Ƿ����
	public int valRoleUserIsExists(String roleId, String userId);

	// ���ݽ�ɫ��ѯӵ�еĲ˵�
	public List<MenusDto> getExistsMenusByRoleId(String roleId);

	// ���ݽ�ɫ��ѯû��ӵ�еĲ˵� -->
	public List<MenusDto> getNotExistsMenusByRoleId(String roleId, String userId);

	// ��ѯ�˵��Ƿ��ܹ�����
	public int getMenuIsFicedByMenuId(String menuId);

	// ���ݽ�ɫId�Ͳ˵�Id��֤�Ƿ��Ѿ�����
	public int getRoleMenuIsExistsByRoleIdAndMenuId(String roleId, String menuId);

	// ��Ӳ˵��ǽ�ɫ����
	public int addRolemeun(String roleId, String menuId);

	// �Ƴ���ɫ�еĲ˵�
	public int removeRolemeunByRoleIdAndMenuId(String roleId, String menuId);

	// �����û�
	public int addcreateUser(UserDto user);

	// ��֤�û��˺��Ƿ��Ѿ�����
	public int valUserAccountIsExists(String userAccount);

	// ��ѯ���û�
	public List<ChildUserInfo> getChildUserInfoByUserId(String parentUserId);

	// ��֤�û��Ƿ����ڵ�ǰ�������û� -->
	public int valThisUserIsUser(String parentUserId, String userId);

	// �޸��û���ɫ
	public int updateUserRole(String userId, String roleId);

	// ɾ���û�
	public int updateUserIsDeleteByuserId(String userId);

	// �����û�����
	public int resetPwdByuserId(String userId, String pwd);
}
