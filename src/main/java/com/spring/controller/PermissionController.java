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
 * 权限模块
 * @author Administrator
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PermissionService service;

	// 角色管理-查询**********************
	@RequestMapping("/roleManagerView")
	public String roleManagerView(ModelMap model,HttpSession session) {
		// 得到用户
		User user = (User) session.getAttribute("user");
		if (user == null) {// 用户登陆超时
			return "item/login";
		}
		// 判断用户是否是超级管理
		if (user.getIsProperty() == 0) {
			return "item/login";
		}
		// 根据用户主键得到用户下面的
		List<RoleInfo> roles = service.getRolesByUserId(user.getUserid());
		model.addAttribute("roles", roles);
		//添加日志：
		log.warn(user.getUserAccount()+":用户进行了角色查询。");
		return "item/RoleInfoManager";
	}

	// 添加角色
	@RequestMapping("addRole")
	@ResponseBody
	public ResultDto addRole(String roleName, HttpSession session)throws Exception {
		roleName = roleName.trim();
		// 验证参数是否合法
		if (roleName == null || roleName.equals("")) {
			return new ResultDto(1002, "角色名字不能为空！");
		}
		// 验证角色是否已经存在
		// 得到用户
		User user = (User) session.getAttribute("user");
		if (user == null) {// 用户登陆超时
			throw new Exception("登陆超时，请重新登陆！");
		}
		// 判断用户是否是该权限
		if (user.getIsProperty() ==0) {
			throw new Exception("操作异常！");
		}
		// 验证角色是否已经存在
		if (service.getRoleCount(user.getUserid(), roleName) > 0) {
			return new ResultDto(1003, "【" + roleName + "】角色名字已经存在,无须重复添加！");
		}
		RoleInfo role = new RoleInfo();
		role.setUserId(user.getUserid());
		role.setRoleName(roleName);
		// 添加角色
		if (service.addRole(role) > 0) {
			//添加日志：
			log.warn(user.getUserAccount()+":用户添加角色成功！角色名字是："+roleName);
			// 添加成功！
			return new ResultDto(200, "添加成功", role);
		} else {
			//添加日志：
			log.error(user.getUserAccount()+":用户添加角色失败！角色名字是："+roleName);
			// 添加失败！
			return new ResultDto(1004, "添加失败！");
		}
	}

	// 删除角色
	@RequestMapping("deleteRole")
	@ResponseBody
	public ResultDto deleteRole(HttpSession session, String roleId)throws Exception {
		roleId = roleId.trim();
		// 得到用户
		User user = (User) session.getAttribute("user");
		// 验证角色Id是否合法
		if (roleId == null || roleId.equals("")) {
			throw new Exception("操作异常！");
		}
		// 验证当前角色下面是否有用户
		if (service.getUserCountByRoleId(roleId) > 0) {//角色下面存在用户
			return new ResultDto(1005, "该角色下包含用户，如必须删除该角色，请先删除用户！");
		}
		//验证是否是自己删自己
		if(roleId.equals(user.getRoleId())){
			return new ResultDto(1009, "该角色是您当前的角色，不能直接删除自己哟！");
		}
		// 删除角色（修改角色状态）
		if (service.updateRoleIsDeleteByRoleId(roleId) > 0) {//删除成功
			//添加日志：
			log.warn(user.getUserAccount()+":用户删除角色成功！");
			return new ResultDto(200, "修改成功！");
		}
		log.error(user.getUserAccount()+":删除角色失败！");
		return new ResultDto(1006, "修改失败！");
	}
	//修改角色
	@RequestMapping("updateRoleName")
	@ResponseBody
	public ResultDto updateRoleName(String roleId,String roleName,HttpSession session){
		//得到用户
		User user = (User) session.getAttribute("user");
		//用户userId去查询是否存在当前角色名字
		if(service.getRoleCount(user.getUserid(), roleName)>0){//角色名字已经存在
			return new ResultDto(1007, "当前角色名字已经存在！");
		}
		//更改角色名字
		if(service.updateRoleNameByroleId(roleId, roleName)>0){//修改成功
			log.warn(user.getUserAccount()+":修改成功败！");
			return new ResultDto(200,"修改成功！");
		}
		log.error(user.getUserAccount()+":修改失败！");
		return new ResultDto(1008, "修改失败！");
	}
	
	//角色菜单查询
	@RequestMapping("RoleMenuView")
	public String RoleMenuView(ModelMap model,String roleId,HttpSession session) throws Exception{
		//得到用户
		User user = (User) session.getAttribute("user");
		if (user == null) {// 用户登陆超时
			return "login";
		}
		//验证角色是否是当前用户的
		if(service.valRoleUserIsExists(roleId, user.getUserid())==0){
			throw new Exception("访问异常！");
		}
		System.out.println(roleId);
		//查询角色的选择的菜单和未选择的菜单
		model.addAttribute("ok_menus", service.getExistsMenusByRoleId(roleId));
		model.addAttribute("no_menus", service.getNotExistsMenusByRoleId(roleId,user.getUserid()));
		model.addAttribute("roleId", roleId);
		return "item/RoleMenuManger";
	}
	//分配菜单
	@RequestMapping("addMenu")
	public @ResponseBody ResultDto addMenu(String roleId,String menuId,HttpSession session) throws Exception{
		//得到用户
		User user = (User) session.getAttribute("user");
		if (user == null) {// 用户登陆超时
			throw new Exception("登陆超时！");
		}
		//验证角色是否是当前用户的
		if(service.valRoleUserIsExists(roleId, user.getUserid())==0){
			return new ResultDto(1008,"该角色不能拥有当前用户的!");
		}
		//验证菜单是否能够进行分配
		if(service.getMenuIsFicedByMenuId(menuId)==0){//不能分配或者菜单不存在
			return new ResultDto(1009,"不能分配或者菜单不存在!");
		}
		//根据角色Id和菜单Id验证是否已经存在
		if(service.getRoleMenuIsExistsByRoleIdAndMenuId(roleId, menuId)>0){//已经存在
			return new ResultDto(1009,"当前角色已经拥有该菜单，无需重复添加！");
		}
		//添加
		if(service.addRolemeun(roleId, menuId)>0){
			//添加成功
			return new ResultDto(200,"添加成功！");
		}
		return new ResultDto(1010,"添加失败！");
	}
	//角色移除菜单
	@RequestMapping("removeMenu")
	public @ResponseBody ResultDto removeMenu(String roleId,String menuId){
		if(service.removeRolemeunByRoleIdAndMenuId(roleId, menuId)>0){
			return new ResultDto(200,"移除成功！");
		}
		return new ResultDto(1011,"移除失败！");
	}
	//查询用户**********************
	@RequestMapping("userView")
	public String userView(ModelMap map,HttpSession session){
		//得到用户
		User user = (User) session.getAttribute("user");
		if (user == null) {// 用户登陆超时
			return "item/login";
		}
		//根据userId询全部子用户
		map.addAttribute("cuser", service.getChildUserInfoByUserId(user.getUserid()));
		//根据userId查询全部角色
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
	//创建用户
	@RequestMapping("addcreateUser")
	public @ResponseBody ResultDto creatrUser(String userAccount,String userPassword,HttpSession session){
		//得到用户
		User user = (User) session.getAttribute("user");
		User us=service.getUserByUserId(user.getUserid());
		if (user == null||us.getIsProperty()==0) {// 用户登陆超时   验证用户是否超级管理员否则不准创建
			return new ResultDto(1012,"该账号不是超级管理员！");
		}
		//验证账号有效性 字母数字6-20位
		if(!userAccount.matches("[A-Za-z0-9]{6,20}")){
			return new ResultDto(1011,"账号不合法！只能是6-20字母和数字组成！");
		}
		//密码再次加密
		userPassword=MD5Util.Decrypted(userPassword);
		//验证账号是否已经存在
		if(service.valUserAccountIsExists(userAccount)>0){//账号已存在
			return new ResultDto(2013,"该账号已经存在，请换一个试试！");
		}
		//拼接用户
		UserDto createuser=new UserDto();
		createuser.setParentUserId(user.getUserid());
		createuser.setRoleId("97040034426781699");
		createuser.setUserAccount(userAccount);
		createuser.setUserPassword(userPassword);
		//添加用户
		if(service.addcreateUser(createuser)>0){
			return new ResultDto(200,"创建成功！");
		}
		return new ResultDto(2012,"创建失败！");
	}
	//删除用户（根据UserId修改用户状态）
	@RequestMapping("deleteUser")
	public @ResponseBody ResultDto deleteUser(HttpSession session,String userId){
		//验证参数
		if(userId==null||userId.trim().equals("")){
			return new ResultDto(1013,"参数不合法！");
		}
		//得到用户
		User user = (User) session.getAttribute("user");
		//验证用户是否属于当前用户
     	if(service.valThisUserIsUser(user.getUserid(), userId)==0){
     		return new ResultDto(1014,"不是当前用户创建的就不能删除?");
     	}
     	//删除
     	if(service.updateUserIsDeleteByuserId(userId)>0){
     		//成功
     		return new ResultDto(200,"删除成功！");
     	}
     	return new ResultDto(1015,"删除失败！");
	}
	//重置密码（根据UserId修改用户密码123456两次加密）
	@RequestMapping("resetPwd")
	public @ResponseBody ResultDto resetPwd(String userId,HttpSession session){
		if(userId==null||userId.equals("")){
			return new ResultDto(1016,"参数不合法！");
		}
		//得到用户
		User user = (User) session.getAttribute("user");
		//验证用户是否属于当前用户
     	if(service.valThisUserIsUser(user.getUserid(),userId)==0){
     		return new ResultDto(1014,"不是当前用户创建的就不能重置,请找创建管理员重置！");
     	}
     	//修改
     	String pwd=MD5Util.Decrypted("123456");
     	if(service.resetPwdByuserId(userId, pwd)>0){
     		return new ResultDto(200,"重置成功！");
     	}
     	return new ResultDto(1015,"重置失败！");
	}
	//修改用户角色分配
	@RequestMapping("updateRole")
	public @ResponseBody ResultDto updateRole(HttpSession session,String userId,String roleId){
		//给当前用户的角色修改成选中的角色
		if(userId==null||roleId==null){
			return new ResultDto(1013,"操作异常！");
		}
		//得到用户
		User user = (User) session.getAttribute("user");
		//验证用户是否属于当前用户
     	if(service.valThisUserIsUser(user.getUserid(),userId)==0){
     		return new ResultDto(1014,"不是当前用户创建的就不能分配,请找创建管理员分配?");
     	}
		//修改
     	if(service.updateUserRole(userId, roleId)>0){
     		return new ResultDto(200,"修改成功！");
     	}
     	return new ResultDto(1015,"修改失败！");
	}
}
