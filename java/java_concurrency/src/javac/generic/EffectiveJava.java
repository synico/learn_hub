package javac.generic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EffectiveJava {
	
	private static void rule23() {
		@SuppressWarnings("all") Set rawtypeSet = new HashSet();
		Set<String> strSet = new HashSet<String>();
		Set<?> uSet = new HashSet<String>();
		System.out.println(rawtypeSet.getClass());
		System.out.println(strSet.getClass());
		rule23child1(strSet);
		rule23child2(rawtypeSet);
	}
	
	@SuppressWarnings("rawtypes")
	private static void rule23child1(Set set) {}
	
	private static void rule23child2(Set<Object> set) {}
	
	private static void rule24() {
		List<?>[] list = new List<?>[2];
		List[] list1 = new List[3];
	}

	public static void main(String[] args) {
		rule23();
	}

}
