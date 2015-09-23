package com.udianqu.wash.viewmodel;

import java.math.BigDecimal;
import java.util.List;

import com.udianqu.wash.model.WashOrder;

public class WashOrderVM extends WashOrder{
	private String customerName; 
	private String autoPN;
	private String autoBrand;
	private String autoModel;
	private String autoPosition;
	private String customerMobile;
	private String shopName;
	private String regionName;
	private String washerName;
	private String washTypeName;
	private String washerGrade;
	private String customerGrade; 
	private String couponName;
	private BigDecimal couponAmount;
	private BigDecimal fixedAmount;
	private BigDecimal finalAmount;
	private Integer couponId;
	private Integer washTypeId;
	private String payType;
	private List<Integer> states;
	
	private BigDecimal sumCouponAmount;
	private BigDecimal sumFixedAmount;
	private BigDecimal sumFinalAmount;
	
	private List<Integer> washTypeIds;
	private List<Integer> couponIds;
	private List couponAmounts;
	private List fixedAmounts;
	private Object charge;
	private String channel;
	
	
	
	public String getAutoBrand() {
		return autoBrand;
	}
	public void setAutoBrand(String autoBrand) {
		this.autoBrand = autoBrand;
	}
	public String getAutoModel() {
		return autoModel;
	}
	public void setAutoModel(String autoModel) {
		this.autoModel = autoModel;
	}
	public String getAutoPosition() {
		return autoPosition;
	}
	public void setAutoPosition(String autoPosition) {
		this.autoPosition = autoPosition;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public List<Integer> getStates() {
		return states;
	}
	public void setStates(List<Integer> states) {
		this.states = states;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Object getCharge() {
		return charge;
	}
	public void setCharge(Object charge) {
		this.charge = charge;
	}
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
	
	public List getCouponAmounts() {
		return couponAmounts;
	}
	public void setCouponAmounts(List couponAmounts) {
		this.couponAmounts = couponAmounts;
	}
	public List getFixedAmounts() {
		return fixedAmounts;
	}
	public void setFixedAmounts(List fixedAmounts) {
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
	public String getWashTypeName() {
		return washTypeName;
	}
	public void setWashTypeName(String washTypeName) {
		this.washTypeName = washTypeName;
	}
}
