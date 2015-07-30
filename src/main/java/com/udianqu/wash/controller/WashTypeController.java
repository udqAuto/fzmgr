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
import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.Organization;
import com.udianqu.wash.model.WashType;
import com.udianqu.wash.service.WashTypeService;
import com.udianqu.wash.viewmodel.OrganVM;
import com.udianqu.wash.viewmodel.UserVM;

/**
 * 洗车类型
 * @author xml777
 *
 */
@Controller
@RequestMapping("/washType")
public class WashTypeController {
	@Autowired WashTypeService washTypeService;
	
	@RequestMapping(value = "getWashTypeList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getWashTypeList( 
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();   
		page = page == 0 ? 1 : page;
		map.put("pageStart", (page - 1) * rows);
		map.put("pageSize", rows);   
		ListResult<WashType> rs = new ListResult<WashType>();
		rs = washTypeService.loadWashTypeList(map);
		return rs.toJson();
	}
	@RequestMapping(value = "getWashTypeList4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getWashTypeList4App( 
			HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();   
		ListResult<WashType> rs = new ListResult<WashType>();
		rs = washTypeService.loadWashTypeList(map);
		return rs.toJson();
	}
	
	@RequestMapping(value = "saveWashType.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveWashType(
			WashType washType,
			HttpServletRequest request
			){
		try {
				if (washType.getId() > 0) {//编辑
					WashType w = washTypeService.selectByPrimaryKey(washType.getId());
					if(w!=null){ 
						washType.setIsUsed(w.getIsUsed());
						washType.setSort(w.getSort());
						washTypeService.updateByPrimaryKey(washType);
					}
				} else {
					washType.setIsUsed(true);
					washType.setSort((byte) 1);
					washTypeService.insert(washType);
				}
				Result<WashType> s = new Result<WashType>(null, true,
						false, false, "保存成功");
				return s.toJson();
		} catch (Exception ex) {
			Result<WashType> s = new Result<WashType>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
	}
	
	@RequestMapping(value = "deleteWashType.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String  deleteWashType(
			@RequestParam(value = "Id", required = true) Integer id,
			HttpServletRequest request){
		try{
			washTypeService.deleteWashType(id);
			Result<WashType> s = new Result<WashType>(null, true, false,
					false, "调用后台方法出错");
			return s.toJson();
		}catch(Exception ex){
			Result<WashType> s = new Result<WashType>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
		
	}
	
}
