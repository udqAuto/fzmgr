package com.udianqu.wash.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

import com.udianqu.wash.core.Constants;
import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.core.TransPayType;
import com.udianqu.wash.dao.AutoMapper;
import com.udianqu.wash.dao.PayMapper;
import com.udianqu.wash.dao.RegionMapper;
import com.udianqu.wash.dao.UserBalanceMapper;
import com.udianqu.wash.dao.UserMapper;
import com.udianqu.wash.dao.WashOrderItemMapper;
import com.udianqu.wash.dao.WashOrderMapper;
import com.udianqu.wash.model.Auto;
import com.udianqu.wash.model.Pay;
import com.udianqu.wash.model.UserBalance;
import com.udianqu.wash.model.WashOrder;
import com.udianqu.wash.model.WashOrderItem;
import com.udianqu.wash.util.NotificationUtil;
import com.udianqu.wash.viewmodel.UserVM;
import com.udianqu.wash.viewmodel.WashOrderVM;

@Service
public class WashOrderService {
	@Autowired WashOrderMapper washOrderMapper;
	@Autowired WashOrderItemMapper washOrderItemMapper;
	@Autowired PayMapper payMapper;
	@Autowired RegionMapper regionMapper;
	@Autowired AutoMapper autoMapper;
	@Autowired UserBalanceMapper userBalanceMapper;
	@Autowired UserMapper userMapper;

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

	public WashOrderVM selectByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return washOrderMapper.selectByOrderNo(orderNo);
	}

	public WashOrderVM save(WashOrderVM o) throws ParseException {
		// TODO Auto-generated method stub
		WashOrder wo = new WashOrder();
		int payType = TransPayType.transToInt(o.getChannel());
		int orgId = regionMapper.selectOrgIdByRegionId(o.getRegionId());
		Map<String,Object> map = GeneralUtil.getCurrentTime();
		Date time = (Date) map.get("currentTime");
		/*订单主体对象构造；*/
		if(payType==100){//余额支付
			wo.setState(1);
		}else{
			wo.setState(0);
		}
		wo.setStateNote("客户已下单");
		wo.setPayId(1);
		wo.setOrderNo(o.getOrderNo());
		wo.setUserId(o.getUserId());
		wo.setUserNote(o.getUserNote());
		wo.setAutoId(o.getAutoId());
		wo.setRegionId(o.getRegionId());
		wo.setOrgId(orgId);
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
		//订单金额支付对象构造；
		p.setOrderType(1);
		p.setPayType(payType);
		p.setOrderId(wo.getId());
		p.setUserId(o.getUserId());
		p.setAmount(sumFinalAmount);
		payMapper.insert(p);
		//将计算过的各种金额总和放入OrderVM
		//o.setSumCouponAmount(sumCouponAmount);
		//o.setSumFinalAmount(sumFinalAmount);
		//o.setSumFixedAmount(sumFixedAmount);
		/*更新车位*/
		Auto auto =new Auto();
		auto.setId(o.getAutoId());
		auto.setPosition(o.getAutoPosition());
		autoMapper.updateByPrimaryKeySelective(auto);
		/*余额支付*/
		if(payType==100){
			BigDecimal t = new BigDecimal(-1);
			BigDecimal amount = sumFinalAmount.multiply(t);
			/*保存消费记录*/
			UserBalance ub = new UserBalance();
			ub.setAmount(amount);
			ub.setOrderNo(o.getOrderNo());
			ub.setRecordTime(time);
			ub.setUserId(o.getUserId());
			ub.setType(1);//手机端=1；后台=2
			ub.setState(1);
			userBalanceMapper.insert(ub);
			/*更新用户余额*/
			UserVM user =new UserVM();
			user.setId(o.getUserId());
			user.setAmount(amount);
			userMapper.updateBalance(user);
			//消息推送
			Map<String,Object> pushMap=new HashMap<String, Object>();
			pushMap.put("orgId", String.valueOf(orgId));
			pushMap.put("MESSAGE", "有新订单可接收！");
			try {
				NotificationUtil.SendPush(pushMap,Constants.PUSH_TYPE_WASHER);
			} catch (APIConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (APIRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		WashOrderVM order = washOrderMapper.selectByOrderNo(o.getOrderNo());
		order.setFinalAmount(sumFinalAmount);
		order.setFixedAmount(sumFixedAmount);
		return order;
	}
    /*
     * 支付完成，更改状态，消息推送
     * 
     * */
	public void updateByOrderNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		washOrderMapper.updateByOrderNo(map);
		//消息推送
		String orderNo = (String) map.get("orderNo");
		WashOrderVM order = washOrderMapper.selectByOrderNo(orderNo);
		Map<String,Object> pushMap=new HashMap<String, Object>();
		pushMap.put("orgId", order.getOrgId().toString());
		pushMap.put("MESSAGE", "有新订单可接收！");
		try {
			NotificationUtil.SendPush(pushMap,Constants.PUSH_TYPE_WASHER);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
     * 处理订单：接收、完成、取消、评价。消息推送
     * 
     * */
	public Result<WashOrder> handleOrder(WashOrderVM order) throws ParseException {
		Result<WashOrder> result = null;
		try{
			Integer state = order.getState();
			Map<String,Object> pushMap=new HashMap<String, Object>();
			Map<String,Object> m = GeneralUtil.getCurrentTime();
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("orderNo", order.getOrderNo());
			if(state == 2){//接收订单
				WashOrderVM o = washOrderMapper.selectByOrderNo(order.getOrderNo());
				if(o.getState() == 1){
					Date acceptTime = (Date) m.get("currentTime");
					map.put("washerId", order.getWasherId());
					map.put("washerNote", order.getWasherNote());
					map.put("acceptTime", acceptTime);
					map.put("state", state);
					map.put("stateNote", "门店已接受预约");
					//消息推送
					pushMap.put("customerId", order.getUserId().toString());
					pushMap.put("MESSAGE", "您好，您的"+order.getAutoPN()+"订单已被接收！");
					NotificationUtil.SendPush(pushMap,Constants.PUSH_TYPE_CUSTOMER);
				}else{
					result = new Result<WashOrder>(null, false, "此订单已被接收");
					return result;
				}
			}
			if(state == 4){//完成订单
				Date beginTime = (Date) m.get("beginTime");
				Date finishTime = (Date) m.get("currentTime");
				map.put("beginTime", beginTime);
				map.put("finishTime", finishTime);
				map.put("state", state);
				map.put("stateNote", "洗车已完成");
				map.put("washerNote", order.getWasherNote());
				//消息推送
				pushMap.put("customerId", order.getUserId().toString());
				pushMap.put("MESSAGE", "您好，您的"+order.getAutoPN()+"洗车完成！");
				NotificationUtil.SendPush(pushMap,Constants.PUSH_TYPE_CUSTOMER);
			}
			if(state == 5){//评价
				map.put("gradeUser", order.getGradeUser());
				map.put("gradeUserNote", order.getGradeUserNote());
				map.put("state", state);
			}
			if(state == 10||state == 11){//取消订单
				map.put("state", state);
				map.put("stateNote", "订单已取消");
				//消息推送
				pushMap.put("customerId", order.getUserId().toString());
				pushMap.put("MESSAGE", "您好，您的"+order.getAutoPN()+"订单已取消！");
				NotificationUtil.SendPush(pushMap,Constants.PUSH_TYPE_CUSTOMER);
			}
			washOrderMapper.updateByOrderNo(map);
			result = new Result<WashOrder>(null, true, "操作成功");
			return result;
		}catch(Exception ex){
			result = new Result<WashOrder>(null, false, "操作失败");
			return result;
		}
		
	}

	public ListResult<WashOrderVM> getOrderByUserId(Integer userId, String orderDate) throws ParseException {
		Map<String,Object> m = GeneralUtil.getCurrentTime();
		Date currentDate = (Date) m.get("currentTime");
		Date monthAgo = (Date) m.get("monthAgo");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		if("recently".equals(orderDate)){
			map.put("startDate", monthAgo);
			map.put("endDate", currentDate);
		}
		if("history".equals(orderDate)){
			map.put("startDate", "2015-01-01 00:00:00");
			map.put("endDate", monthAgo);
		}
		List<WashOrderVM> ls=washOrderMapper.getOrderByUserId(map);
		ListResult<WashOrderVM> result=new ListResult<WashOrderVM>(ls);
		return result;
	}

	public ListResult<WashOrderVM> getOrderByState(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<WashOrderVM> ls=washOrderMapper.getOrderByMap(map);
		ListResult<WashOrderVM> result=new ListResult<WashOrderVM>(ls);
		return result;
	}

	public void deletePhoto(String orderNo, Integer No) throws ParseException {
		//删除数据库中的
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		switch(No){
		case 1:
			map.put("photoUrl1", "");break;
		case 2:
			map.put("photoUrl2", "");break;
		case 3:
			map.put("photoUrl3", "");break;
		}
		washOrderMapper.updateByOrderNo(map);
		//删除文件
		Map<String,Object> m = GeneralUtil.getCurrentDate();
        String date = (String) m.get("currentDate");
        String path=getClass().getResource("/").getPath();
        String rootPath = path.substring(0,path.lastIndexOf("WEB-INF"));
        String photoUrl = rootPath+"washPhoto/"+date+"/"+orderNo+"/"+orderNo+"_"+No+".jpg";
		boolean success = (new File(photoUrl)).delete();
	}
}
