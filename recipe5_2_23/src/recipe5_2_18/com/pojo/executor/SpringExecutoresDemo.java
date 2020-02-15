package recipe5_2_18.com.pojo.executor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SpringExecutoresDemo {
	@Autowired
	private SimpleAsyncTaskExecutor asyncTaskExecutor;
	
	@Autowired
	private SyncTaskExecutor syncTaskExecutor;
	
	@Autowired
	private TaskExecutorAdapter taskExecutorAdaptor;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Autowired
	private DemonstrationRunnable task;
	
	@PostConstruct
	public void submitJobs() {
		syncTaskExecutor.execute(task);
		taskExecutorAdaptor.submit(task);
		asyncTaskExecutor.submit(task);
		
		for(int i = 0; i < 500; i++) {
			threadPoolTaskExecutor.submit(task);
		}
	}
	
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(ExecutorsConfiguration.class).registerShutdownHook();
	}
}
