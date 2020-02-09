package recipe5_2_18.com.pojo.calculator.introduction;

public class MinCalculatorImpl implements MinCalCulator{

	@Override
	public double min(double a, double b) {
		double result = (a <= b) ? a : b;
		System.out.println("min(" + a + ", " + b + ") = " + result);
		return result;
	}
	

}
