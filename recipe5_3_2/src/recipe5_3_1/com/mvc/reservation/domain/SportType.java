package recipe5_3_1.com.mvc.reservation.domain;

public class SportType {

	private int id;
	private String name;
	
	
	
	public SportType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}