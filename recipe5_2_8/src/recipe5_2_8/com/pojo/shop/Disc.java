package recipe5_2_8.com.pojo.shop;

public class Disc extends Product{

	private int capacity;
	
	public Disc() {
		super();
	}
	
	public Disc(String name, Double price) {
		super(name, price);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
