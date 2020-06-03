package recipe5_2_18.com.pojo.shop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource("classpath:discounts.properties")
@ComponentScan("recipe5_2_10.com.pojo.shop")
public class ShopConfiguration {

//	@Value("classpath:banner.txt")
//	private Resource banner;
	
//	@Value("${endofyear.discount:0}")
//	private Double specialEndofyearDiscountField;
	
	//spring.context.suppport.PropertySourcesPlaceholderConfigurer
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer(); 
//	}
	
//	@Bean
//	public BannerLoader bannerLoader() {
//		BannerLoader b1 = new BannerLoader();
//		b1.setBanner(banner);
//		return b1;
//	}
	@Bean
	public ProductCreator productCreatorFactory() {
		ProductCreator factory = new ProductCreator();
		Map<String, Product> products = new HashMap<>();
		products.put("aaa", new Battery("AAA", 2.5));
		products.put("cdrw", new Disc("CD-RW", 1.5));
		products.put("dvdrw", new Disc("DVD-RW", 3.0));
		factory.setProducts(products);
		return factory;
	}
	
	
//	@Bean
//	public Product aaa() {
//		return productCreatorFactory().createProduct("aaa");
//	}
//	
//	@Bean
//	public Product cdrw() {
//		return productCreatorFactory().createProduct("cdrw");
//	}
//	
//	@Bean
//	public Product dvdrw() {
//		return productCreatorFactory().createProduct("dvdrw");
//	}
	
//	@Bean
//	public ReloadableResourceBundleMessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasenames("classpath:messages");
//		messageSource.setCacheSeconds(1);
//		return messageSource;
//	}
	
//	@Bean(initMethod="openFile", destroyMethod="closeFile")
//	public Cashier casher() {
//		String path = System.getProperty("java.io.tmpdir") + "/cashier";
//		Cashier c1 = new Cashier();
//		c1.setFileName("checkout");
//		c1.setPath(path);
//		return c1;
//	}
	
	@Bean
	public Battery aaa() {
		Battery aaa = new Battery("AAA", 2.5);
		return aaa;
	}
	
	@Bean
	public Disc cdrw() {
		Disc aaa = new Disc("CD-RW", 1.5);
		return aaa;
	}
	
	@Bean
	public Disc dvdrw() {
		Disc aaa = new Disc("DVD-RW", 3.0);
		return aaa;
	}
	
	@Bean
	public DiscountFactoryBean discountFactoryBeanAAA() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(aaa());
		factory.setDiscount(0.2);
		return factory;
	}
	
	@Bean
	public DiscountFactoryBean discountFactoryBeanCDRW() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(cdrw());
		factory.setDiscount(0.1);
		return factory;
	}
	
	@Bean
	public DiscountFactoryBean discountFactoryBeanDVDRW() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(dvdrw());
		factory.setDiscount(0.1);
		return factory;
	}
}
