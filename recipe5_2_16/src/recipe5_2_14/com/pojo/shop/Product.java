package recipe5_2_14.com.pojo.shop;

public class Product {
	private String name;
	private Double price;
	
	public Product() {}
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String toString() {
		return name + " " + price;
	}
	
}
