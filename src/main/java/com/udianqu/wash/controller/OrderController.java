package com.udianqu.wash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 洗车业务
 * @author xml777
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	/**
	 * 车主预约洗车
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String add(
			@RequestParam(value = "order", required = true) String order,
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	/**
	 * 洗车店接受订单
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "accept.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String accept(
			@RequestParam(value = "order", required = true) String order,
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	/**
	 * 洗车开始
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "execute.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String execute(
			@RequestParam(value = "order", required = true) String order,
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	/**
	 * 洗车完成
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "finish.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String finish(
			@RequestParam(value = "order", required = true) String order,
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	/**
	 * 车主给洗车店评分
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "gradeByUser.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String gradeByUser(
			@RequestParam(value = "order", required = true) String order,
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	/**
	 * 取消订单
	 * 车主和洗车店都可以取消订单
	 * 已经被洗车店接受的订单不可以取消
	 * 返回成功或不成功
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "cancel.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String cancel(
			@RequestParam(value = "order", required = true) String order,
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
}
