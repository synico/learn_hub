package com.zk.c5;

import static com.zk.App.ZK_HOST;
import static com.zk.App.ZK_HOST2;
import static com.zk.App.ZK_PORT;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ConstructorUsageSimple implements Watcher {
	
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper(ZK_HOST2 + ":" + ZK_PORT, 5000, new ConstructorUsageSimple());
		System.out.println("State: " + zk.getState());
		try {
			connectedSemaphore.await();
		} catch (InterruptedException e) {}
		System.out.println("ZK session established");
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event: " + event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}

}
