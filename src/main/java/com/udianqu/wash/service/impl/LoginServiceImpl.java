package com.udianqu.wash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.dao.UserMapper;
import com.udianqu.wash.model.User;
import com.udianqu.wash.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired UserMapper userMapper;

	
	public boolean login(String username, String password) {

		String userName = "admin";
		String passWord = "admin";
		if (userName.equals(username) && passWord.equals(password)) {
			return true;
		} else
			return false;
	}

	public User getLoginInfo(String username) {
		return userMapper.selectByName(username);
	}

}
