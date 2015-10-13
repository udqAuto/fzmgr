package com.udianqu.wash.viewmodel;

import java.math.BigDecimal;

import com.udianqu.wash.model.User;

public class UserVM extends User{
private String orgName;
private String username;
private BigDecimal amount;//充值或消费的金额
private BigDecimal totalAmount;
private Integer washCount;
	
    
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
