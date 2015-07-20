package com.udianqu.wash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/index")
public class UserController {
	
	@RequestMapping(value = "addUser.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView addUser(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	@RequestMapping(value = "deleteUser.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView deleteUser(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	@RequestMapping(value = "updateUser.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView updateUser(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}

}