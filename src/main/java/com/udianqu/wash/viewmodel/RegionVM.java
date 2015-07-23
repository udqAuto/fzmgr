package com.udianqu.wash.viewmodel;

import java.util.List;

import com.udianqu.wash.model.Region;

public class RegionVM extends Region {
	private List<RegionVM> children;
	private String state;
	private String text;
	private String shopName;
	public List<RegionVM> getChildren() {
		return children;
	}
	public void setChildren(List<RegionVM> children) {
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	} 
}
