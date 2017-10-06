package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class OrderDetailQuery extends BaseQuery{

	private Integer id;
	private  Integer orderId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
