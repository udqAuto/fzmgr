package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.model.Region;
import com.udianqu.wash.viewmodel.RegionVM;

public interface RegionService {

	List<RegionVM> getRegionList(Integer qid);

	Region selectByPrimaryKey(Integer id);

	void updateByPrimaryKey(Region region);

	void insert(Region region);

	void deleteRegion(Integer id);

	ListResult<RegionVM> getRegionByPidAndLevel(Map<String, Object> map);

}
