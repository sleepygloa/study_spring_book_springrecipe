package recipe5_2_18.com.pojo.calculator;


public class Complex {

	private int real;
	private int imaginary;
	
	
	public Complex(int real, int imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public int getReal() {
		return real;
	}
	public void setReal(int real) {
		this.real = real;
	}
	public int getImaginary() {
		return imaginary;
	}
	public void setImaginary(int imaginary) {
		this.imaginary = imaginary;
	}

	@Override
	public String toString() {
		return "Complex [real=" + real + ", imaginary=" + imaginary + "]";
	}
	
	
}
