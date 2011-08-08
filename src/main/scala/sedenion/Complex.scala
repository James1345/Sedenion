package sedenion;

import java.lang.Math._;
/**
 * A class to handle Complex numbers.
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
class Complex( val re: Double, val im: Double ) {
  
  def +(that: Any):Complex = that match {
    case that2: Complex => new Complex(re + that2.re, im + that2.im);
    case that2: Double => new Complex(re + that2, im);
  }
  
  def -(that: Any):Complex = that match {
    case that2: Double => this + (that2 * (-1));
	case that2: Complex => this + (that2 * (-1));
  }
  def unary_-: = new Complex(-re, -im);
  
  def *(that: Any):Complex = that match {
    case that2: Double => new Complex(re*that2, im*that2);
    case that2: Complex => new Complex(re*that2.re + im*that2.im, re*that2.im + im*that2.re);	
  }
  
  def /(that: Any):Complex = that match {
    case that2: Double => new Complex(re/that2, im/that2);
	case that2: Complex => new Complex((re*that2.re + im*that2.im)/(that2.re*that2.re + that2.im*that2.im), (im*that2.re - re*that2.im)/(that2.re*that2.re + that2.im*that2.im));
  }
  
  def abs = sqrt(re*re + im*im);
  
  def arg = atan2(im, re);
  
  def * = new Complex(re, -im);
 
}

/* Try to get statics working 
object Complex {
  def sqrt(c: Complex): Complex = {
    val gamma = java.lang.Math.sqrt((re+sqrt(re*re + im*im))/2);
	val gamma = signum(im)*java.lang.Math.sqrt((-re+sqrt(re*re + im*im))/2)
  
  }
}
*/