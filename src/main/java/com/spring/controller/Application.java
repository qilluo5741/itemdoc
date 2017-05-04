package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class Application {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
    public String index(){
		return "main";
	}
	/**
	 * lication
	 * @return
	 */
	@RequestMapping(value="/lication",method=RequestMethod.GET)
    public String application(){
		return "item/itemone";
	}
	/**
	 * itemlication
	 * @return
	 */
	@RequestMapping(value="/itemlication",method=RequestMethod.GET)
    public String itemlication(){
		return "item/itemtwo";
	}
	/**
	 * login
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
    public String itemlogin(){
		return "item/login";
	}
	/**
	 * getupload
	 * @return
	 */
	@RequestMapping(value="/getupload",method=RequestMethod.GET)
    public String upload(){
		return "item/upload";
	}
	/**
	 * exit
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/exit")
	public String logout(ModelMap model,HttpSession session) {
		session.removeAttribute("user");
		return "item/login";
	}
}
