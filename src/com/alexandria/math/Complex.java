package com.alexandria.math;

/**
 * A class to handle complex numbers.
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class Complex {
	
	protected double real = 0.0;
	protected double imaginary = 0.0;
	
	/**
	 * Default constructor.
	 * 
	 * Constructs a new Complex number with the given Real and imaginary parts.
	 * @param real
	 * @param imaginary
	 */
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/* Basic operations */
	
	public Complex add(Complex c){
		double returnReal = this.real + c.real;
		double returnImaginary = this.imaginary + c.imaginary;
		return new Complex(returnReal, returnImaginary);
	}
	
	/**
	 * Multiplies two complex numbers.
	 * 
	 * This multiplies a complex number by another. Complex number multiplication is
	 * commutative (i.e. this*c == c*this)
	 * @param c the complex number to multiply by
	 * @return The product of this and c
	 */
	public Complex multiply(Complex c){
		double returnReal = this.real*c.real - this.imaginary*c.imaginary;
		double returnImaginary = this.real*c.imaginary + this.imaginary*c.real;
		return new Complex(returnReal, returnImaginary);
	}

}
