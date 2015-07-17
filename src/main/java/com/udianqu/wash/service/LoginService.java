package com.udianqu.wash.service;

import com.udianqu.wash.model.User;

public interface LoginService {

	boolean login(String username, String password);
	
	User getLoginInfo(String name);

}
