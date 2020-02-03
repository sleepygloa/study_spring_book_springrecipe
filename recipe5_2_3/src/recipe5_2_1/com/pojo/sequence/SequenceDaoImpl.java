package recipe5_2_1.com.pojo.sequence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component("sequenceDao")
public class SequenceDaoImpl implements SequenceDao{
	
	private final Map<String, Sequence> sequences = new HashMap<>();
	private final Map<String, AtomicInteger> values = new HashMap<>();

	public SequenceDaoImpl() {
		sequences.put("IT", new Sequence("IT", "30", "A"));
		values.put("IT", new AtomicInteger(100000));
	}
	
	@Override
	public Sequence getSequence(String sequenceId) {
		// TODO Auto-generated method stub
		return sequences.get(sequenceId);
	}

	@Override
	public int getNextValue(String sequenceId) {
		AtomicInteger value = new AtomicInteger();
		// TODO Auto-generated method stub
		return value.getAndIncrement();
	}
	
}
