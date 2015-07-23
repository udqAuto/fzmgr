package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.AutoMapper;
import com.udianqu.wash.viewmodel.AutoVM;

@Service
public class AutoService {
	
	@Autowired AutoMapper autoMapper;

	public ListResult<AutoVM> loadAutolist(Map<String, Object> map) {
		//int count=autoMapper.countByMap(map);
		int count = 1;
		List<AutoVM> ls=autoMapper.loadAutolistWithPage(map);
		ListResult<AutoVM> result=new ListResult<AutoVM>(count,ls);
		return result;
	}

}
