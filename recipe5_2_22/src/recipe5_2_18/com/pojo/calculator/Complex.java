package recipe5_2_18.com.pojo.calculator;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configurable
@Component
@Scope("prototype")
public class Complex {

	private int real;
	private int imaginary;
	
	private ComplexFormatter formatter;
	
	
	public Complex(int real, int imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	
	
	public ComplexFormatter getFormatter() {
		return formatter;
	}
	public void setFormatter(ComplexFormatter formatter) {
		this.formatter = formatter;
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
		//return "Complex [real=" + real + ", imaginary=" + imaginary + "]";
		return formatter.format(this);
	}
	
	
}
