package recipe5_2_5.com.pojo.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);
		
		Product aaa = context.getBean("aaa", Product.class);
		Product cdrw = context.getBean("cdrw", Product.class);
		Product dvdrw = context.getBean("dvdrw", Product.class);
		
		System.out.println(aaa);
		System.out.println(cdrw);
		System.out.println(dvdrw);
		
		ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
		cart1.addItem(aaa);
		cart1.addItem(cdrw);
		System.out.println("shopping cart 1 contains " + cart1.getItems());
		
		ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
		cart2.addItem(dvdrw);
		System.out.println("shopping cart 2 contains " + cart2.getItems());
		
		/*
AAA 2.5
CD-RW 1.5
DVD-RW 3.0
shopping cart 1 contains [AAA 2.5, CD-RW 1.5]
shopping cart 2 contains [AAA 2.5, CD-RW 1.5, DVD-RW 3.0]

		 * */
	}

}
