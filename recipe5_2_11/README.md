# 스프링 레시피5 2장

##### 메인

1. 개발환경에 맞체 초기값 다르게 설정하기
- @Profile({"","",""})
등으로 설정.

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("global", "winter");
		context.scan("recipe5_2_11.com.pojo.shop");
		context.refresh();
		
##### 서브
