package org.springframework.renewtech;

import org.springframework.beans.BeanUtils;

import com.xyz.chapter6.OrderBean;


/**
 * Unit test for simple App.
 */
@SuppressWarnings("all")
public class AppTest 
{
	public static void main(String args[]) {
		Class<OrderBean> ob = null;
		try {
			ob = (Class<OrderBean>) Class.forName("com.xyz.chapter6.OrderBean");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		OrderBean orderB = BeanUtils.instantiateClass(OrderBean.class, ob);
		System.out.println(orderB);
	}
}
