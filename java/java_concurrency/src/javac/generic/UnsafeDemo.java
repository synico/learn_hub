package javac.generic;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("all")
public class UnsafeDemo {
	
	public static Unsafe getUnsafeInstance() throws Exception {
		Field unsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
		unsafeInstance.setAccessible(true);
		return (Unsafe)unsafeInstance.get(Unsafe.class);
	}

	public static void main(String[] args) {
		try {
			Unsafe unsafe = getUnsafeInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
