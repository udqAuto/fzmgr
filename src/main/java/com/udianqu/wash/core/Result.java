package com.udianqu.wash.core;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 返回到前台的数据封装
 * @author Owen
 *
 */
public class Result<T> {
	
	private  JsonConfig jsonConfig;
	
	private T data;
	private boolean isTimeOut;
	private boolean isSessionExpired;
	private boolean isSuccess=true;
	private String msg;
	
	
	public Result(){
		jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
	}
	
	public Result(T data){
		this();
		this.data=data;
	}
	
	public Result(T data,boolean isSuccess,boolean isSessionExpired,boolean isTimeOut,String msg){
		this();
		this.data=data;
		this.isSuccess=isSuccess;
		this.isSessionExpired=isSessionExpired;
		this.isTimeOut=isTimeOut;
		this.msg=msg;
	}
	
	public String toJson(){
		//JSONObject rs=JSONObject.fromObject(this);
		JSONObject rs=JSONObject.fromObject(this,jsonConfig);
		return rs.toString();
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean getIsTimeOut() {
		return isTimeOut;
	}

	public void setIsTimeOut(boolean isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	public boolean getIsSessionExpired() {
		return isSessionExpired;
	}

	public void setIsSessionExpired(boolean isSessionExpired) {
		this.isSessionExpired = isSessionExpired;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
