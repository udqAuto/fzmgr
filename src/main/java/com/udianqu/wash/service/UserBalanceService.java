package com.udianqu.wash.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.dao.UserBalanceMapper;
import com.udianqu.wash.model.User;
import com.udianqu.wash.model.UserBalance;
import com.udianqu.wash.viewmodel.UserBalanceVM;
import com.udianqu.wash.viewmodel.UserVM;

@Service
public class UserBalanceService {
	@Autowired UserBalanceMapper userBalanceMapper;
	@Autowired UserService userService;
	
	public void insert(UserBalance balance) {
		// TODO Auto-generated method stub
		userBalanceMapper.insert(balance);
	}

	public void save(UserBalanceVM balance) throws ParseException {
		// TODO Auto-generated method stub
		Map<String,Object> map = GeneralUtil.getCurrentTime();
		Date time = (Date) map.get("currentTime");
	    /*保存充值记录*/
		UserBalance ub = new UserBalance();
		ub.setAmount(balance.getAmount());
		ub.setOrderNo(balance.getOrderNo());
		ub.setRecordTime(time);
		ub.setUserId(balance.getUserId());
		ub.setType(1);//手机端=1；后台=2
		userBalanceMapper.insert(ub);
		/*更新余额*/
		UserVM user =new UserVM();
		user.setId(balance.getUserId());
		user.setAmount(balance.getAmount());
		userService.updateBalance(user);
	}

}
