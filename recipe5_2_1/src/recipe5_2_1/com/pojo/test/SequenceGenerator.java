package recipe5_2_1.com.pojo.test;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator {
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
