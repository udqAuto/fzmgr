package com.udianqu.wash.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.service.AutoService;
import com.udianqu.wash.viewmodel.AutoVM;
import com.udianqu.wash.viewmodel.UserVM;

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
	@RequestMapping(value = "getAutoListByUserId.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getAutoListByUserId(  
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();    
		map.put("userId", userId);   
		ListResult<AutoVM> rs = autoService.loadAutolist(map);

		return rs.toJson();
	}
	
	@RequestMapping("/saveAuto4App.do")
	@ResponseBody
	public String saveAuto4App(AutoVM auto, HttpServletRequest request) {

		try {
		    if (auto.getId() > 0) {//edit
				autoService.updateByPrimaryKey(auto);
			} else {//add
				autoService.insert(auto);
			}
			Result<AutoVM> s = new Result<AutoVM>(null, true,
					false, false, "保存成功");
			return s.toJson();
		} catch (Exception ex) {
			Result<AutoVM> s = new Result<AutoVM>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
	}
	
	@RequestMapping(value = "deleteAuto4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String  deleteAuto4App(
			@RequestParam(value = "Id", required = true) Integer id,
			HttpServletRequest request){
		try{
			autoService.deleteAuto(id);
			Result<AutoVM> s = new Result<AutoVM>(null, true, false,
					false, "删除成功");
			return s.toJson();
		}catch(Exception ex){
			Result<AutoVM> s = new Result<AutoVM>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
		
	}
}
