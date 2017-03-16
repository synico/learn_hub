package com.xyz.dao;

import com.xyz.domain.Order;

public interface OrderDaoService {
	
	Order getOrderById(String id);
	
	Long getOrderId(String id);
	
	void printOrderInfo() throws Throwable;

}
