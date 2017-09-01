package javac.concurrent.queue.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
	
	private static final BlockingQueue<String> syncQueue = new SynchronousQueue<String>(); 
	
	private static final CountDownLatch latch = new CountDownLatch(1);
	
	class Consumer implements Runnable {

		@Override
		public void run() {
			for(;;) {
				try {
					latch.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				String rst = syncQueue.poll();
				if(rst == null) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println(rst);
				}
			}
		}
		
	}
	
	class Producer implements Runnable{

		@Override
		public void run() {
			for(;;) {
				latch.countDown();
				if(syncQueue.offer("test: " + System.currentTimeMillis())) {
					return;
				}
			}
		}
		
	}
	
	public void test() {
		Consumer con = new Consumer();
		
		Producer pro = new Producer();
		
		Thread proT = new Thread(pro);
		Thread conT = new Thread(con);
		conT.start();
		proT.start();
	}

	public static void main(String[] args) {
		SynchronousQueueDemo demo = new SynchronousQueueDemo();
		demo.test();
	}

}
