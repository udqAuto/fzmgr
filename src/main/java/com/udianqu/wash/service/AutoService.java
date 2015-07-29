package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.AutoMapper;
import com.udianqu.wash.model.Auto;
import com.udianqu.wash.viewmodel.AutoVM;

@Service
public class AutoService {
	
	@Autowired AutoMapper autoMapper;

	public ListResult<AutoVM> loadAutolist(Map<String, Object> map) {
		int count=autoMapper.countByMap(map);
		List<AutoVM> ls=autoMapper.loadAutolistWithPage(map);
		ListResult<AutoVM> result=new ListResult<AutoVM>(count,ls);
		return result;
	}

	public void updateByPrimaryKey(AutoVM auto) {
		// TODO Auto-generated method stub
		autoMapper.updateByPrimaryKey(auto);
	}

	public void insert(AutoVM auto) {
		// TODO Auto-generated method stub
		autoMapper.insert(auto);
	}

	public void deleteAuto(Integer id) {
		// TODO Auto-generated method stub
		autoMapper.deleteByPrimaryKey(id);
	}

	public Auto selectByPn(String pn) {
		// TODO Auto-generated method stub
		return autoMapper.selectByPn(pn);
	}

}
