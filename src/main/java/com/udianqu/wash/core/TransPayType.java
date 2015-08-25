package com.udianqu.wash.core;


public class TransPayType {
	public static int transToInt(String channel){
		int payType=0;
		if("alipay".equals(channel)){
			payType = 1;
		}
		if("wx".equals(channel)){
			payType = 2;
		}
		if("upacp".equals(channel)){
			payType = 3;
		}
		return payType;
	}
	
	public String transToString(Integer channelNo){
		String channel = null;
		switch(channelNo){
		case 1:
			channel = "alipay";break;
		case 2:
			channel = "wx";break;
		case 3:
			channel = "upacp";break;
		}
		
		return channel;
		
	}

}
