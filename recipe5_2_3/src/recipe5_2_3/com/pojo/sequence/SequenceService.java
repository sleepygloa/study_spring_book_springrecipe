package recipe5_2_3.com.pojo.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import recipe5_2_1.com.pojo.sequence.Sequence;
import recipe5_2_1.com.pojo.sequence.SequenceDao;

@Component
public class SequenceService {

	@Autowired
	private SequenceDao sequenceDao;

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}
	
	public String generate(String sequenceId) {
		Sequence sequence = sequenceDao.getSequence(sequenceId);
		int value = sequenceDao.getNextValue(sequenceId);
		return sequence.getPrefix() + value + sequence.getSubfix();
	}
	
}
