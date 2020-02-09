package recipe5_2_18.com.pojo.calculator.introduction;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {

	@DeclareParents(
			value = "com.pojo.calculator.ArithmeticCalculatorImpl",
			defaultImpl = MaxCalculatorImpl.class
			)
	public MaxCalculator maxCalculator;
	
	
	@DeclareParents(
			value = "com.pojo.calculator.ArithmeticCalculatorImpl",
			defaultImpl = MinCalculatorImpl.class
			)
	public MinCalCulator minCalCulator;
	
	@DeclareParents(
			value = "recipe5_2_18.com.pojo.calculator.introduction.*CalculatorImpl",
			defaultImpl = CounterImpl.class
			)
	public Counter counter;
	
	@After("execution(* recipe5_2_18.com.pojo.calculator.introduction.*Calculator.*(..)) && this(counter)")
	public void increateCount(Counter counter) {
		counter.increase();
	}
	
	
}
