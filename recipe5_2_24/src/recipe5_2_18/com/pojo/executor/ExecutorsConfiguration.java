package recipe5_2_18.com.pojo.executor;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan
public class ExecutorsConfiguration {
	
	//java.util.concurrence.executors 인스턴스를 감싼 단순 래퍼, 스프링 taskExecutor 인터페이스와 같은 방식으로 다룰수 있다.
	@Bean
	public TaskExecutorAdapter taskExecutorAdapter() {
		return new TaskExecutorAdapter(Executors.newCachedThreadPool());
	}
	
	//전송한 잡마다 thread 를 새로 만들어 제공하면 스레드 풀링을 하거나 재사용하지 않는다. 전송한 각 잡은 스레드에서 비동기로 실행.
	@Bean
	public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}
	
	
	//가장 단순한 taskExecutor 구현체, 동기적으로 thread를 듸우고 join을 바로 연결.
	//쓰레드를 완전히 띄우고 run을 실행한 것과 같다.
	@Bean
	public SyncTaskExecutor syncTaskExecutor() {
		return new SyncTaskExecutor();
	}
	
	//scheduledExecutorTask 빈으로 정의된 잡을 자동트리거한다., scheduledExecutorTask인스턴스목록을 지정해서 여러잡을 동시해 실행할수 있다. 
	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean(ScheduledExecutorTask scheduledExecutorTasks) {
		ScheduledExecutorFactoryBean scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
		scheduledExecutorFactoryBean.setScheduledExecutorTasks(scheduledExecutorTasks);
		return scheduledExecutorFactoryBean;
	}
	
	@Bean
	public ScheduledExecutorTask scheduledExecutorTask(Runnable runnable) {
		ScheduledExecutorTask shceduledExecutorTask = new ScheduledExecutorTask();
		shceduledExecutorTask.setPeriod(1000);
		shceduledExecutorTask.setRunnable(runnable);
		return shceduledExecutorTask;
	}
	
	//java.util.concurrent.threadPoolExecutor을 기반으로 모든 기능이 완비된 스레드 풀 구현체.
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(50);
		taskExecutor.setMaxPoolSize(100);
		taskExecutor.setAllowCoreThreadTimeOut(true);
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return taskExecutor;
	}
}
