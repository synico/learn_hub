package com.ds.map;

import java.util.HashMap;

public class HashMapDemo {

	public static void main(String[] args) {
		HashMap<String, String> hm = new HashMap<String, String>(32);
		for(int i = 0; i < 43; i++) {
			String key = Integer.toString(i);
			hm.put(key, key);
		}
		
		for(int i = 43; i < 60; i++) {
			String key = Integer.toString(i);
			int h = 0;
			System.out.println((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));
			hm.put(key, key);
		}
	}

}
