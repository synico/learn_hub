package com.java.lamda;

@FunctionalInterface
public interface Transformer<T> {

	T transform(T input);
	
}
