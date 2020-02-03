package recipe5_2_2.com.shop;

public class Battery extends Product {
	
	private boolean rechargeable;
	
	public Battery() {
		super();
	}
	
	public Battery(String name, Double price) {
		super(name,price);
	}

	public boolean isRechargeable() {
		return rechargeable;
	}

	public void setRechargeable(boolean rechargeable) {
		this.rechargeable = rechargeable;
	}
	
	

}
