# 스프링 레시피5 2장

##### 메인

> 현재의 프로젝트는 다중구현방법에 대해 에러를 포함하고있다.

1. 위버weaver 구성하기
스프링 AOP 프레임워크는 제한된 aspectj 포인트컷만 지원하게되는데, 
그외의 타입과 외부의 애플리케이션 aspectj 를 끌어오려면 위버 기능을 써야한다.

- aop.xml 에 inclue 적용할 패키지 선언, aspect 클래스 선언.
- ComplexCachingAspect 내 aound에서 호출 하는 메서드 방식을 적어준다. VO.new(args)
- bean을 주입하는 방법은 같다.

- 자바 로드타임에 불러올수 있도록, 
-- java -javaagent:lib/aspectjweaver-1.9.0.jar -jar Recipe5_2_19_li-4.0.0.jar 
-- @EnableLoadTimeWeaving 을 추가한다.


2. 위버를 이용했던 aspect를 
-SPRING AOP ASPECTJ 가 아닌 코딩 aspectj 를 구성.
- calculatorconfiguration 의
	@Bean
	public ComplexCachingAspect complexCachingAspect(){
		
		Map<String, Complex> cache = new HashMap<>();
		cache.put("2,3", new Complex(2,3));
		cache.put("3,5", new Complex(3,5));
			
		ComplexCachingAspect complexCachingAspect = Aspects.aspectOf(ComplexCachingAspect.class);
		complexCachingAspect.setCache(cache);
		return complexCachingAspect;
	}

##### 서브
