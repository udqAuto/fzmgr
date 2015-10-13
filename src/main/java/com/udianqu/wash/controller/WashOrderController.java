package com.udianqu.wash.controller;

import java.io.UnsupportedEncodingException;
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
import com.pingplusplus.util.WxpubOAuth;
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
		String customerMobile = joQuery.getString("customerMobile");
		//int orderState = joQuery.getInt("orderState");
		//int cancelType = joQuery.getInt("cancelType");

		if (endTime != null && endTime != " " && endTime.length() > 0) {
			endTime += " 23:59:59";
		}
		if (!"".equals(startTime)) {
			map.put("startTime", startTime);
		}
		if (!"".equals(endTime)) {
			map.put("endTime", endTime);
		}
		if (!"".equals(customerMobile)) {
			map.put("customerMobile", customerMobile);
		}
		List<Integer> ids = new ArrayList<Integer>();
		if (orderType == 1) {//新订单
			ids.add(1);
		} else if (orderType == 2) {//进行中
			ids.add(2);
			ids.add(3);
		} else if(orderType == 4){//已完成
			ids.add(4);
		}else if(orderType == 5){//已评价
			ids.add(5);
		}
		map.put("stateIds", ids);

		ListResult<WashOrderVM> rs = orderService.loadOrderlist(map); 
		return rs.toJson();
	}
	/**
	 * 在车辆查看中显示该车辆的订单
	 * @param aId 车辆Id
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getOrderListByAutoId.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getOrderListByAutoId(
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
		Organization o = organService.selectByPrimaryKey(orgId);
		map.put("orgId", orgId);
		map.put("orgPath", o.getPath() == null ? (orgId+"") : o.getPath());
		
		int autoId = joQuery.getInt("autoId");
		map.put("autoId", autoId);
		
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
	@RequestMapping(value = "deletePhoto4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deletePhoto(
			@RequestParam(value = "orderNo", required = true) String orderNo, 
			@RequestParam(value = "No", required = true) Integer No, 
			HttpServletRequest request) throws Exception {
		Result<WashOrder> result = null;
		try{
			orderService.deletePhoto(orderNo,No);
			result = new Result<WashOrder>(null, true,"删除成功");
			return result.toJson();
		}catch(Exception ex){
			result = new Result<WashOrder>(null, false,"删除失败");
			return result.toJson();
		} 
	}
	@RequestMapping(value = "getOrderByUserId4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getOrderByUserId4App(
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "orderDate", required = true) String orderDate,
			HttpServletRequest request){
		try{
			ListResult<WashOrderVM> result = orderService.getOrderByUserId(userId,orderDate);
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
			if(order==null){
				Result<WashOrderVM> res = new Result<WashOrderVM>(null,false,"传入后台数据为空");
				return res.toJson();
			}
			Map<String, Object> map = new HashMap<String, Object>();    
			map.put("state", order.getState());  
			map.put("states", order.getStates());  
			map.put("washerId", order.getWasherId());  
			map.put("orgId", order.getOrgId());  
			ListResult<WashOrderVM> result = orderService.getOrderByState(map);
			return result.toJson();
		}catch(Exception ex){
			ListResult<WashOrderVM> result = new ListResult<WashOrderVM>(null,false,"获取订单信息失败");
			return result.toJson();
		} 
	}
	
	@RequestMapping(value = "submitOrder4App.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveOrder4App(
			@RequestParam(value = "orderInfo", required = true) String orderInfo,
			HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		Result<WashOrderVM> result = new Result<WashOrderVM>();
		try {
			JSONObject jObj = JSONObject.fromObject(orderInfo);
			WashOrderVM order = (WashOrderVM) JSONObject.toBean(jObj,WashOrderVM.class);
			if(order == null){
				result = new Result<WashOrderVM>(null, false, false, false, "传入后台数据为空");
				return result.toJson();
			}
			WashOrderVM wovm = new WashOrderVM();
			//orderNo为空则是新提交的订单，需要生成orderNo，将信息保存到数据库；
			//若orderNo不空则是未支付的订单再次支付，上一次已经保存了信息到数据库，此时不用再保存。
			if(order.getOrderNo() == null||order.getOrderNo() == ""){
				Map<String,Object> map=GeneralUtil.getSerialNoPars(1);//billType = 1
				String orderNo =  billSerialNoService.getNextBillSerialNo(map);
				order.setOrderNo(orderNo);
				wovm = orderService.save(order);
				billSerialNoService.updateBillSerialNo(map);
			}else{
				wovm = orderService.selectByOrderNo(order.getOrderNo());
				wovm.setFinalAmount(order.getFinalAmount());
			}
			if(!"udq".equals(order.getChannel())){
				try{
					Charge charge = chargeCreate(order,ip);
					wovm.setCharge(charge);
				}catch(Exception ex){
					result = new Result<WashOrderVM>(null, false, false, false,
							ex.getCause().getMessage());
					return result.toJson(); 
				}
			}
			result = new Result<WashOrderVM>(wovm, true, false, false, "保存成功");
			return result.toJson();
		} catch (Exception ex) {
			result = new Result<WashOrderVM>(null, false, false, false,
					ex.getCause().getMessage());
			return result.toJson();
		}
	}
	
	@RequestMapping(value = "handleOrder4App.do",produces = "application/json;charset=UTF-8")
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
			Result<WashOrder> res = orderService.handleOrder(order);
			return res.toJson();
		} catch (Exception ex) {
			result = new Result<WashOrder>(null, false, false, false,
					"调用后台方法出错");
			return result.toJson();
		}
	}
	
	public Charge chargeCreate(WashOrderVM order,String ip) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException, UnsupportedEncodingException{
		BigDecimal amount =null;
		if(order.getFinalAmount()!=null){
			amount = order.getFinalAmount();
		}else{
			amount = countAmount(order);
		}
		BigDecimal t = new BigDecimal(100);
		//System.out.println(amount);
		Pingpp.apiKey = "sk_live_EURSbiebpWWpatTR2mQflnRh";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
	    chargeParams.put("order_no", order.getOrderNo());
	    chargeParams.put("amount",amount.multiply(t).intValue());//amount.multiply(t).intValue()
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", "app_5CWvTSPubXHSeLyH");
	    chargeParams.put("app",app);
	    chargeParams.put("channel",order.getChannel());
	    chargeParams.put("currency","cny");
	    chargeParams.put("client_ip",ip);
	    chargeParams.put("subject","点趣车生活");
	    chargeParams.put("body","点趣车生活");
//	    Map<String, String> extra = new HashMap<String, String>();
//	    String openId = WxpubOAuth.getOpenId("app_5CWvTSPubXHSeLyH","5543cfeff66382f4c2e503f596ee976f",WxpubOAuth.createOauthUrlForCode("app_5CWvTSPubXHSeLyH","5543cfeff66382f4c2e503f596ee976f",false));
//	    extra.put("open_id", openId);
//	    chargeParams.put("extra", extra);

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
