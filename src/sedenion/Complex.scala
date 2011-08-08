package sedenion {

	/**
	 * A class to handle complex numbers.
	 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
	 *
	 */
	 class Complex(real: Double, imaginary: Double) {
	  
	  def re = real
	  def im = imaginary
	  
	  def +(complex: Complex): Complex = new Complex(re + complex.re, im + complex.im);
	  
	}
}