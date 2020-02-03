package recipe5_2_1.com.pojo.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context= new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);
		SequenceGenerator generator = context.getBean(SequenceGenerator.class);
		
		System.out.println(generator.getSequence());
		System.out.println(generator.getSequence());
	}

}
