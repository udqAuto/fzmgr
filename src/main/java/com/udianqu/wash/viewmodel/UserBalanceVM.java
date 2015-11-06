package com.udianqu.wash.viewmodel;

import com.udianqu.wash.model.UserBalance;

public class UserBalanceVM extends UserBalance {
	private String channel;
	private String userName;
	private String userMobile;
	private String userAuto;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserAuto() {
		return userAuto;
	}
	public void setUserAuto(String userAuto) {
		this.userAuto = userAuto;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
