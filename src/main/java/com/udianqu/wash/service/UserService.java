package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.UserMapper;
import com.udianqu.wash.model.User; 
import com.udianqu.wash.viewmodel.DirectorVM;
import com.udianqu.wash.viewmodel.UserVM;
import com.udianqu.wash.viewmodel.WashOrderVM;

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
	
	public void updateById(User user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);
	}

	public User insert(UserVM user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
		return user;
	}

	public int deleteUser(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	public List<User> loadadminUserlist() {
		// TODO Auto-generated method stub
		return userMapper.loadadminUserlist();
	}

	public User selectByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectByName(name);
	}

	public User selectByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userMapper.selectByMobile(mobile);
	}

	public ListResult<DirectorVM> selectDirectorById(Integer customerId) {
		// TODO Auto-generated method stub
		List<DirectorVM> ls=userMapper.selectDirectorById(customerId);
		ListResult<DirectorVM> result=new ListResult<DirectorVM>(ls);
		return result;
	}

	public User selectById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	public void updateBalance(UserVM user) {
		// TODO Auto-generated method stub
		userMapper.updateBalance(user);
	}

}
