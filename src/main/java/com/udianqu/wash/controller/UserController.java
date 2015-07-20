package com.udianqu.wash.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.model.User;
import com.udianqu.wash.service.LoginService;
import com.udianqu.wash.service.RegistService;
import com.udianqu.wash.service.UserService; 

/**
 * 用户
 * 包括（user_type)
 * 1=系统管理员
 * 2=职员
 * 4=洗车工
 * 8=用户
 * @author xml777
 *
 */
@Controller
@RequestMapping("/user")
public class UserController { 
	@Autowired UserService userService; 
	@Autowired LoginService loginService; 
	@Autowired RegistService registService; 
	@RequestMapping("/login.do")
	@ResponseBody
	public ModelAndView testLogin(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("login/login");
		// mv.addObject("sss",123);
		// return mv;
		return mv;
	}
	
	@RequestMapping(value = "login4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String login4App(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response)
			{
		String info;
		boolean b = loginService.login(username, password);
			if (b) {
				info = "angular.callbacks._0({'status':'success'})";
			} else {
				info = "Error";
			}
		
		return info;
	}

	@RequestMapping(value = "login4Web.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	ModelAndView login4Web(
			@RequestParam(value = "mobile", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response)
			{
		ModelAndView mv;
		User user = new User();
		
		user = loginService.getLoginInfo(username);
		String userName = user.getName();
		String passWord = user.getPsd();
			if (userName.equals(username)&&passWord.equals(password)) {
				mv = new ModelAndView("login/success");
			} else {
				mv = new ModelAndView("login/loginfail");
			}
		
		return mv;
	}
	

	/*
	 * 获取注册用户列表，以下拉列表形式呈现；
	 */
	@RequestMapping(value = "getUserList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getUserList( 
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();   
		page = page == 0 ? 1 : page;
		map.put("pageStart", (page - 1) * rows);
		map.put("pageSize", rows);   
		ListResult<User> rs = userService.loadUserlist(map);

		return rs.toJson();
	}
	
	
}