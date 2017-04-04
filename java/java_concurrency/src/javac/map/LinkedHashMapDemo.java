package javac.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
	
	private static volatile Map<String, String> map = new LinkedHashMap<String, String>();

	public static void main(String[] args) {
		map.put("a", "A");
	}

}
