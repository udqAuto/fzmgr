package com.udianqu.wash.viewmodel;

import com.udianqu.wash.model.UserBalance;

public class UserBalanceVM extends UserBalance {
	private String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
