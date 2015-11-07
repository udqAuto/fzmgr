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
import com.udianqu.wash.viewmodel.ReportShowVM;
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
        	String date = joQuery.getString("date");
        	String startTime = joQuery.getString("startTime");
        	String endTime = joQuery.getString("endTime");
        	
//        	Map<String,Object> m = GeneralUtil.getYesterday();
//        	Map<String,Object> m2 = GeneralUtil.getCurrentTime();
//    		Date yesterday = (Date) m.get("yesterday"); 
//    		Date time2 = (Date) m2.get("currentTime"); 
    		
        	Map<String, Object> map = new HashMap<String, Object>();  
        	if (!"".equals(startTime)) {
        		startTime += " 00:00:00";
    			map.put("startTime", startTime);
    		}
    		if (!"".equals(endTime)) {
    			endTime += " 23:59:59";
    			map.put("endTime", endTime);
    		}
    		if (!"".equals(date)) {
    			map.put("date", date);
    		}
    		ListResult<ReportShowVM> rs = orderService.loadReportlist(map);
    		
    		return rs.toJson();
        }catch(Exception ex){
        	Result<ReportShowVM> s = new Result<ReportShowVM>(null, false, false,
					false, "加载数据失败！");
			return s.toJson();
        }
	}

}
