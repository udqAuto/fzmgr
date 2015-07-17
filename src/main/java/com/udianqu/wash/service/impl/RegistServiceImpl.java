package com.udianqu.wash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.dao.UserMapper;
import com.udianqu.wash.model.User;
import com.udianqu.wash.service.RegistService;

@Service
public class RegistServiceImpl implements RegistService {
	
	@Autowired UserMapper userMapper;
	
	public void regist(User user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

}
