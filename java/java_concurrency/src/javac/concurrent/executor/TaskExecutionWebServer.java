package javac.concurrent.executor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
	
	private static final int NTHREADS = 1000;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	
	public static void handleRequest(Socket conn) {
		try(
			InputStream in = conn.getInputStream();
			OutputStream out = conn.getOutputStream()
		) {
			int data;
			while ((data = in.read()) != -1) {
				out.write(Util.transmogrify(data));
			}
			out.write(conn.getLocalPort());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void log(String msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) {
		while(true) {
			try(ServerSocket socket = new ServerSocket(80)) {
				final Socket connection = socket.accept();
				exec.execute(()->handleRequest(connection));
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
