package cn.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.app.pojo.DevUser;
import cn.app.service.DevUserService;
@Controller
@RequestMapping("/dev")
public class DevUserController {
   @Resource
	private DevUserService devUserService;
	
	//跳转到开发者登录界面
   @RequestMapping("/beforeLogin")
   public String beforeLogin(){
	   return "devlogin";
   }
   
   //登录验证
   @RequestMapping("/login")
   public String login(String devCode,String devPassword,HttpSession session,Model model){
	  DevUser devUser = devUserService.login(devCode, devPassword);
	 if(devUser != null){
		 session.setAttribute("devUserSession", devUser);
		 return "developer/main";
	 }else{
		 model.addAttribute("error", "用户名或密码错误!");
		 return "devlogin";
	 }
   }
   
   //开发者注销
   @RequestMapping("/logout")
   public String logout(HttpSession session){
	   //让session失效
	   session.invalidate();
	   return "devlogin";
   }
}
