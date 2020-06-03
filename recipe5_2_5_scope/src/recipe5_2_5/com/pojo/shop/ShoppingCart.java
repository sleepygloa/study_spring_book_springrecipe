package recipe5_2_5.com.pojo.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ShoppingCart {
	
	private List<Product> items = new ArrayList<>();
	
	public void addItem(Product item) {
		items.add(item);
	}
	
	public List<Product> getItems(){
		return items;
	}
	
}
