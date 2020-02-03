package recipe5_2_1.com.pojo.sequence;

public interface SequenceDao {
	public Sequence getSequence(String sequenceId);
	public int getNextValue(String sequenceId);
}
