package com.udianqu.wash.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.GeneralUtil;
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

	public WashOrder save(WashOrderVM o) throws ParseException {
		// TODO Auto-generated method stub
		WashOrder wo = new WashOrder();
		Map<String,Object> map = GeneralUtil.getCurrentTime();
		Date time = (Date) map.get("currentTime");
		//订单主体对象构造；
		wo.setState(1);
		wo.setPayId(1);
		wo.setAcceptTime(time);
		wo.setOrderNo(o.getOrderNo());
		wo.setUserId(o.getUserId());
		wo.setUserNote(o.getUserNote());
		wo.setAutoId(o.getAutoId());
		wo.setRegionId(o.getRegionId());
		wo.setOrgId(o.getOrgId());
		wo.setOrderTime(o.getOrderTime());
		wo.setBillTime(time);
		washOrderMapper.insert(wo);
		
		WashOrderItem woi = new WashOrderItem();
		BigDecimal fixedAmount = o.getFixedAmount();
		BigDecimal couponAmount = o.getCouponAmount();
		BigDecimal finalAmount = fixedAmount.subtract(couponAmount);
		//订单金额对象构造；
		woi.setOrderId(wo.getId());
		woi.setFinalAmount(finalAmount);
		woi.setWashTypeId(o.getWashTypeId());
		woi.setFixedAmount(o.getFixedAmount());
		woi.setCouponId(o.getCouponId());
		woi.setCouponAmount(o.getCouponAmount());
		washOrderItemMapper.insert(woi);
		
		Pay p = new Pay();
		Integer amount = finalAmount.intValue(); 
		//订单金额支付对象构造；
		p.setOrderType(1);
		p.setPayType(1);
		p.setOrderId(wo.getId());
		p.setUserId(o.getUserId());
		p.setAmount(amount);
		payMapper.insert(p);
		
		return wo;
	}

	public void updateByOrderNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		washOrderMapper.updateByOrderNo(map);
	}

}
