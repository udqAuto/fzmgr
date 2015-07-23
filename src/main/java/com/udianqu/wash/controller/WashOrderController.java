package com.udianqu.wash.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.service.WashOrderService;
import com.udianqu.wash.viewmodel.WashOrderVM;

@Controller 
@RequestMapping("/order")
public class WashOrderController {
	
	@Autowired WashOrderService orderService;
	
	@RequestMapping(value = "getOrderList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getOrderList( 
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();   
		page = page == 0 ? 1 : page;
		map.put("pageStart", (page - 1) * rows);
		map.put("pageSize", rows);   
		ListResult<WashOrderVM> rs = orderService.loadOrderlist(map);

		return rs.toJson();
	}

}
