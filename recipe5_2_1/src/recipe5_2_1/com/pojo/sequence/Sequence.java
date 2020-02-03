package recipe5_2_1.com.pojo.sequence;

public class Sequence {

	private final String id;
	private final String prefix;
	private final String subfix;
	
	public Sequence(String id, String prefix, String subfix) {
		this.id = id;
		this.prefix = prefix;
		this.subfix = subfix;
	}

	public String getId() {
		return id;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSubfix() {
		return subfix;
	}
	
	
	
}
