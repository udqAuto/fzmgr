package com.udianqu.wash.viewmodel;

import com.udianqu.wash.model.User;

public class UserVM extends User{
private String orgName;
private String username;
	
	public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
