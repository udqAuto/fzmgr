package com.udianqu.wash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.dao.OrganizationMapper;
import com.udianqu.wash.model.Organization;
import com.udianqu.wash.service.OrganService;

@Service
public class OrganServiceImpl implements OrganService{

	@Autowired OrganizationMapper organMapper;
	
	@Override
	public List<Organization> getOrganList(Integer id) {
		// TODO Auto-generated method stub
		return organMapper.getOrganList(id);
	}

	@Override
	public List<Organization> getParentIdItems(Integer id) {
		// TODO Auto-generated method stub
		 return organMapper.getParentIdItems(id);
	}

	@Override
	public List<Organization> getItemsByParentId(Integer id) {
		// TODO Auto-generated method stub
		return organMapper.getItemsByParentId(id);
	}

}
