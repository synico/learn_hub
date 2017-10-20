package com.java.lamda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class OrderSample {

	public static void main(String[] args) {
		Order order = new Order(Arrays.asList(
				new OrderItem(1, 125),
				new OrderItem(2, 983),
				new OrderItem(3, 154)
				));
		
		order.transformAndPrint(new Transformer<Stream<OrderItem>>() {

			@Override
			public Stream<OrderItem> transform(Stream<OrderItem> input) {
				return input.sorted(Comparator.comparing(OrderItem::getPrice));
			}
			
		});
		
		System.out.println("=================================================");
		
		order.transformAndPrintByFunc(orderitems -> orderitems.sorted(Comparator.comparing(OrderItem::getPrice)));
	}

}
