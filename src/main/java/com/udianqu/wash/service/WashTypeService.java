package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.WashTypeMapper;
import com.udianqu.wash.model.WashType;
import com.udianqu.wash.viewmodel.UserVM;

@Service
public class WashTypeService {
	
	@Autowired WashTypeMapper washTypeMapper;

	public ListResult<WashType> loadWashTypeList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int count = 0;
		List<WashType> ls=washTypeMapper.loadWashTypeListWithPage(map);

		ListResult<WashType> result=new ListResult<WashType>(count,ls);
		return result;
	}

	public WashType selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return washTypeMapper.selectByPrimaryKey(id);
	}

	public void updateByPrimaryKey(WashType washType) {
		// TODO Auto-generated method stub
		washTypeMapper.updateByPrimaryKey(washType);
	}

	public void insert(WashType washType) {
		// TODO Auto-generated method stub
		washTypeMapper.insert(washType);
	}

	public void deleteWashType(Integer id) {
		// TODO Auto-generated method stub
		washTypeMapper.deleteByPrimaryKey(id);
	}
	
}
