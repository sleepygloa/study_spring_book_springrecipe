package recipe5_2_8.com.pojo.sequence;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

public class SequenceGenerator {
	
	@Inject @DatePrefixAnnotation
	private PrefixGenerator prefixGenerator;
	
	private String prefix;
	private String suffix;
	private int initial;
	private final AtomicInteger count = new AtomicInteger();
	
	public SequenceGenerator() {
		
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}
	
	public String getSequence() {
		StringBuilder builder = new StringBuilder();
		builder.append(prefix)
		.append(initial)
		.append(count.getAndIncrement())
		.append(suffix);
		return builder.toString();
	}
}
