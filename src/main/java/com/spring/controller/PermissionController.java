package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.entity.User;
import com.spring.falseentity.RoleInfo;
import com.spring.falseentity.UserDto;
import com.spring.security.ResultDto;
import com.spring.service.PermissionService;
import com.spring.util.MD5Util;

/**
 * Ȩ��ģ��
 * @author Administrator
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PermissionService service;

	// ��ɫ����-��ѯ**********************
	@RequestMapping("/roleManagerView")
	public String roleManagerView(ModelMap model,HttpSession session) {
		// �õ��û�
		User user = (User) session.getAttribute("user");
		if (user == null) {// �û���½��ʱ
			return "item/login";
		}
		// �ж��û��Ƿ��ǳ�������
		if (user.getIsProperty() == 0) {
			return "item/login";
		}
		// �����û������õ��û������
		List<RoleInfo> roles = service.getRolesByUserId(user.getUserid());
		model.addAttribute("roles", roles);
		//�����־��
		log.warn(user.getUserAccount()+":�û������˽�ɫ��ѯ��");
		return "item/RoleInfoManager";
	}

	// ��ӽ�ɫ
	@RequestMapping("addRole")
	@ResponseBody
	public ResultDto addRole(String roleName, HttpSession session)throws Exception {
		roleName = roleName.trim();
		// ��֤�����Ƿ�Ϸ�
		if (roleName == null || roleName.equals("")) {
			return new ResultDto(1002, "��ɫ���ֲ���Ϊ�գ�");
		}
		// ��֤��ɫ�Ƿ��Ѿ�����
		// �õ��û�
		User user = (User) session.getAttribute("user");
		if (user == null) {// �û���½��ʱ
			throw new Exception("��½��ʱ�������µ�½��");
		}
		// �ж��û��Ƿ��Ǹ�Ȩ��
		if (user.getIsProperty() ==0) {
			throw new Exception("�����쳣��");
		}
		// ��֤��ɫ�Ƿ��Ѿ�����
		if (service.getRoleCount(user.getUserid(), roleName) > 0) {
			return new ResultDto(1003, "��" + roleName + "����ɫ�����Ѿ�����,�����ظ���ӣ�");
		}
		RoleInfo role = new RoleInfo();
		role.setUserId(user.getUserid());
		role.setRoleName(roleName);
		// ��ӽ�ɫ
		if (service.addRole(role) > 0) {
			//�����־��
			log.warn(user.getUserAccount()+":�û���ӽ�ɫ�ɹ�����ɫ�����ǣ�"+roleName);
			// ��ӳɹ���
			return new ResultDto(200, "��ӳɹ�", role);
		} else {
			//�����־��
			log.error(user.getUserAccount()+":�û���ӽ�ɫʧ�ܣ���ɫ�����ǣ�"+roleName);
			// ���ʧ�ܣ�
			return new ResultDto(1004, "���ʧ�ܣ�");
		}
	}

	// ɾ����ɫ
	@RequestMapping("deleteRole")
	@ResponseBody
	public ResultDto deleteRole(HttpSession session, String roleId)throws Exception {
		roleId = roleId.trim();
		// �õ��û�
		User user = (User) session.getAttribute("user");
		// ��֤��ɫId�Ƿ�Ϸ�
		if (roleId == null || roleId.equals("")) {
			throw new Exception("�����쳣��");
		}
		// ��֤��ǰ��ɫ�����Ƿ����û�
		if (service.getUserCountByRoleId(roleId) > 0) {//��ɫ��������û�
			return new ResultDto(1005, "�ý�ɫ�°����û��������ɾ���ý�ɫ������ɾ���û���");
		}
		//��֤�Ƿ����Լ�ɾ�Լ�
		if(roleId.equals(user.getRoleId())){
			return new ResultDto(1009, "�ý�ɫ������ǰ�Ľ�ɫ������ֱ��ɾ���Լ�Ӵ��");
		}
		// ɾ����ɫ���޸Ľ�ɫ״̬��
		if (service.updateRoleIsDeleteByRoleId(roleId) > 0) {//ɾ���ɹ�
			//�����־��
			log.warn(user.getUserAccount()+":�û�ɾ����ɫ�ɹ���");
			return new ResultDto(200, "�޸ĳɹ���");
		}
		log.error(user.getUserAccount()+":ɾ����ɫʧ�ܣ�");
		return new ResultDto(1006, "�޸�ʧ�ܣ�");
	}
	//�޸Ľ�ɫ
	@RequestMapping("updateRoleName")
	@ResponseBody
	public ResultDto updateRoleName(String roleId,String roleName,HttpSession session){
		//�õ��û�
		User user = (User) session.getAttribute("user");
		//�û�userIdȥ��ѯ�Ƿ���ڵ�ǰ��ɫ����
		if(service.getRoleCount(user.getUserid(), roleName)>0){//��ɫ�����Ѿ�����
			return new ResultDto(1007, "��ǰ��ɫ�����Ѿ����ڣ�");
		}
		//���Ľ�ɫ����
		if(service.updateRoleNameByroleId(roleId, roleName)>0){//�޸ĳɹ�
			log.warn(user.getUserAccount()+":�޸ĳɹ��ܣ�");
			return new ResultDto(200,"�޸ĳɹ���");
		}
		log.error(user.getUserAccount()+":�޸�ʧ�ܣ�");
		return new ResultDto(1008, "�޸�ʧ�ܣ�");
	}
	
	//��ɫ�˵���ѯ
	@RequestMapping("RoleMenuView")
	public String RoleMenuView(ModelMap model,String roleId,HttpSession session) throws Exception{
		//�õ��û�
		User user = (User) session.getAttribute("user");
		if (user == null) {// �û���½��ʱ
			return "login";
		}
		//��֤��ɫ�Ƿ��ǵ�ǰ�û���
		if(service.valRoleUserIsExists(roleId, user.getUserid())==0){
			throw new Exception("�����쳣��");
		}
		System.out.println(roleId);
		//��ѯ��ɫ��ѡ��Ĳ˵���δѡ��Ĳ˵�
		model.addAttribute("ok_menus", service.getExistsMenusByRoleId(roleId));
		model.addAttribute("no_menus", service.getNotExistsMenusByRoleId(roleId,user.getUserid()));
		model.addAttribute("roleId", roleId);
		return "item/RoleMenuManger";
	}
	//����˵�
	@RequestMapping("addMenu")
	public @ResponseBody ResultDto addMenu(String roleId,String menuId,HttpSession session) throws Exception{
		//�õ��û�
		User user = (User) session.getAttribute("user");
		if (user == null) {// �û���½��ʱ
			throw new Exception("��½��ʱ��");
		}
		//��֤��ɫ�Ƿ��ǵ�ǰ�û���
		if(service.valRoleUserIsExists(roleId, user.getUserid())==0){
			return new ResultDto(1008,"�ý�ɫ����ӵ�е�ǰ�û���!");
		}
		//��֤�˵��Ƿ��ܹ����з���
		if(service.getMenuIsFicedByMenuId(menuId)==0){//���ܷ�����߲˵�������
			return new ResultDto(1009,"���ܷ�����߲˵�������!");
		}
		//���ݽ�ɫId�Ͳ˵�Id��֤�Ƿ��Ѿ�����
		if(service.getRoleMenuIsExistsByRoleIdAndMenuId(roleId, menuId)>0){//�Ѿ�����
			return new ResultDto(1009,"��ǰ��ɫ�Ѿ�ӵ�иò˵��������ظ���ӣ�");
		}
		//���
		if(service.addRolemeun(roleId, menuId)>0){
			//��ӳɹ�
			return new ResultDto(200,"��ӳɹ���");
		}
		return new ResultDto(1010,"���ʧ�ܣ�");
	}
	//��ɫ�Ƴ��˵�
	@RequestMapping("removeMenu")
	public @ResponseBody ResultDto removeMenu(String roleId,String menuId){
		if(service.removeRolemeunByRoleIdAndMenuId(roleId, menuId)>0){
			return new ResultDto(200,"�Ƴ��ɹ���");
		}
		return new ResultDto(1011,"�Ƴ�ʧ�ܣ�");
	}
	//��ѯ�û�**********************
	@RequestMapping("userView")
	public String userView(ModelMap map,HttpSession session){
		//�õ��û�
		User user = (User) session.getAttribute("user");
		if (user == null) {// �û���½��ʱ
			return "item/login";
		}
		//����userIdѯȫ�����û�
		map.addAttribute("cuser", service.getChildUserInfoByUserId(user.getUserid()));
		//����userId��ѯȫ����ɫ
		map.addAttribute("roles", service.getRolesByUserId(user.getUserid()));
		return "item/UserManager";
	}
	/**
	 * permission/addcreateUser?userAccount=admin&userPassword=123456
	 * @param userAccount
	 * @param userPassword
	 * @param session
	 * @return
	 */
	//�����û�
	@RequestMapping("addcreateUser")
	public @ResponseBody ResultDto creatrUser(String userAccount,String userPassword,HttpSession session){
		//�õ��û�
		User user = (User) session.getAttribute("user");
		User us=service.getUserByUserId(user.getUserid());
		if (user == null||us.getIsProperty()==0) {// �û���½��ʱ   ��֤�û��Ƿ񳬼�����Ա����׼����
			return new ResultDto(1012,"���˺Ų��ǳ�������Ա��");
		}
		//��֤�˺���Ч�� ��ĸ����6-20λ
		if(!userAccount.matches("[A-Za-z0-9]{6,20}")){
			return new ResultDto(1011,"�˺Ų��Ϸ���ֻ����6-20��ĸ��������ɣ�");
		}
		//�����ٴμ���
		userPassword=MD5Util.Decrypted(userPassword);
		//��֤�˺��Ƿ��Ѿ�����
		if(service.valUserAccountIsExists(userAccount)>0){//�˺��Ѵ���
			return new ResultDto(2013,"���˺��Ѿ����ڣ��뻻һ�����ԣ�");
		}
		//ƴ���û�
		UserDto createuser=new UserDto();
		createuser.setParentUserId(user.getUserid());
		createuser.setRoleId("97040034426781699");
		createuser.setUserAccount(userAccount);
		createuser.setUserPassword(userPassword);
		//����û�
		if(service.addcreateUser(createuser)>0){
			return new ResultDto(200,"�����ɹ���");
		}
		return new ResultDto(2012,"����ʧ�ܣ�");
	}
	//ɾ���û�������UserId�޸��û�״̬��
	@RequestMapping("deleteUser")
	public @ResponseBody ResultDto deleteUser(HttpSession session,String userId){
		//��֤����
		if(userId==null||userId.trim().equals("")){
			return new ResultDto(1013,"�������Ϸ���");
		}
		//�õ��û�
		User user = (User) session.getAttribute("user");
		//��֤�û��Ƿ����ڵ�ǰ�û�
     	if(service.valThisUserIsUser(user.getUserid(), userId)==0){
     		return new ResultDto(1014,"���ǵ�ǰ�û������ľͲ���ɾ��?");
     	}
     	//ɾ��
     	if(service.updateUserIsDeleteByuserId(userId)>0){
     		//�ɹ�
     		return new ResultDto(200,"ɾ���ɹ���");
     	}
     	return new ResultDto(1015,"ɾ��ʧ�ܣ�");
	}
	//�������루����UserId�޸��û�����123456���μ��ܣ�
	@RequestMapping("resetPwd")
	public @ResponseBody ResultDto resetPwd(String userId,HttpSession session){
		if(userId==null||userId.equals("")){
			return new ResultDto(1016,"�������Ϸ���");
		}
		//�õ��û�
		User user = (User) session.getAttribute("user");
		//��֤�û��Ƿ����ڵ�ǰ�û�
     	if(service.valThisUserIsUser(user.getUserid(),userId)==0){
     		return new ResultDto(1014,"���ǵ�ǰ�û������ľͲ�������,���Ҵ�������Ա���ã�");
     	}
     	//�޸�
     	String pwd=MD5Util.Decrypted("123456");
     	if(service.resetPwdByuserId(userId, pwd)>0){
     		return new ResultDto(200,"���óɹ���");
     	}
     	return new ResultDto(1015,"����ʧ�ܣ�");
	}
	//�޸��û���ɫ����
	@RequestMapping("updateRole")
	public @ResponseBody ResultDto updateRole(HttpSession session,String userId,String roleId){
		//����ǰ�û��Ľ�ɫ�޸ĳ�ѡ�еĽ�ɫ
		if(userId==null||roleId==null){
			return new ResultDto(1013,"�����쳣��");
		}
		//�õ��û�
		User user = (User) session.getAttribute("user");
		//��֤�û��Ƿ����ڵ�ǰ�û�
     	if(service.valThisUserIsUser(user.getUserid(),userId)==0){
     		return new ResultDto(1014,"���ǵ�ǰ�û������ľͲ��ܷ���,���Ҵ�������Ա����?");
     	}
		//�޸�
     	if(service.updateUserRole(userId, roleId)>0){
     		return new ResultDto(200,"�޸ĳɹ���");
     	}
     	return new ResultDto(1015,"�޸�ʧ�ܣ�");
	}
}
