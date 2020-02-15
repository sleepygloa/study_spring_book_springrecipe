package recipe5_2_18.com.pojo.executor;

import java.util.Date;

public class DemonstrationRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
		System.out.printf("Hello as %s \n", new Date());
	}
	
	

}
