package com.shsxt.crm.dto;

public enum ServeResult {

	SERVE_NEW_SERVE("1"), //创建状态
	SERVE_ASSIGNED("2"),	//已分配
	SERVE_PROCEED("3"),	//已处理
	SERVE_FEED_BACK("4"),	//已反馈
	SERVE_ARCHIVE("5");	//已归档
	
	
	private String state;

	private ServeResult(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	
}
