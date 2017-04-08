package javac.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapDemo {

	public static void main(String[] args) {
		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
		for(int i=0;i<32;i++) {
			if(i==11) {
				System.out.println("x");
			}
			map.put(Integer.toString(i), Integer.toString(i));
		}
	}

}
