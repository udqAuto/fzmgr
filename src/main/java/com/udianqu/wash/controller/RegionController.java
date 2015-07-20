package com.udianqu.wash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 区域
 * 省，市，县
 * @author xml777
 *
 */
@Controller
@RequestMapping("/base")
public class RegionController {
	
	@RequestMapping(value = "addRegion.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView addRegion(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	@RequestMapping(value = "deleteRegion.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView deleteRegion(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	@RequestMapping(value = "updateRegion.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView updateRegion(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
}
