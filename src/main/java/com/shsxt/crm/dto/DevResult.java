package com.shsxt.crm.dto;

public enum DevResult {

	UN_DEV(0),
	DEVING(1),
	DEV_SUCCESS(2),
	DEV_FAILD(3);
	
	private Integer devResult;

	private DevResult(Integer devResult) {
		this.devResult = devResult;
	}

	public Integer getDevResult() {
		return devResult;
	}

	public void setDevResult(Integer devResult) {
		this.devResult = devResult;
	}
	
}
