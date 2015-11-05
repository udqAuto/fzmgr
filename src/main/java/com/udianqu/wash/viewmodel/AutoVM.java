package com.udianqu.wash.viewmodel;

import java.math.BigDecimal;

import com.udianqu.wash.model.Auto;

public class AutoVM extends Auto{
    private String userName;
    private String regionName;
    private String userMobile;
    private BigDecimal totalAmount;
    private Integer washCount;

    
    
	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getWashCount() {
		return washCount;
	}

	public void setWashCount(Integer washCount) {
		this.washCount = washCount;
	}

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
