package com.udianqu.wash.viewmodel;

import com.udianqu.wash.model.WashOrder;

public class WashOrderVM extends WashOrder{
	private String customerName; 
	private String autoPN;
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
}
