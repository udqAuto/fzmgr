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
import com.udianqu.wash.model.User;
import com.udianqu.wash.service.BillSerialNoService;
import com.udianqu.wash.service.UserBalanceService;
import com.udianqu.wash.service.UserService;
import com.udianqu.wash.viewmodel.UserBalanceVM;
import com.udianqu.wash.viewmodel.UserVM;

@Controller
@RequestMapping("/userBalance")
public class UserBalanceController {
	@Autowired
	BillSerialNoService billSerialNoService;
	@Autowired UserBalanceService userBalanceService;
	@Autowired UserService userService;
	
	@RequestMapping(value = "getBalanceList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getBalanceList(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "balanceInfo", required = false) String balanceInfo,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject jObj = JSONObject.fromObject(balanceInfo);
			UserBalanceVM balance = (UserBalanceVM) JSONObject.toBean(jObj,UserBalanceVM.class);
			String userName = balance.getUserName();
			String userMobile = balance.getUserMobile();
			String orderNo = balance.getOrderNo();
			//Organization o = organService.selectByPrimaryKey(orgId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			//map.put("orgPath", o.getPath() == null ? (orgId+"") : o.getPath());
			
			if(!"".equals(userName)){
				map.put("userName", userName);
			}
			if(!"".equals(userMobile)){
				map.put("userMobile", userMobile);
			}
			ListResult<UserBalanceVM> rs = userBalanceService.loadBalancelist(map);
			return rs.toJson();
		} catch (Exception ex) {
			ListResult<UserBalanceVM> rs = new ListResult<UserBalanceVM>(0, null);
			return rs.toJson();
		}
	}
	@RequestMapping(value = "recharge.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String recharge(UserBalanceVM balance,
			HttpServletRequest request) {
		Result<UserBalanceVM> result = new Result<UserBalanceVM>();
		try {
			Map<String,Object> map=GeneralUtil.getSerialNoPars(2);//billType = 2
			String orderNo =  billSerialNoService.getNextBillSerialNo(map);
			balance.setOrderNo(orderNo);
			userBalanceService.insertBalance(balance);
			/*更新用户余额*/
        	UserVM user =new UserVM();
    		user.setId(balance.getUserId());
    		user.setAmount(balance.getAmount());
			userService.updateBalance(user);
			billSerialNoService.updateBillSerialNo(map);
			return result.toJson();
		} catch (Exception ex) {
			result = new Result<UserBalanceVM>(null, false, false, false,
					ex.getCause().getMessage());
			return result.toJson();
		}
	}
	@RequestMapping(value = "recharge4App.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String recharge4App(
			@RequestParam(value = "balanceInfo", required = true) String balanceInfo,
			HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		Result<UserBalanceVM> result = new Result<UserBalanceVM>();
		try {
			JSONObject jObj = JSONObject.fromObject(balanceInfo);
			UserBalanceVM balance = (UserBalanceVM) JSONObject.toBean(jObj,UserBalanceVM.class);
			if(balance == null){
				result = new Result<UserBalanceVM>(null, false, false, false, "传入后台数据为空");
				return result.toJson();
			}
			Map<String,Object> map=GeneralUtil.getSerialNoPars(2);//billType = 2
			String orderNo =  billSerialNoService.getNextBillSerialNo(map);
			balance.setOrderNo(orderNo);
			userBalanceService.save(balance);
			billSerialNoService.updateBillSerialNo(map);
			Charge charge = new Charge();
			try{
				charge =chargeCreate(balance,ip);
			}catch(Exception ex){
				result = new Result<UserBalanceVM>(null, false, false, false,
						ex.getCause().getMessage());
				return result.toJson(); 
			}
			result = new Result(charge, true, false, false, "保存成功");
			return result.toJson();
		} catch (Exception ex) {
			result = new Result<UserBalanceVM>(null, false, false, false,
					ex.getCause().getMessage());
			return result.toJson();
		}
	}

	private Charge chargeCreate(UserBalanceVM balance, String ip) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {
		BigDecimal amount =balance.getAmount();
		BigDecimal t = new BigDecimal(100);
		//System.out.println(amount);
		Pingpp.apiKey = "sk_live_EURSbiebpWWpatTR2mQflnRh";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
	    chargeParams.put("order_no", balance.getOrderNo());
	    chargeParams.put("amount",amount.multiply(t).intValue());//amount.multiply(t).intValue()
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", "app_5CWvTSPubXHSeLyH");
	    chargeParams.put("app",app);
	    chargeParams.put("channel",balance.getChannel());
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

}
