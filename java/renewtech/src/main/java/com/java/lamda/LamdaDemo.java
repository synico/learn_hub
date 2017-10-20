package com.java.lamda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SuppressWarnings("all")
public class LamdaDemo {
	
	private void accessIndexByFor() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		for(int i = 0; i < 5; i++) {
			int temp = i;//final is optional
			executorService.submit(new Runnable() {
				public void run() {
					System.out.println("Running task " + temp);
				}
			});
		}
		
		executorService.shutdown();
	}
	
	private void accessIndexByLamda() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		IntStream.range(0, 5).forEach(i -> executorService.submit(() -> System.out.println("Running task " + i)));
	}

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println("In the Main");
		});
		thread.start();
	}

}
