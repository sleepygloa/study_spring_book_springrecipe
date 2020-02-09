package recipe5_2_18.com.pojo.constructor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import recipe5_2_18.com.pojo.shop.Product;

@Component
public class AuditCheckBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof Product) {
			System.out.println("In postProcessBeforeInitialization postProcessBeforeInitialization, processing bean type : " + bean.getClass());
		}
		//return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof Product) {
			System.out.println("In postProcessBeforeInitialization postProcessAfterInitialization, processing bean type : " + bean.getClass());
		}
		//return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
		return bean;
	}
	
		

}
