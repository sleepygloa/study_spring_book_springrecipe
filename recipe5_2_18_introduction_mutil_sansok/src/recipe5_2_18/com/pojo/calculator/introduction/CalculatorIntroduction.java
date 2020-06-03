package recipe5_2_18.com.pojo.calculator.introduction;

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
}
