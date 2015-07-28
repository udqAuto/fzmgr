package com.udianqu.wash.viewmodel;

import com.udianqu.wash.model.WashOrder;

public class WashOrderVM extends WashOrder{
	private String customerName; 
	private String autoPN;
	private String customerMobile;
	private String regionName;
	private String washerName;
	private String washerTypeName;
	private String washerGrade;
	private String customerGrade; 
	private String couponName;
	private String couponAmount;
	private String fixedAmount;
	private String finalAmount;
	
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getWasherName() {
		return washerName;
	}
	public void setWasherName(String washerName) {
		this.washerName = washerName;
	}
	public String getWasherGrade() {
		return washerGrade;
	}
	public void setWasherGrade(String washerGrade) {
		this.washerGrade = washerGrade;
	}
	public String getCustomerGrade() {
		return customerGrade;
	}
	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(String fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	public String getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(String finalAmount) {
		this.finalAmount = finalAmount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAutoPN() {
		return autoPN;
	}
	public void setAutoPN(String autoPN) {
		this.autoPN = autoPN;
	}
	public String getWasherTypeName() {
		return washerTypeName;
	}
	public void setWasherTypeName(String washerTypeName) {
		this.washerTypeName = washerTypeName;
	}
}
