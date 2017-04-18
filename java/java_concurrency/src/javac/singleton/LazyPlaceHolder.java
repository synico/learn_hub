package javac.singleton;

/**
 * @see http://docs.oracle.com/javase/specs/jls/se7/html/jls-12.html#jls-12.4
 * @author Nick
 *
 */
public class LazyPlaceHolder {
	
	static class Resource {
		
	}
	
	private static class ResouceHolder{
		public static Resource resource = new Resource();
	}
	
	public static Resource getResource() {
		return LazyPlaceHolder.ResouceHolder.resource;
	}
	
	public static void main(String ...args) {
		Resource resource = LazyPlaceHolder.getResource();
	}

}
