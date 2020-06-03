package recipe5_2_14.com.pojo.shop;

import java.util.Map;

public class ProductCreator {

	private Map<String, Product> products;
	
	
	
	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}



	public Product createProduct(String productId) {
//		if("aaa".equals(productId)) {
//			return new Battery("AAA", 1.5);
//		}else if("cdrw".equals(productId)) {
//			return new Disc("CD-RW", 1.5);
//		}else if("dvdrw".equals(productId)) {
//			return new Disc("DVD-RW", 3.0);
//		}
		Product product = products.get(productId);
		if(product != null) {
			return product;
		}
		
		throw new IllegalArgumentException("Unknown product");
	}
	
}
