package com.udianqu.wash.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.core.TransPayType;
import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.PayMapper;
import com.udianqu.wash.dao.WashOrderItemMapper;
import com.udianqu.wash.dao.WashOrderMapper;
import com.udianqu.wash.model.Pay;
import com.udianqu.wash.model.WashOrder;
import com.udianqu.wash.model.WashOrderItem;
import com.udianqu.wash.viewmodel.WashOrderVM;

@Service
public class WashOrderService {
	
	@Autowired WashOrderMapper washOrderMapper;
	@Autowired WashOrderItemMapper washOrderItemMapper;
	@Autowired PayMapper payMapper;

	public ListResult<WashOrderVM> loadOrderlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int count = washOrderMapper.loadWashOrderCount(map);
		List<WashOrderVM> ls=washOrderMapper.loadWashOrderlistWithPage(map);
		ListResult<WashOrderVM> result=new ListResult<WashOrderVM>(count,ls);
		return result;
	}

	public WashOrderVM loadOrderObjById(Integer id) {
		// TODO Auto-generated method stub
		return washOrderMapper.loadOrderObjById(id);
	}

	public void insert(WashOrderVM order) {
		// TODO Auto-generated method stub
		washOrderMapper.insert(order);
	}

	public WashOrder selectByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return washOrderMapper.selectByOrderNo(orderNo);
	}

	public WashOrderVM save(WashOrderVM o) throws ParseException {
		// TODO Auto-generated method stub
		WashOrder wo = new WashOrder();
		Map<String,Object> map = GeneralUtil.getCurrentTime();
		Date time = (Date) map.get("currentTime");
		//订单主体对象构造；
		wo.setState(0);
		wo.setStateNote("客户已下单");
		wo.setPayId(1);
		wo.setOrderNo(o.getOrderNo());
		wo.setUserId(o.getUserId());
		wo.setUserNote(o.getUserNote());
		wo.setAutoId(o.getAutoId());
		wo.setRegionId(o.getRegionId());
		wo.setOrgId(o.getOrgId());
		wo.setOrderTime(time);
		wo.setBillTime(time);
		washOrderMapper.insert(wo);
		
		WashOrderItem woi = new WashOrderItem();
		Pay p = new Pay();
		BigDecimal sumFinalAmount = new BigDecimal(0);
		BigDecimal sumFixedAmount = new BigDecimal(0);
		//BigDecimal sumCouponAmount = new BigDecimal(0);
		List fixedAmounts = o.getFixedAmounts();
		//List<Double> couponAmounts = o.getCouponAmounts();
		//List<Integer> couponIds = o.getCouponIds();
		List<Integer> washTypeIds = o.getWashTypeIds();
		 
		for(int i=0;i<washTypeIds.size();i++){
			Object fixedAmount1=fixedAmounts.get(i);
			BigDecimal fixedAmount = new BigDecimal(fixedAmount1.toString());
			//BigDecimal couponAmount = new BigDecimal(couponAmounts.get(i));
			BigDecimal couponAmount = new BigDecimal(0);
			BigDecimal finalAmount = fixedAmount.subtract(couponAmount);
			sumFixedAmount = sumFixedAmount.add(fixedAmount);
			//sumCouponAmount = sumCouponAmount.add(couponAmount);
			sumFinalAmount = sumFinalAmount.add(finalAmount);
			
			woi.setOrderId(wo.getId());
			woi.setFinalAmount(finalAmount);
			woi.setWashTypeId(washTypeIds.get(i));
			woi.setFixedAmount(fixedAmount);
			//woi.setCouponId(couponIds.get(i));
			//woi.setCouponAmount(couponAmount);
			washOrderItemMapper.insert(woi);
			
			
		}
		int payType = TransPayType.transToInt(o.getChannel());
		Integer amount = sumFinalAmount.intValue(); 
		//订单金额支付对象构造；
		p.setOrderType(1);
		p.setPayType(payType);
		p.setOrderId(wo.getId());
		p.setUserId(o.getUserId());
		p.setAmount(amount);
		payMapper.insert(p);
		//将计算过的各种金额总和放入OrderVM
		//o.setSumCouponAmount(sumCouponAmount);
		o.setSumFinalAmount(sumFinalAmount);
		o.setSumFixedAmount(sumFixedAmount);
		return o;
	}

	public void updateByOrderNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		washOrderMapper.updateByOrderNo(map);
	}

	public void handleOrder(WashOrderVM order) throws ParseException {
		// TODO Auto-generated method stub
		Integer state = order.getState();
		Map<String,Object> m = GeneralUtil.getCurrentTime();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("orderNo", order.getOrderNo());
		if(state == 2){//接收订单
			Date acceptTime = (Date) m.get("currentTime");
			map.put("washerId", order.getWasherId());
			map.put("washerNote", order.getWasherNote());
			map.put("acceptTime", acceptTime);
			map.put("state", state);
			map.put("stateNote", "门店已接受预约");
		}
		if(state == 4){//完成订单
			Date beginTime = (Date) m.get("beginTime");
			Date finishTime = (Date) m.get("currentTime");
			map.put("beginTime", beginTime);
			map.put("finishTime", finishTime);
			map.put("state", state);
			map.put("stateNote", "洗车已完成");
		}
		if(state == 5){//评价
			map.put("gradeUser", order.getGradeUser());
			map.put("state", state);
		}
		if(state == 10||state == 11){//取消订单
			map.put("state", state);
			map.put("stateNote", "订单已取消");
		}
		washOrderMapper.updateByOrderNo(map);
	}

	public ListResult<WashOrderVM> getOrderByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<WashOrderVM> ls=washOrderMapper.getOrderByUserId(userId);
		ListResult<WashOrderVM> result=new ListResult<WashOrderVM>(ls);
		return result;
	}

	public ListResult<WashOrderVM> getOrderByState(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<WashOrderVM> ls=washOrderMapper.getOrderByMap(map);
		ListResult<WashOrderVM> result=new ListResult<WashOrderVM>(ls);
		return result;
	}
}
