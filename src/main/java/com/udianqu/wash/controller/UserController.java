package com.udianqu.wash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.udianqu.wash.model.User;
import com.udianqu.wash.service.LoginService;
import com.udianqu.wash.service.RegistService;

@Controller
@RequestMapping("/index")
public class UserController {

	@Autowired LoginService loginService; // 等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码
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
	
	
}