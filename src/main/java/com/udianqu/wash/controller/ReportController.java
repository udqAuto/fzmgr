package com.udianqu.wash.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.Region;
import com.udianqu.wash.service.WashOrderService;
import com.udianqu.wash.viewmodel.AutoVM;
import com.udianqu.wash.viewmodel.ReportVM;

@Controller 
@RequestMapping("/auto")
public class ReportController {
	
	@Autowired WashOrderService orderService;
	
	@RequestMapping(value = "getReportList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getReportList( 
			@RequestParam(value = "reportInfo", required = false) String reportInfo,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) {
        try{
        	JSONObject joQuery = JSONObject.fromObject(reportInfo);
        	String date = joQuery.getString("date");
        	
        	Map<String,Object> m = GeneralUtil.getYesterday();
    		Date yesterday = (Date) m.get("yesterday"); 
    		
        	Map<String, Object> map = new HashMap<String, Object>();   
    		page = page == 0 ? 1 : page;
    		map.put("pageStart", (page - 1) * rows);
    		map.put("pageSize", rows); 
    		map.put("date", yesterday);
    		ListResult<ReportVM> rs = orderService.loadReportlist(map);
    		
    		return rs.toJson();
        }catch(Exception ex){
        	Result<ReportVM> s = new Result<ReportVM>(null, false, false,
					false, "加载数据失败！");
			return s.toJson();
        }
	}

}
