package com.udianqu.wash.viewmodel;

import java.math.BigDecimal;
import java.util.List;

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
	private BigDecimal couponAmount;
	private BigDecimal fixedAmount;
	private BigDecimal finalAmount;
	private Integer couponId;
	private Integer washTypeId;
	
	private BigDecimal sumCouponAmount;
	private BigDecimal sumFixedAmount;
	private BigDecimal sumFinalAmount;
	
	private List<Integer> washTypeIds;
	private List<Integer> couponIds;
	private List<Double> couponAmounts;
	private List<Double> fixedAmounts;
	
	public BigDecimal getSumCouponAmount() {
		return sumCouponAmount;
	}
	public void setSumCouponAmount(BigDecimal sumCouponAmount) {
		this.sumCouponAmount = sumCouponAmount;
	}
	public BigDecimal getSumFixedAmount() {
		return sumFixedAmount;
	}
	public void setSumFixedAmount(BigDecimal sumFixedAmount) {
		this.sumFixedAmount = sumFixedAmount;
	}
	public BigDecimal getSumFinalAmount() {
		return sumFinalAmount;
	}
	public void setSumFinalAmount(BigDecimal sumFinalAmount) {
		this.sumFinalAmount = sumFinalAmount;
	}
	public List<Integer> getCouponIds() {
		return couponIds;
	}
	public void setCouponIds(List<Integer> couponIds) {
		this.couponIds = couponIds;
	}
	
	public List<Double> getCouponAmounts() {
		return couponAmounts;
	}
	public void setCouponAmounts(List<Double> couponAmounts) {
		this.couponAmounts = couponAmounts;
	}
	public List<Double> getFixedAmounts() {
		return fixedAmounts;
	}
	public void setFixedAmounts(List<Double> fixedAmounts) {
		this.fixedAmounts = fixedAmounts;
	}
	public List<Integer> getWashTypeIds() {
		return washTypeIds;
	}
	public void setWashTypeIds(List<Integer> washTypeIds) {
		this.washTypeIds = washTypeIds;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getWashTypeId() {
		return washTypeId;
	}
	public void setWashTypeId(Integer washTypeId) {
		this.washTypeId = washTypeId;
	}
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
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}
	public BigDecimal getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	public BigDecimal getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(BigDecimal finalAmount) {
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
