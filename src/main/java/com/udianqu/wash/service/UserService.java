package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.UserMapper;
import com.udianqu.wash.model.User; 

@Service
public class UserService {

	@Autowired UserMapper userMapper;
	
	public User loadUserByNameAndPwd(Map<String, Object> map) {
		return userMapper.loadUserByNameAndPwd(map);
	}
	
	public ListResult<User> loadUserlist(Map<String, Object> map) {
		int count=userMapper.countByMap(map);
		List<User> ls=userMapper.loadUserlistWithPage(map);

		ListResult<User> result=new ListResult<User>(count,ls);

		return result;
	}

}
