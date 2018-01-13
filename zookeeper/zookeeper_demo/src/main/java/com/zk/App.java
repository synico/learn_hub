package com.zk;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Hello world!
 *
 */
public interface App 
{
	public static final String ZK_HOST = "172.25.73.176";
	
	public static final String ZK_HOST2 = "192.168.31.108";
	
	public final static String ZK_PORT = "2181";
	
	default void println(String msg) {
		System.out.println(msg);
	}
	
	default ZooKeeper getZKInstance(Watcher watcher) throws Exception {
		return new ZooKeeper(ZK_HOST + ":" + ZK_PORT, 600, watcher);
	}
	
	default ZooKeeper getZKInstance(Watcher watcher, long sessionId, byte[] sessionPassword) throws Exception {
		return new ZooKeeper(ZK_HOST + ":" + ZK_PORT, 600, watcher, sessionId, sessionPassword);
	}
}
