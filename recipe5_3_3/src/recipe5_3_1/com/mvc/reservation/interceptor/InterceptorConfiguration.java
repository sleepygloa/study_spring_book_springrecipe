package recipe5_3_1.com.mvc.reservation.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfiguration implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(measurementInterceptor());
		registry.addInterceptor(summaryReportInterceptor())
		.addPathPattern("/reservationSummary*");
	}

	@Bean
	public MeasurementInterceptor measurementInterceptor() {
		return new MeasurementInterceptor();
	}
	
	@Bean
	public SummaryReportInterceptor summaryReportInterceptor() {
		return new SummaryReportInterceptor();
	}
}
