package com.udianqu.wash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.udianqu.wash.dao.RegionMapper;
import com.udianqu.wash.model.Region;
import com.udianqu.wash.service.RegionService;
 
@Service
public class RegionServiceImpl implements RegionService {

	@Autowired RegionMapper regionMapper;
	@Override
	public List<Region> getRegionList(Integer id) {
		// TODO Auto-generated method stub
		return regionMapper.getRegionList(id);
	}

}
