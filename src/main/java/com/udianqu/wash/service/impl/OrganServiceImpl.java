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

	@Override
	public void insert(Organization organ) {
		// TODO Auto-generated method stub
		organMapper.insert(organ);
	}

	@Override
	public void updateByPrimaryKey(Organization organ) {
		// TODO Auto-generated method stub
		organMapper.updateByPrimaryKey(organ);
	}

	@Override
	public Organization selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return organMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		organMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteOrgan(Integer id) {
		// TODO Auto-generated method stub
		organMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Organization> loadAllShoplist() {
		// TODO Auto-generated method stub
		return organMapper.loadAllShoplist();
	}

}
