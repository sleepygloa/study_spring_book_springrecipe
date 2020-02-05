# 스프링 레시피5 2장

##### 메인

1. Aspect 의 pointcut을 공통으로 사용하자

- 한 클래스 내에서 전역 변수로 뽑아, array 식으로 메서드위에 메서드를 스트링으로 선언하여 사용

//	@Pointcut("execution(* *.*(..))")
//	private void loggingOperation() {}

	@AfterReturning(
			pointcut = "loggingOperation()",
			returning = "result"
			)
			
			
- pointcut을 관리하는 클래스를 만들어서 클래스.메서드를 선언하여 사용

- 클래스 : CalculatorPointcuts
	@AfterReturning(
			pointcut = "CalculatorPointcuts.loggingOperation()",
			returning = "result"
			)

##### 서브
