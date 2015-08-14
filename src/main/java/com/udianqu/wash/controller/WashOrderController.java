package com.udianqu.wash.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.Organization;
import com.udianqu.wash.model.WashOrder;
import com.udianqu.wash.service.BillSerialNoService;
import com.udianqu.wash.service.OrganService;
import com.udianqu.wash.service.WashOrderService;
import com.udianqu.wash.viewmodel.WashOrderVM;

@Controller
@RequestMapping("/order")
public class WashOrderController{

	@Autowired
	WashOrderService orderService;
	@Autowired
	BillSerialNoService billSerialNoService;

	@Autowired
	OrganService organService;

	@RequestMapping(value = "getOrderList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getOrderList(
			@RequestParam(value = "order_Query", required = false) String order_Query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {

		JSONObject joQuery = JSONObject.fromObject(order_Query);

		Map<String, Object> map = new HashMap<String, Object>();
		page = page == 0 ? 1 : page;
		map.put("pageStart", (page - 1) * rows);
		map.put("pageSize", rows);
		int orgId = joQuery.getInt("orgId");
		int orderType = joQuery.getInt("orderType");
		Organization o = organService.selectByPrimaryKey(orgId);
		map.put("orgId", orgId);
		map.put("orgPath", o.getPath() == null ? (orgId+"") : o.getPath());

		String startTime = joQuery.getString("startTime");
		String endTime = joQuery.getString("endTime");
		int orderState = joQuery.getInt("orderState");
		int cancelType = joQuery.getInt("cancelType");

		if (endTime != null && endTime != " " && endTime.length() > 0) {
			endTime += " 23:59:59";
		}
		if (!"".equals(startTime)) {
			map.put("startTime", startTime);
		}
		if (!"".equals(endTime)) {
			map.put("endTime", endTime);
		}
		List<Integer> ids = new ArrayList<Integer>();
		if (orderType == 1) {
			ids.add(4);
		} else if (orderType == 2) {
			if (orderState > 0) {
				ids.add(orderState);
			} else {
				ids.add(1);
				ids.add(2);
				ids.add(3);
			}
		} else {
			if (cancelType > 0) {
				ids.add(cancelType);
			} else {
				ids.add(10);
				ids.add(11);
			}
		}
		map.put("stateIds", ids);

		ListResult<WashOrderVM> rs = orderService.loadOrderlist(map); 
		return rs.toJson();
	}
	@RequestMapping(value = "getOrderObjById.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getOrderObjById(
			@RequestParam(value = "id", required = false) Integer orderId, 
			HttpServletRequest request) throws Exception {
		try{
			WashOrderVM wovm = orderService.loadOrderObjById(orderId);
			Result<WashOrderVM> s = new Result<WashOrderVM>(wovm, true,"查询订单信息成功");
			return s.toJson();
		}catch(Exception ex){
			Result<WashOrderVM> s = new Result<WashOrderVM>(null, false,"查询订单信息失败");
			return s.toJson();
		} 
	}
	@RequestMapping(value = "getOrderByUserId4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getOrderByUserId4App(
			@RequestParam(value = "userId", required = true) Integer userId,
			HttpServletRequest request){
		try{
			ListResult<WashOrderVM> result = orderService.getOrderByUserId(userId);
			return result.toJson();
		}catch(Exception ex){
			ListResult<WashOrderVM> result = new ListResult<WashOrderVM>(null,false,"获取订单信息失败");
			return result.toJson();
		} 
	}
	@RequestMapping(value = "getOrderByState4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getOrderByState4App(
			@RequestParam(value = "orderInfo", required = true) String orderInfo,
			HttpServletRequest request){
		try{
			JSONObject jObj = JSONObject.fromObject(orderInfo);
			WashOrderVM order = (WashOrderVM) JSONObject.toBean(jObj,WashOrderVM.class);
			Map<String, Object> map = new HashMap<String, Object>();    
			map.put("state", order.getState());  
			map.put("states", order.getStates());  
			map.put("washerId", order.getWasherId());  
			ListResult<WashOrderVM> result = orderService.getOrderByState(map);
			return result.toJson();
		}catch(Exception ex){
			ListResult<WashOrderVM> result = new ListResult<WashOrderVM>(null,false,"获取订单信息失败");
			return result.toJson();
		} 
	}
	
	@RequestMapping("/submitOrder4App.do")
	@ResponseBody
	public String saveOrder4App(
			@RequestParam(value = "orderInfo", required = true) String orderInfo,
			HttpServletRequest request) {

		Result<WashOrderVM> result = new Result<WashOrderVM>();
		try {
			JSONObject jObj = JSONObject.fromObject(orderInfo);
			WashOrderVM order = (WashOrderVM) JSONObject.toBean(jObj,WashOrderVM.class);
			if(order == null){
				result = new Result<WashOrderVM>(null, false, false, false, "传入后台数据为空");
				return result.toJson();
			}
			int billType = 4;
			Map<String,Object> map=GeneralUtil.getSerialNoPars(billType);
			String orderNo =  billSerialNoService.getNextBillSerialNo(map);
			order.setOrderNo(orderNo);
			Charge charge = chargeCreate(order);
			WashOrderVM wovm = orderService.save(order);
			billSerialNoService.updateBillSerialNo(map);
			wovm.setCharge(charge);
			result = new Result<WashOrderVM>(wovm, true, false, false, "保存成功");
			return result.toJson();
		} catch (Exception ex) {
			result = new Result<WashOrderVM>(null, false, false, false,
					ex.getCause().getMessage());
			return result.toJson();
		}
	}
	
	@RequestMapping("/handleOrder4App.do")
	@ResponseBody
	public String handleOrder4App(
			@RequestParam(value = "orderInfo", required = true) String orderInfo,
			HttpServletRequest request) {
		Result<WashOrder> result = new Result<WashOrder>();
		try {
			JSONObject jObj = JSONObject.fromObject(orderInfo);
			WashOrderVM order = (WashOrderVM) JSONObject.toBean(jObj,WashOrderVM.class);
			if(order == null||order.getOrderNo()==null||order.getState()==null){
				result = new Result<WashOrder>(null, false, false, false, "传入后台数据为空");
				return result.toJson();
			}
			orderService.handleOrder(order);
			result = new Result<WashOrder>(null, true, false, false, "操作成功");
			return result.toJson();
		} catch (Exception ex) {
			result = new Result<WashOrder>(null, false, false, false,
					"调用后台方法出错");
			return result.toJson();
		}
	}
	
	public Charge chargeCreate(WashOrderVM order) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException{
		BigDecimal amount = countAmount(order);
		BigDecimal t = new BigDecimal(100);
		System.out.println(amount);
		Pingpp.apiKey = "sk_test_SKenfLLGeHiPjzTC0OfrjjDG";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
	    chargeParams.put("order_no", order.getOrderNo());
	    chargeParams.put("amount",amount.multiply(t).intValue());
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", "app_5CWvTSPubXHSeLyH");
	    chargeParams.put("app",app);
	    chargeParams.put("channel",order.getChannel());
	    chargeParams.put("currency","cny");
	    chargeParams.put("client_ip","192.168.1.101");
	    chargeParams.put("subject","点趣洗车");
	    chargeParams.put("body","点趣洗车订单支付");
	    return Charge.create(chargeParams);
	}
	/*
	 * 计算结算金额
	 * 
	 * */
	public BigDecimal countAmount(WashOrderVM order){
		BigDecimal sumFinalAmount = new BigDecimal(0);
		List fixedAmounts = order.getFixedAmounts();
		for(int i=0;i<fixedAmounts.size();i++){
			Object fixedAmount1=fixedAmounts.get(i);
			BigDecimal fixedAmount = new BigDecimal(fixedAmount1.toString());
			//BigDecimal couponAmount = new BigDecimal(couponAmounts.get(i));
			BigDecimal couponAmount = new BigDecimal(0);
			BigDecimal finalAmount = fixedAmount.subtract(couponAmount);
			//sumFixedAmount = sumFixedAmount.add(fixedAmount);
			//sumCouponAmount = sumCouponAmount.add(couponAmount);
			sumFinalAmount = sumFinalAmount.add(finalAmount);
		}
		return sumFinalAmount;
	}
}
