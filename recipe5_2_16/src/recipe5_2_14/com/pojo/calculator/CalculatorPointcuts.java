package recipe5_2_14.com.pojo.calculator;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/*
 * 포인트 컷을 관리하는 클래스
 * */
@Aspect
public class CalculatorPointcuts {

	@Pointcut("execution(* *.*(..))")
	public void loggingOperation() {}
	
}
