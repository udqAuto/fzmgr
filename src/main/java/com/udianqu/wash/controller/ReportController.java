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
import com.udianqu.wash.service.WashOrderService;
import com.udianqu.wash.viewmodel.ReportVM;

@Controller 
@RequestMapping("/report")
public class ReportController {
	
	@Autowired WashOrderService orderService;
	
	@RequestMapping(value = "getReportList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getReportList( 
			@RequestParam(value = "reportInfo", required = false) String reportInfo,
			HttpServletRequest request) {
        try{
        	JSONObject joQuery = JSONObject.fromObject(reportInfo);
        	String startTime = joQuery.getString("startTime");
        	String endTime = joQuery.getString("endTime");
        	
//    		Date start = startTime; 
//    		Date end = endTime; 
    		
        	Map<String, Object> map = new HashMap<String, Object>();  
        	if (!"".equals(startTime)) {
        		startTime += " 00:00:00";
    			map.put("start_time", startTime);
    		}
    		if (!"".equals(endTime)) {
    			endTime += " 23:59:59";
    			map.put("end_time", endTime);
    		}
    		ListResult<ReportVM> rs = orderService.loadReportlist(map);
    		
    		return rs.toJson();
        }catch(Exception ex){
        	Result<ReportVM> s = new Result<ReportVM>(null, false, false,
					false, "加载数据失败！");
			return s.toJson();
        }
	}

}
