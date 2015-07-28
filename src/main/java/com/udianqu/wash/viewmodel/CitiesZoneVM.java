package com.udianqu.wash.viewmodel;

import java.util.List;

public class CitiesZoneVM {
	private String name ;
	private List<AreaZoneVM> regions;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AreaZoneVM> getRegions() {
		return regions;
	}
	public void setRegions(List<AreaZoneVM> regions) {
		this.regions = regions;
	}
}
