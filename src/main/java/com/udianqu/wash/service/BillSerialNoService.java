package com.udianqu.wash.service;

import java.util.Map;

public interface BillSerialNoService {

	String getNextBillSerialNo(Map<String, Object> map);
	
	String updateBillSerialNo(Map<String,Object> map);
}
