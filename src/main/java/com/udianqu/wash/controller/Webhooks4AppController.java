package com.udianqu.wash.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;
import com.udianqu.wash.service.WashOrderService;

@Controller
@RequestMapping("/order")
public class Webhooks4AppController extends HttpServlet{
	
	@Autowired
	WashOrderService orderService;
	
	@Override
	@RequestMapping("/webhooks.do")
	@ResponseBody
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF8");
	        //获取头部所有信息
	        Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            String value = request.getHeader(key);
	            System.out.println(key+" "+value);
	        }
	        // 获得 http body 内容
	        BufferedReader reader = request.getReader();
	        StringBuffer buffer = new StringBuffer();
	        String string;
	        while ((string = reader.readLine()) != null) {
	            buffer.append(string);
	        }
	        reader.close();
	        // 解析异步通知数据
	        Event event = Webhooks.eventParse(buffer.toString());
	        Object s = event.getData().get("object");
	        String objStr =  s.toString();
	        String[] a = objStr.split(",");
	        String orderNo = a[8].replaceAll("order_no=", "").trim();
	        Map<String,Object> map=new HashMap<String, Object>();
	        map.put("orderNo", orderNo);
	        map.put("state", 1);
	        orderService.updateByOrderNo(map);
	        if ("charge.succeeded".equals(event.getType())) {
	            response.setStatus(200);
	        } else if ("refund.succeeded".equals(event.getType())) {
	            response.setStatus(200);
	        } else {
	            response.setStatus(500);
	        }
	    }
}
