package recipe5_2_18.com.pojo.calculator.introduction;

public class CounterImpl implements Counter{
	private int count;

	@Override
	public void increase() {
		count++;
	}

	@Override
	public int getCount() {
		return count;
	}
	
	
}
