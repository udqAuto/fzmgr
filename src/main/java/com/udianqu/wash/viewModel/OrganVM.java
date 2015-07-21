package com.udianqu.wash.viewModel;

import java.util.List;

import com.udianqu.wash.model.Organization; 

public class OrganVM extends Organization {
	private List<OrganVM> children;
	private String state;
	private String text; 
	
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
}
