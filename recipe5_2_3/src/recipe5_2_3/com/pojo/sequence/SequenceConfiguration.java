package recipe5_2_3.com.pojo.sequence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import recipe5_2_1.com.pojo.sequence.SequenceGenerator;

@Configuration
public class SequenceConfiguration {

	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		DatePrefixGenerator dpg = new DatePrefixGenerator();
		dpg.setPattern("yyyyMMdd");
		return dpg;
	}
	
	@Bean
	public SequenceGenerator sequenceGenerator() {
		SequenceGenerator sequence = new SequenceGenerator();
		sequence.setInitial(100000);
		sequence.setSuffix("A");
		sequence.setPrefixGenerator(datePrefixGenerator());
		return sequence;
	}
	
}
