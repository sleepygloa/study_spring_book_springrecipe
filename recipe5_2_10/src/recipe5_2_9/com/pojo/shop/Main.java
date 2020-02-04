package recipe5_2_9.com.pojo.shop;

import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);
		
//		String alert = context.getMessage("alert.checkout",  null, Locale.US);
//		String alert_inventory = context.getMessage("alert.inventory.checkout", new Object[] {"[DVD-RW 3.0]", new Date()}, Locale.US);
//		
//		System.out.println("The I18N message for alert.checkout is : " + alert);
//		System.out.println("The I18N message for alert.inventory.check is : " + alert_inventory);
//		
//		
//		
//		Resource resource = new ClassPathResource("discounts.properties");
//		Properties props = PropertiesLoaderUtils.loadProperties(resource);
//		
//		System.out.println("And don't forget out discounts!");
//		System.out.println(props);
		
	}

}
