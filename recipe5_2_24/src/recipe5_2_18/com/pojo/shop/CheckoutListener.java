package recipe5_2_18.com.pojo.shop;

import org.springframework.context.ApplicationListener;

public class CheckoutListener implements ApplicationListener<CheckoutEvent>{

	@Override
	public void onApplicationEvent(CheckoutEvent event) {
		//체크아웃 시각으로 할 일을 여기에 구현합니다.
		System.out.println("Checkout event : [" + event.getTime() + "]");
	}

}
