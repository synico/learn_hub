package javac.lang;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo extends Thread {
	
	private static volatile AtomicInteger race = new AtomicInteger(0);
	
	private String threadName;
	
	public ThreadDemo(String threadName) {
		this.threadName = threadName;
	}
	
	public void run() {
		run2();
	}
	
	public void run1() {
		System.out.println("Running in thread: " + this.threadName + " " + System.currentTimeMillis());
		try {
			sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(this.threadName + " will exit " + System.currentTimeMillis());
		}
	}
	
	public void run2() {
		ReentrantLock action = new ReentrantLock();
		action.lock();
		for(int i=0; i < 100; i++) {
			if(race.intValue() < 10) {
				race.addAndGet(1);
				log(this.threadName + " value of race[+1]: " + race + "|" + System.currentTimeMillis());
			} else {
				race.addAndGet(-5);
				log(this.threadName + " value of race[-5]: " + race + "|" + System.currentTimeMillis());
			}
		}
		action.unlock();
	}
	
	public static void printTime() {
		System.out.println(System.currentTimeMillis());
	}
	
	public static void log(String msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) {
		ThreadDemo threadA = new ThreadDemo("A");
		threadA.start();
		ThreadDemo threadB = new ThreadDemo("B");
		threadB.start();
	}

}
