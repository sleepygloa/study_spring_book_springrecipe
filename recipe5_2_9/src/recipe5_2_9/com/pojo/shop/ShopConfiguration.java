package recipe5_2_9.com.pojo.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:discounts.properties")
@ComponentScan("recipe5_2_8.com.pojo.shop")
public class ShopConfiguration {

	@Value("classpath:banner.txt")
	private Resource banner;
	
	@Value("${endofyear.discount:0}")
	private Double specialEndofyearDiscountField;
	
	//spring.context.suppport.PropertySourcesPlaceholderConfigurer
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer(); 
	}
	
	@Bean
	public BannerLoader bannerLoader() {
		BannerLoader b1 = new BannerLoader();
		b1.setBanner(banner);
		return b1;
	}
	
	@Bean
	public Product dvdrw() {
		//Disc p2 = new Disc("DVD-RW", 3.0, specialEndofyearDiscountField);
		Disc p2 = new Disc("DVD-RW", 3.0);
		p2.setCapacity(700);
		return p2;
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}
	
	@Bean(initMethod="openFile", destroyMethod="closeFile")
	public Cashier casher() {
		String path = System.getProperty("java.io.tmpdir") + "/cashier";
		Cashier c1 = new Cashier();
		c1.setFileName("checkout");
		c1.setPath(path);
		return c1;
	}
	
}
