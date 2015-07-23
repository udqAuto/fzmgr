package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.UserMapper;
import com.udianqu.wash.model.User; 
import com.udianqu.wash.viewmodel.UserVM;

@Service
public class UserService {

	@Autowired UserMapper userMapper;
	
	public UserVM loadUserByNameAndPwd(Map<String, Object> map) {
		return userMapper.loadUserByNameAndPwd(map);
	}
	
	public ListResult<UserVM> loadUserlist(Map<String, Object> map) {
		int count=userMapper.countByMap(map);
		List<UserVM> ls=userMapper.loadUserlistWithPage(map);

		ListResult<UserVM> result=new ListResult<UserVM>(count,ls);

		return result;
	}

	public void updateByPrimaryKey(UserVM user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKey(user);
	}

	public void insert(UserVM user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

	public int deleteUser(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	public List<User> loadadminUserlist() {
		// TODO Auto-generated method stub
		return userMapper.loadadminUserlist();
	}

}
