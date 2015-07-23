package com.udianqu.wash.viewmodel;

import com.udianqu.wash.model.Auto;

public class AutoVM extends Auto{
    private String userName;
    private String regionName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
}
