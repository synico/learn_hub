package com.zk.c5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import com.zk.App;

public class ConstructorUsageWithSidPasswd implements App {
	
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	
	class MyWatcher implements Watcher {

		@Override
		public void process(WatchedEvent event) {
			println("Receive watched event: " + event);
			if (KeeperState.SyncConnected == event.getState()) {
				connectedSemaphore.countDown();
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		ConstructorUsageWithSidPasswd demo = new ConstructorUsageWithSidPasswd();
//		ZooKeeper zk = new ZooKeeper(ZK_HOST + ":" + ZK_PORT, 5000, demo.new MyWatcher());
		ZooKeeper zk = demo.getZKInstance(demo.new MyWatcher());
		connectedSemaphore.await();
		long sessionId = zk.getSessionId();
		byte[] password = zk.getSessionPasswd();
		
		zk = demo.getZKInstance(demo.new MyWatcher(), 1L, "test".getBytes());
		
		TimeUnit.SECONDS.sleep(3);
		
		zk = demo.getZKInstance(demo.new MyWatcher(), sessionId, password);
		
		TimeUnit.SECONDS.sleep(10);
		
		demo.println("***END***");
	}

}
