package com.java.lamda;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Order {
	
	List<OrderItem> items;
	
	public Order(List<OrderItem> orderitems) {
		items = orderitems;
	}
	
	public void transformAndPrint(Transformer<Stream<OrderItem>> transformOrderItems) {
//		transformOrderItems.transform(items.stream()).forEach(item -> System.out.println(item));
		transformOrderItems.transform(items.stream()).forEach(System.out::println);
	}
	
	public void transformAndPrintByFunc(Function<Stream<OrderItem>, Stream<OrderItem>> transformOrderitems) {
		transformOrderitems.apply(items.stream()).forEach(System.out::println);
	}

}
