package com.udianqu.wash.service;

import java.util.List;

import com.udianqu.wash.model.Region;

public interface RegionService {

	List<Region> getRegionList(Integer qid);

	Region selectByPrimaryKey(Integer id);

	void updateByPrimaryKey(Region region);

	void insert(Region region);

	void deleteRegion(Integer id);

}
