package sedenion;

import java.lang.Math._;
/**
  * A class to handle Complex numbers.
  * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a> */
class Complex( val re: Double, val im: Double ) {
  
  
  /**
  Utility method to turn numeric types into complex numbers for use in other methods.
  */
  private def parseComplex(that: Any): Complex = that match {
    case that2: Complex => that2;
	case that2: Double => new Complex(that2, 0);
	case that2: Int => new Complex(that2.toDouble, 0);
	case that2: Long => new Complex(that2.toDouble, 0);
	case that2: Short => new Complex(that2.toDouble, 0);
	case that2: Float => new Complex(that2.toDouble, 0);
	// TODO add other numeric types.
  }
  
  def +(that: Any):Complex = {
    val that2 = parseComplex(that);
    new Complex(re + that2.re, im + that2.im);
  }
  
  def -(that: Any):Complex = {
    val that2 = parseComplex(that);
	this + (that2 * (-1));
  }
  def unary_- = new Complex(-re, -im);
  
  def *(that: Any):Complex = {
    val that2 = parseComplex(that);
    new Complex(re*that2.re + im*that2.im, re*that2.im + im*that2.re);	
  }
  
  def /(that: Any):Complex = {
    val that2 = parseComplex(that);
	new Complex((re*that2.re + im*that2.im)/(that2.re*that2.re + that2.im*that2.im), (im*that2.re - re*that2.im)/(that2.re*that2.re + that2.im*that2.im));
  }
  
  /**
   Raise the complex number to the power of that.
   
   That may be a Complex or basic numeric type. Real numbers can be raise to Complex
   powers by first constructing a Complex with im=0.
  */
  def **(that: Any): Complex = {
	val that2 = parseComplex(that);
	val r = pow(abs, that2.re)*pow(E, -that2.im*arg); // The Magnitude of the result.
	val theta = that2.im*log(abs)+that2.re*arg; // The argument of the result.
	val real = r*cos(theta); // The real part of the result.
	val imaginary = r*sin(theta); // The imaginary part of the result.
	new Complex(real, imaginary);
  }
  
  def toMatrix : Matrix = new Matrix( this );
  
  def abs = sqrt(re*re + im*im);
  
  def arg = atan2(im, re);
  
  def * = new Complex(re, -im);
  
  override def toString() = re.toString() + " + i*" + im.toString();
 
}

object Complex {
  /**
   Returns the positive (primary) complex square root of a Complex Number (even if the number is entirely real or imaginary).
   
   The other square root can be found by -sqrt(myComplex)
   */
  def sqrtc(c: Complex): Complex = {
	val re = c.re;
	val im = c.im;
    val gamma = sqrt((re+sqrt((re*re) + (im*im)))/2);
	val delta = signum(im)*sqrt((-re+sqrt(re*re + im*im))/2);
	new Complex(gamma, delta);
  }
}