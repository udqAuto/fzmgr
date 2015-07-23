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
import com.udianqu.wash.service.AutoService;
import com.udianqu.wash.viewmodel.AutoVM;

@Controller 
@RequestMapping("/auto")
public class AutoController { 
	
	@Autowired AutoService autoService;
	
	@RequestMapping(value = "getAutoList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getAutoList( 
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();   
		page = page == 0 ? 1 : page;
		map.put("pageStart", (page - 1) * rows);
		map.put("pageSize", rows);   
		ListResult<AutoVM> rs = autoService.loadAutolist(map);

		return rs.toJson();
	}
}
