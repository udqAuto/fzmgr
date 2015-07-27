package com.udianqu.wash.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.RegionMapper;
import com.udianqu.wash.model.Region;
import com.udianqu.wash.service.RegionService;
import com.udianqu.wash.viewmodel.RegionVM;
import com.udianqu.wash.viewmodel.UserVM;
 
@Service
public class RegionServiceImpl implements RegionService {

	@Autowired RegionMapper regionMapper;
	@Override
	public List<RegionVM> getRegionList(Integer id) {
		// TODO Auto-generated method stub
		return regionMapper.getRegionList(id);
	}
	@Override
	public Region selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return regionMapper.selectByPrimaryKey(id);
	}
	@Override
	public void updateByPrimaryKey(Region region) {
		// TODO Auto-generated method stub
		regionMapper.updateByPrimaryKey(region);
	}
	@Override
	public void insert(Region region) {
		// TODO Auto-generated method stub
		regionMapper.insert(region);
	}
	@Override
	public void deleteRegion(Integer id) {
		// TODO Auto-generated method stub
		regionMapper.deleteByPrimaryKey(id);
	}
	@Override
	public ListResult<RegionVM> getRegionByPidAndLevel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int count = regionMapper.countByMap(map);
		List<RegionVM> ls=regionMapper.getRegionByPidAndLevel(map);
		ListResult<RegionVM> result=new ListResult<RegionVM>(count,ls);
		return result;
	}

}
