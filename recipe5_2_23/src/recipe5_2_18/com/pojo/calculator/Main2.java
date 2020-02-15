package recipe5_2_18.com.pojo.calculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import recipe5_2_18.com.pojo.calculator.introduction.Counter;
import recipe5_2_18.com.pojo.calculator.introduction.MaxCalculator;
import recipe5_2_18.com.pojo.calculator.introduction.MinCalCulator;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
		ArithmeticCalculator arithmeticCalculator = context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
		arithmeticCalculator.add(1, 2);
		arithmeticCalculator.sub(4, 3);
		arithmeticCalculator.mul(2, 3);
		arithmeticCalculator.div(4, 2);
		
		UnitCalculator unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);
		unitCalculator.kilogramToPound(10);
		unitCalculator.kilometerToMile(5);
		
		
//		MaxCalculator maxCalculator = (MaxCalculator)arithmeticCalculator;
//		maxCalculator.max(1, 2);
//		
//		MinCalCulator minCalCulator = (MinCalCulator)arithmeticCalculator;
//		minCalCulator.min(1, 2);
//		
//		Counter arithmeticCounter = (Counter) arithmeticCalculator;
//		System.out.println(arithmeticCounter.getCount());
//		
//		Counter unitCounter = (Counter) unitCalculator;
//		System.out.println(unitCounter.getCount());
		
		ComplexCalculator complexCalculator = context.getBean("complexCalculator", ComplexCalculator.class);
		
		complexCalculator.add(new Complex(1,2), new Complex(2,3));
		complexCalculator.sub(new Complex(5,8), new Complex(2,3));
	}

}
