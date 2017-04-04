package javac.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	
	private Object obj = new Object();
	
	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for(int i = 0; i < nThreads; i++) {
			Thread t = new Thread(Integer.toString(i)) {
				public void run() {
					synchronized(obj) {
						try{
							System.out.println("Thread Name: " + this.getName() + " will invoke startGate.await()");
							startGate.await();
							try{
								System.out.println("Thread Name: " + this.getName() + " wil run");
								task.run();
							} finally {
								endGate.countDown();
								System.out.println(this.getName() + " endGate.countDown()");
							}
						} catch (InterruptedException ignored) {
							
						}
					}
				}
			};
			t.start();
		}
		
		long start = System.currentTimeMillis();
		System.out.println("will invoke startGate.countDown()");
		startGate.countDown();
		System.out.println("will invoke endGate.await()");
		endGate.await();
		long end = System.currentTimeMillis();
		
		
		return end - start;		
	}
	
	public static void main(String args[]) {
		TestHarness th = new TestHarness();
		Thread thread = new Thread() {
			public void run() {
				try {
					sleep(0L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		try {
			long totalTime = th.timeTasks(10, thread);
			System.out.println("Total time: " + totalTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
