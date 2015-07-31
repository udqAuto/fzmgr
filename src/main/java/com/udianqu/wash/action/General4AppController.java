package com.udianqu.wash.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.service.BillSerialNoService;
import com.udianqu.wash.service.RegionService;
import com.udianqu.wash.viewmodel.CitiesZoneVM;
import com.udianqu.wash.viewmodel.RegionVM;
 

@Controller
@RequestMapping("/general4App")
public class General4AppController {
	
	@Autowired BillSerialNoService billSerialNoService; 
	
	@RequestMapping(value = "getNextBillSerialNo.do",produces = "application/json;charset=UTF-8")
	public @ResponseBody String getNextBillSerialNo(
			@RequestParam(value = "billType", required = false) Integer billType,
			HttpServletRequest request
			){
		
		Map<String,Object> map=GeneralUtil.getSerialNoPars(billType);
		
		String r = billSerialNoService.getNextBillSerialNo(map);
		return r;
	}
	 
}
