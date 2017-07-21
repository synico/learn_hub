package javac.concurrent.thread;

public class TestMJoin {
	
	public void buildTasks() {
		Thread task1 = new Thread() {
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("task1");
			}
		};
		Thread task2 = new Thread() {
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("task2");
			}
		};
		Thread task3 = new Thread() {
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("task3");
			}
		};
		try {
			task1.start();
			task2.start();
			task3.start();
			
			task1.join();
			task2.join();
			task3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		TestMJoin join = new TestMJoin();
		join.buildTasks();
	}

}
