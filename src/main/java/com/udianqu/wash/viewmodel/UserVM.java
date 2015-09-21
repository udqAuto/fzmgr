package com.udianqu.wash.viewmodel;

import java.math.BigDecimal;

import com.udianqu.wash.model.User;

public class UserVM extends User{
private String orgName;
private String username;
private BigDecimal amount;//充值或消费的金额
	
    
	public BigDecimal getAmount() {
	return amount;
}
public void setAmount(BigDecimal amount) {
	this.amount = amount;
}
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
