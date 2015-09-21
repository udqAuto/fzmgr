package com.udianqu.wash.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.Auto;
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
		map.put("isUsed", true);
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
		map.put("isUsed", true);
		ListResult<AutoVM> rs = autoService.loadAutolist(map);

		return rs.toJson();
	}
	@RequestMapping(value = "getAutoByUserId4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getAutoByUserId4App(  
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();    
		map.put("userId", userId);   
		map.put("isUsed", true);   
		ListResult<AutoVM> rs = autoService.loadAutolist(map);
		
		return rs.toJson();
	}
	
	@RequestMapping(value = "saveAuto4App.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveAuto4App(
			@RequestParam(value = "autoInfo", required = true) String autoInfo,
			HttpServletRequest request) {
		try {
			JSONObject jObj = JSONObject.fromObject(autoInfo);
			AutoVM auto = (AutoVM) JSONObject.toBean(jObj,AutoVM.class);
			String pn = auto.getPn();
//			Auto auto1 = autoService.selectByPn(pn);
		    if (auto.getId() > 0) {//edit
//		    	if(auto1.getPn() != pn&&auto1 != null){//更改了车牌却已存在
//		    		Result<AutoVM> s = new Result<AutoVM>(null, false,
//							false, false, "你所修改的车牌号码已存在");
//					return s.toJson();
		    	autoService.updateByPrimaryKey(auto);
			} else {//add
				auto.setIsUsed(true);
				autoService.insert(auto);
			}
		    //Auto auto2 = autoService.selectByPn(pn);
			Result<Auto> s = new Result<Auto>(auto, true, false, false, "保存成功");
			return s.toJson();
		} catch (Exception ex) {
			Result<AutoVM> s = new Result<AutoVM>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
	}
	/**
	 * 删除时将isUsed置为false
	 * 
	 * 
	 */
	@RequestMapping(value = "deleteAuto4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String  deleteAuto4App(
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request){
		try{
			Auto auto = autoService.selectById(id);
			auto.setIsUsed(false);
			autoService.updateIsUsedAuto(auto);
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
