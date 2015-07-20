package com.udianqu.wash.dao;

import com.udianqu.wash.core.MyBatisRepository;
import com.udianqu.wash.model.User;

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
}