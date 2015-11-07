package com.udianqu.wash.viewmodel;

import java.math.BigDecimal;

public class ReportShowVM {
	private String shopName;
	private Integer orderCount;
	private Integer newOrder;
	private Integer receivedOrder;
	private Integer completedOrder;
	private Integer gradedOrder;
	private Integer fastWash;
	private Integer insideWash;
	private Integer waxed;
	private Integer alipay;
	private Integer weChat;
	private Integer balancePay;
	private BigDecimal totalAmount;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(Integer newOrder) {
		this.newOrder = newOrder;
	}
	public Integer getReceivedOrder() {
		return receivedOrder;
	}
	public void setReceivedOrder(Integer receivedOrder) {
		this.receivedOrder = receivedOrder;
	}
	public Integer getCompletedOrder() {
		return completedOrder;
	}
	public void setCompletedOrder(Integer completedOrder) {
		this.completedOrder = completedOrder;
	}
	public Integer getGradedOrder() {
		return gradedOrder;
	}
	public void setGradedOrder(Integer gradedOrder) {
		this.gradedOrder = gradedOrder;
	}
	public Integer getFastWash() {
		return fastWash;
	}
	public void setFastWash(Integer fastWash) {
		this.fastWash = fastWash;
	}
	public Integer getInsideWash() {
		return insideWash;
	}
	public void setInsideWash(Integer insideWash) {
		this.insideWash = insideWash;
	}
	public Integer getWaxed() {
		return waxed;
	}
	public void setWaxed(Integer waxed) {
		this.waxed = waxed;
	}
	public Integer getAlipay() {
		return alipay;
	}
	public void setAlipay(Integer alipay) {
		this.alipay = alipay;
	}
	public Integer getWeChat() {
		return weChat;
	}
	public void setWeChat(Integer weChat) {
		this.weChat = weChat;
	}
	public Integer getBalancePay() {
		return balancePay;
	}
	public void setBalancePay(Integer balancePay) {
		this.balancePay = balancePay;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
