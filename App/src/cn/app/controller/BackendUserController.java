package cn.app.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.app.pojo.AppCategory;
import cn.app.pojo.BackendUser;
import cn.app.pojo.DataDictionary;
import cn.app.pojo.Page;
import cn.app.service.AppCategoryService;
import cn.app.service.AppInfoService;
import cn.app.service.BackendUserService;
import cn.app.service.DataDictionaryService;
@Controller
@RequestMapping("/bkd")
public class BackendUserController {
   @Resource
   private BackendUserService backendUserService;
   @Resource
   private AppInfoService appinfoService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource
	private AppCategoryService appCategoryService;

   //跳转去到管理登录页面
   @RequestMapping("/belogin")
   public String beforelogin(){
	   return "backendlogin";
   }
   //管理员登录
   @RequestMapping("/login")
   public String login(String userCode,String userPassword,HttpSession session,Model model){
	   BackendUser backendUser = backendUserService.login(userCode, userPassword);
	   if(backendUser != null){
		   session.setAttribute("userSession", backendUser);
		   return "backend/main";
	   }else{
		   model.addAttribute("error", "用户名或密码错误!请再次输入");
		   return "backendlogin";
	   }
   }
   //管理员注销
   @RequestMapping("/logout")
   public String logout(HttpSession session){
	   session.invalidate();
	   return "backendlogin";
   }
   
}
