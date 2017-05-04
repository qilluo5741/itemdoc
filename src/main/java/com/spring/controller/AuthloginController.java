package com.spring.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.entity.User;
import com.spring.falseentity.MenuTypeInfo;
import com.spring.security.ResultDto;
import com.spring.service.UserService;
import com.spring.util.MD5Util;

@Controller
@RequestMapping("/Auth")
public class AuthloginController{
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService service;
	/**
	 * Auth/login?userName=admin&userPwd=123456
	 * @param session
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
    public ResultDto getUserisuserAccountByuserPassword(HttpSession session,String userName, String userPwd){
		if(userName==null && userPwd==null){
			//返回给请求者
			return new ResultDto(3000,"请求参数为空");
		}
		//密码加密
		userPwd=MD5Util.Decrypted(userPwd);
		//根据用户账号密码查询需要的信息
		User user = service.getUser(userName,userPwd);
		try {
			if (user != null) {
				session.setAttribute("user", user);
				System.out.println("欢迎"+user.getUserAccount()+"成功登陆item系统！");
				System.out.println("登陆时间！"+new Date().toString());
				return new ResultDto(200,"成功",user);// 登陆成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("异常！");
			System.out.println("异常！！");
		}
		return new ResultDto(201,"登录失败");// 登陆失败
	}
	/**
	 * Auth/index
	 * 跳转主页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/index")
	public String goIndex(ModelMap model,HttpSession session) {
		//得到用户信息
		User user =(User) session.getAttribute("user");
		if(user==null){ 
			return "item/login";//跳转登陆
		}
		List<MenuTypeInfo> mt=service.getAllMenuByRoleId(user.getRoleId());
		model.addAttribute("menuTypes",mt);
		return "item/menu";
	}
}
