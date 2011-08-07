package sedenion;


/**
 * A class to handle complex numbers.
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class Complex implements Cloneable {
	
	public final double real;
	public final double imaginary;
	
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
	
	/**
	 * Adds a complex number to this, also used to add purely imaginary numbers.
	 * @param c The complex number to add.
	 * @return The sum of the numbers
	 */
	public Complex add(Complex c){
		double returnReal = this.real + c.real;
		double returnImaginary = this.imaginary + c.imaginary;
		return new Complex(returnReal, returnImaginary);
	}
	
	/**
	 * Adds a real number to this
	 * @param d the real number to add
	 * @return The sum of this and d
	 */
	public Complex add(double d){
		return this.add(new Complex(d, 0));
	}
	
	public Complex subtract(Complex c){
		return this.add(new Complex(-c.real, -c.imaginary));
	}
	
	/**
	 * Subtracts a real number.
	 * 
	 * Calls add with the given parameter*-1
	 * @param d the number to subtract
	 * @return The result of the subtraction
	 */
	public Complex subtract(double d){
		return this.add(new Complex(-d, 0));
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
	
	public Complex multiply(double d){
		return new Complex(real*d, imaginary*d);
	}
	
	@Override
	public String toString(){
		if(imaginary > 0)
			return "" + real + "+i" + imaginary;
		else if(imaginary < 0)
			return "" + real + "-i" + -imaginary;
		else //imaginary == 0
			return "" + real;
	}
	
	/**
	 * Returns a deep clone of this Complex number
	 */
	@Override
	public Complex clone(){
		return new Complex(this.real, this.imaginary);
	}


}

