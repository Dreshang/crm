package com.shsxt.crm.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.shsxt.base.BaseQuery;

public class CustomerLossQuery extends BaseQuery{

	 private String cusNo;

	 private String cusName;

	 private String cusManager;
	 private String createDate;

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusManager() {
		return cusManager;
	}

	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
	 
	 
}
