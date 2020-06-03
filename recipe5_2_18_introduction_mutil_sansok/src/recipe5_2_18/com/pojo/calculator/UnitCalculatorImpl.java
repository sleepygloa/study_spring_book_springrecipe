package recipe5_2_18.com.pojo.calculator;

import org.springframework.stereotype.Component;

@Component("unitCalculator")
public class UnitCalculatorImpl implements UnitCalculator{

	@Override
	public double kilogramToPound(double kilogram) {
		double pound = kilogram * 2.2;
		System.out.println(kilogram + "kilogram = " + pound + " pound");
		return pound;
	}

	@Override
	public double kilometerToMile(double kilometer) {
		double mile = kilometer * 2.2;
		System.out.println(kilometer + "kilometer = " + mile + " mile");
		return mile;
	}
	
	

}
