package recipe5_2_18.com.pojo.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("global")
@ComponentScan("recipe5_2_12.com.pojo.shop")
public class ShopConfigurationGlobal {
	
	@Bean(initMethod="openFile", destroyMethod="closeFile")
	public Cashier cashier() {
		final String path = System.getProperty("java.io.tmpdir") + "cashier";
		Cashier c1 = new Cashier();
		c1.setPath(path);
		return c1;
	}
	
}
