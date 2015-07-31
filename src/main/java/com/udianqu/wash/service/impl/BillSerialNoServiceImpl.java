package com.udianqu.wash.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udianqu.wash.dao.BillSerialMapper;
import com.udianqu.wash.model.BillSerial;
import com.udianqu.wash.service.BillSerialNoService;
@Service
public class BillSerialNoServiceImpl implements BillSerialNoService {

	@Autowired  BillSerialMapper billSerialMapper;
	
	@Override
	public String getNextBillSerialNo(Map<String, Object> map) {
		// TODO Auto-generated method stub 
		return billSerialMapper.getNextBillSerialNo(map);
	}

	@Override 
	public String updateBillSerialNo(Map<String,Object> map){
		return billSerialMapper.updateBillSerialNo(map);
	}
}
