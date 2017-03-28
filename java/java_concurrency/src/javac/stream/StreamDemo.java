package javac.stream;

import java.util.List;

import com.google.common.collect.Lists;

public class StreamDemo {

	public static void main(String[] args) {
		List<Integer> nums = Lists.newArrayList(1,null,3,4,null,6);
		long count = nums.stream().filter(num -> num != null).count();
		System.out.println("count: " + count);
	}

}
