package recipe5_2_1.com.pojo.sequence;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import recipe5_2_3.com.pojo.sequence.PrefixGenerator;

public class SequenceGenerator {
	
	private PrefixGenerator prefixGenerator;
	
	@Autowired
	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}
	
	
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
