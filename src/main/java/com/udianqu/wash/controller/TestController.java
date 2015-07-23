package com.udianqu.wash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;
 

/**
 * 测试
 * @author xml777
 *
 */
@Controller
@RequestMapping("/texts")
public class TestController {
	
		@RequestMapping(value = "loadSerialNo.do", produces = "application/json;charset=UTF-8")
		public @ResponseBody String loadSerialNo(
				HttpServletRequest request, HttpServletResponse response
				){
			
			JSONArray  json = JSONArray.fromObject(null);
			String resutl  = json.toString();
			return resutl;
		}
}
