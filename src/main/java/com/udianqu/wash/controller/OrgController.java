package com.udianqu.wash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 组织机构
 * @author xml777
 *
 */
@Controller
@RequestMapping("/base")
public class OrgController {
	
	@RequestMapping(value = "addOrg.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView addOrg(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	@RequestMapping(value = "deleteOrg.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView deleteOrg(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	@RequestMapping(value = "updateOrg.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView updateOrg(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}

}
