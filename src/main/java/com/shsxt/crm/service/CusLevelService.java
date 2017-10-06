package com.shsxt.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.CusLevelDao;
import com.shsxt.crm.vo.CusLevel;

@Service
public class CusLevelService {

	@Autowired
	private CusLevelDao cusLevelDao;
	
	
	public List<CusLevel> queryLevel(String dataDicName){
		return cusLevelDao.queryLevel(dataDicName);
	}

}
