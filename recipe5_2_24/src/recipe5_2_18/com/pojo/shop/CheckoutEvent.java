package recipe5_2_18.com.pojo.shop;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class CheckoutEvent extends ApplicationEvent{

	private final ShoppingCart cart;
	private final Date time;
	
	
	public CheckoutEvent(Object source, ShoppingCart cart, Date time) {
		super(source);
		this.cart = cart;
		this.time = time;
	}
	
	public ShoppingCart getCart() {
		return cart;
	}
	public Date getTime() {
		return time;
	}
	
	
	
}
