package com.xyz.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.xyz.domain.Order;

@Service("orderDaoService")
public class OrderDaoServiceImpl implements OrderDaoService {
	
	Log log = LogFactory.getLog(this.getClass());
	
	public OrderDaoServiceImpl() {
		log.info("constructor of OrderDaoServiceImpl");
	}

	@Override
	public Order getOrderById(String id) {
		log.info("OrderDaoServiceImpl.getOrderById()");
		return null;
	}

	@Override
	public Long getOrderId(String id) {
		log.info("OrderDaoServiceImpl.getOrderId()");
		return new Long(111);
	}

	@Override
	public void printOrderInfo() throws Throwable {
		log.info("OrderDaoServiceImpl.printOrderInfo()");
		throw new Exception("printOrderInfo exception");
	}

}
