/**
 * 
 */
package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 
 * @Author: chenyihong
 * @Date: 2019年1月11日
 */
@Controller
public class DispatcherController {

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate(); //销毁session
		return "redirect:/index.htm";
	}

	//异步请求
	@RequestMapping("/doLogin")
	@ResponseBody
	public Object doLogin(String loginacct, String userpswd, String type, HttpSession session){

		AjaxResult result = new AjaxResult();

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loginacct", loginacct);
			paramMap.put("userpswd", MD5Util.digest(userpswd));
			paramMap.put("type", type);

			User user = userService.queryUserlogin(paramMap);

			session.setAttribute(Const.LOGIN_USER, user);
			result.setSuccess(true);
		} catch (Exception e){
			result.setMessage("登陆失败!");
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;

	}

/*	//同步请求
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct, String userpswd, String type, HttpSession session){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", userpswd);
		paramMap.put("type", type);
		User user = userService.queryUserlogin(paramMap);
		session.setAttribute(Const.LOGIN_USER, user);

		return "redirect:/main.htm";

	}*/
	
}
