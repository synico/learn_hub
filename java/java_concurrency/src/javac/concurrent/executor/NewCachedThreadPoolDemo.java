package javac.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NewCachedThreadPoolDemo {
	
	private ExecutorService exec = Executors.newCachedThreadPool();
	
	public void runBatchJobs() {
		List<LoadDataTask> jobs = new ArrayList<LoadDataTask>();
		jobs.add(new LoadDataTask(1));
		jobs.add(new LoadDataTask(2));
		jobs.add(new LoadDataTask(3));
		jobs.add(new LoadDataTask(4));
		jobs.add(new LoadDataTask(5));
		List<Future<String>> ret;
		try {
			ret = exec.invokeAll(jobs);
			for(Future<String> job : ret) {
				if(job.isDone()) {
					System.out.println(job.get());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		exec.shutdown();
	}

	public static void main(String[] args) {
		new NewCachedThreadPoolDemo().runBatchJobs();
	}

}
