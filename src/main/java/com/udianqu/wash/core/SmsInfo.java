package com.udianqu.wash.core;

import java.util.Date;

public class SmsInfo { 
	private String mobile;  
	private Date	sendTime;
	private String  verifCode;
	 
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}  
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getVerifCode() {
		return verifCode;
	}
	public void setVerifCode(String verifCode) {
		this.verifCode = verifCode;
	}
}
