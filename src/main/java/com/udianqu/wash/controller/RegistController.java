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
import com.udianqu.wash.service.RegistService;

@Controller
@RequestMapping("/registPage")

public class RegistController {
	
	@Autowired RegistService registService;
	User user;
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public ModelAndView regist(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("regist/regist");
		return mv;
	}
	
	@RequestMapping(value = "registAction.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String registAction(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "sex", required = true) Byte sex,
			@RequestParam(value = "tel", required = true) String tel,
			@RequestParam(value = "address", required = true) String address,
			HttpServletRequest request, HttpServletResponse response)
			{
		user = new User();
		user.setName(username);
		user.setPsd(password);
		user.setSex(sex);
		user.setMobile(tel);
		user.setEmail(address);
		registService.regist(user);
		
		return "regist";
	}


}
