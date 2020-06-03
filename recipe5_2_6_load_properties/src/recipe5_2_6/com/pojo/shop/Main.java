package recipe5_2_6.com.pojo.shop;

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
		
		Resource resource = new ClassPathResource("discounts.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		
		System.out.println("And don't forget out discounts!");
		System.out.println(props);
		
	}

}
