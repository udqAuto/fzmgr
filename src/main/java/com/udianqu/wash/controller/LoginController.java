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

/**
 * 登录、注册
 * @author xml777
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * 登录，注册都用这个控制器
	 * 移动端专用的接口后面都加上4App，移动端接口只能返回String,不能返回modelAndView
	 * web接口不需要加后缀
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired LoginService loginService;
	
	@RequestMapping(value = "login4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String login4App(
			@RequestParam(value = "identity", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response
			){
		
		String info;
		boolean b = loginService.login(username, password);
			if (b) {
				info = "angular.callbacks._0({'status':'success'})";
			} else {
				info = "Error";
			}
		
		return info;
	}
	
	@RequestMapping(value = "login.do", produces = "application/json;charset=UTF-8")
	public ModelAndView login(
			@RequestParam(value = "identity", required = true) String identity,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response
			){
		
		ModelAndView mv;
		User user = new User();
		
		user = loginService.getLoginInfo(identity);
		String userName = user.getName();
		String passWord = user.getPsd();
			if (userName.equals(identity)&&passWord.equals(password)) {
				mv = new ModelAndView("login/success");
			} else {
				mv = new ModelAndView("login/loginfail");
			}
		
		return mv;
	}
}
