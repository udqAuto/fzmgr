package com.udianqu.wash.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.udianqu.wash.model.User;
import com.udianqu.wash.service.LoginService;
import com.udianqu.wash.service.UserService;

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
	@Autowired UserService userService;
	
	@RequestMapping(value = "login4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String login4App(
			/*@RequestParam(value = "identity", required = true) String username,
			@RequestParam(value = "password", required = true) String password,*/
			@RequestParam(value = "identity", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
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
	 @ResponseBody
	public void login(
			/*@RequestParam(value = "identity", required = true) String username,
			@RequestParam(value = "password", required = true) String password,*/
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception{ 
		try {
			/*if (username.isEmpty()) {
				response.sendRedirect("../login.jsp?optType=0");
				return;
			}
			if (password.isEmpty()) {
				response.sendRedirect("../login.jsp?optType=1");
				return;
			}
			HttpSession session = request.getSession();
			if (username.trim().length() == 0) {
				response.sendRedirect("../login.jsp?optType=0");
				return;
			}
			if (password.trim().length() == 0) {
				response.sendRedirect("../login.jsp?optType=1");
				return;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", username);
			map.put("pwd", password);
			User u = userService.loadUserByNameAndPwd(map);
			if (u != null) {
				session.setAttribute("user", u);
				response.sendRedirect("../index.jsp");
			} else {
				response.sendRedirect("../login.jsp");
				return;
			}*/
			response.sendRedirect("../index.jsp");
		}catch(Exception ex){ 
			response.sendRedirect("../login.jsp"); 
			return;
		}
	}
	/**
	 * 退出主页，返回登录页面
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "onExit.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	void onExit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();// 清除当前用户相关的session对象
		response.sendRedirect("../login.jsp"); 
	}
}
