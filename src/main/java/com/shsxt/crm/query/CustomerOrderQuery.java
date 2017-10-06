package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class CustomerOrderQuery extends BaseQuery{

	private Integer cid;
	
	private Integer orderId;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
