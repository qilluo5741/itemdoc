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
			//���ظ�������
			return new ResultDto(3000,"�������Ϊ��");
		}
		//�������
		userPwd=MD5Util.Decrypted(userPwd);
		//�����û��˺������ѯ��Ҫ����Ϣ
		User user = service.getUser(userName,userPwd);
		try {
			if (user != null) {
				session.setAttribute("user", user);
				System.out.println("��ӭ"+user.getUserAccount()+"�ɹ���½itemϵͳ��");
				System.out.println("��½ʱ�䣡"+new Date().toString());
				return new ResultDto(200,"�ɹ�",user);// ��½�ɹ�
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("�쳣��");
			System.out.println("�쳣����");
		}
		return new ResultDto(201,"��¼ʧ��");// ��½ʧ��
	}
	/**
	 * Auth/index
	 * ��ת��ҳ
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/index")
	public String goIndex(ModelMap model,HttpSession session) {
		//�õ��û���Ϣ
		User user =(User) session.getAttribute("user");
		if(user==null){ 
			return "item/login";//��ת��½
		}
		List<MenuTypeInfo> mt=service.getAllMenuByRoleId(user.getRoleId());
		model.addAttribute("menuTypes",mt);
		return "item/menu";
	}
}
