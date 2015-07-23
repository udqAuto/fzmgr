package com.udianqu.wash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.dao.WashOrderMapper;
import com.udianqu.wash.viewmodel.AutoVM;
import com.udianqu.wash.viewmodel.WashOrderVM;

@Service
public class WashOrderService {
	
	@Autowired WashOrderMapper washOrderMapper;

	public ListResult<WashOrderVM> loadOrderlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int count = 0;
		List<WashOrderVM> ls=washOrderMapper.loadWashOrderlistWithPage(map);
		ListResult<WashOrderVM> result=new ListResult<WashOrderVM>(count,ls);
		return result;
	}

}