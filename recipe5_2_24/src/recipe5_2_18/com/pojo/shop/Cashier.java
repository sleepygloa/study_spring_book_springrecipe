package recipe5_2_18.com.pojo.shop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Cashier implements BeanNameAware{
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	

//	public void checkout(ShoppingCart cart) throws IOException{
//		String alert = messageSource.getMessage("alert.inventory.checkout", new Object[] {cart.getItems(), new Date()}, Locale.US);
//		System.out.println(alert);
//	}
	
	@Value("checkout")
	private String fileName;
	
	@Override
	public void setBeanName(String beanName) {
		this.fileName = beanName;
	}

	@Value("D:/wms/workspace/study_spring_recipe/recipe5_2_8/src")
	private String path;
	private BufferedWriter writer;
	
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@PostConstruct
	public void openFile() throws IOException{
		File targetDir = new File(path);
		if(!targetDir.exists()) {
			targetDir.mkdir();
		}
		
		File checkoutFile = new File(path,fileName+".txt");
		if(!checkoutFile.exists()) {
			checkoutFile.createNewFile();
		}
		
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile, true)));
	}
	
	public void checkout(ShoppingCart cart) throws IOException{
		writer.write(new Date() + "\t" + cart.getItems() + "\r\n");
		//5.2.24 추가
		CheckoutEvent event = new CheckoutEvent(cart, cart, new Date());
		applicationEventPublisher.publishEvent(event);
	}
	
	@PreDestroy
	public void closeFile() throws IOException{
		writer.close();
	}
	
	
}
