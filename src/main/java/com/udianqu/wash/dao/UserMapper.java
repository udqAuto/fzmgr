package com.udianqu.wash.dao;

import java.util.List;
import java.util.Map;

import com.udianqu.wash.core.MyBatisRepository;
import com.udianqu.wash.model.User;
import com.udianqu.wash.viewmodel.DirectorVM;
import com.udianqu.wash.viewmodel.UserVM;

@MyBatisRepository
public interface UserMapper {
  
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByName(String name);
	
	User selectByMobile(String mobile);

	int countByMap(Map<String, Object> map);

	List<UserVM> loadUserlistWithPage(Map<String, Object> map);

	UserVM loadUserByNameAndPwd(Map<String, Object> map);

	List<User> loadadminUserlist();

	List<DirectorVM> selectDirectorById(Integer id);

}