# 스프링 레시피5 2장

##### 메인

> 현재의 프로젝트는 다중구현방법에 대해 에러를 포함하고있다.

1. Aspect 의 다중으로 구현하는 방법
	@DeclareParents(
			value = "com.pojo.calculator.ArithmeticCalculatorImpl",
			defaultImpl = MaxCalculatorImpl.class
			)
	public MaxCalculator maxCalculator;
	
	@DeclareParents를 선언하고
	defaultImpl 에 기본으로 구현하는 impl을 지정한후, value 에 여러 impl을 등록한다.
	
	
	사용하는 곳.
	ArithmeticCalculator arithmeticCalculator2 = (ArithmeticCalculator) context.getBean("arithmeticCalculator");
		
	MaxCalculator maxCalculator = (MaxCalculator)arithmeticCalculator2;
	maxCalculator.max(1, 2);
	
	arithmeticCalculator 빈을 찾은 다음. maxCalculator로 변환 하여. 인터페이스에 넣어주고, 사용하는데
	자동 프록시 설정이 에러가 있는지. ArithmeticCalculator -> MaxCalculator 형변환 상황에서 에러가 난다.

##### 서브
