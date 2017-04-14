package javac.concurrent.executor;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class NastyChump {

	public static void main(String[] args) throws InterruptedException {
		Socket[] sockets = new Socket[1000];
		for (int i=0; i < sockets.length; i++) {
			try {
				sockets[i] = new Socket("localhost", 80);
				System.out.println(i);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		TimeUnit.DAYS.sleep(1);
	}

}
