package recipe5_2_1.com.pojo.sequence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//2
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
@ComponentScan(
		includeFilters = {
			@ComponentScan.Filter(
					type = FilterType.REGEX,
					pattern = {
								"recipe5_2_1.com.pojo.sequence.*Dao",
								"recipe5_2_1.com.pojo.sequence.*Service"
							  }
					)
		},
		excludeFilters = {
				@ComponentScan.Filter(
						type = FilterType.ANNOTATION,
						classes = {
								org.springframework.stereotype.Controller.class
								}
				)
		}
	)
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1. Configuration 과 Bean
//		ApplicationContext context= new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);
//		SequenceGenerator generator = context.getBean(SequenceGenerator.class);
//		
//		System.out.println(generator.getSequence());
//		System.out.println(generator.getSequence());
		
		//2.
		ApplicationContext context= new AnnotationConfigApplicationContext("recipe5_2_1.com.pojo.sequence");
		SequenceDao sequenceDao = context.getBean(SequenceDao.class);
		
		System.out.println(sequenceDao.getNextValue("IT"));
		System.out.println(sequenceDao.getNextValue("IT"));		

	}

}
