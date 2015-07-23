package com.udianqu.wash.viewmodel;

import java.util.List;

import com.udianqu.wash.model.Organization; 

public class OrganVM extends Organization {
	private List<OrganVM> children;
	private String state;
	private String text; 
	private String bmName;
	public List<OrganVM> getChildren() {
		return children;
	}
	public void setChildren(List<OrganVM> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getBmName() {
		return bmName;
	}
	public void setBmName(String bmName) {
		this.bmName = bmName;
	}
}
