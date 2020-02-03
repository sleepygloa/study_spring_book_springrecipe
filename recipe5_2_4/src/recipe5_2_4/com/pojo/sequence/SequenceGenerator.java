package recipe5_2_4.com.pojo.sequence;

import javax.inject.Inject;

public class SequenceGenerator {
	
	@Inject @DatePrefixAnnotation
	private PrefixGenerator prefixGenerator;

}
