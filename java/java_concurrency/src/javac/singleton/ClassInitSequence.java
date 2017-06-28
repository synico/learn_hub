package javac.singleton;

public class ClassInitSequence {

	public static void main(String[] args) {
		System.out.println(Sub.taxi);
	}

}


class Super {
	static {
		System.out.println("initialize class Super");
	}
	static int taxi = 1729;
}

class Sub extends Super {
	static {
		System.out.println("initialize class Sub");
	}
}