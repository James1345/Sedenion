package sedenion;

import java.lang.Math._;
/**
 * A class to handle Complex numbers.
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
class Complex( val re: Double, val im: Double ) {
  
  def +(that: Any): Complex = that match {
    case Complex => new Complex(re + that.re, im + that.im);
    case Double => new Complex(re + that, im);
  }
  
  def -(that: Any): Complex = this + (that * (-1));
  
  def unary_-: Complex = new Complex(-re, -im);
  
  def *(that: Any): Complex = that match {
    case Double => new Complex(re*that, im*that);
    case Complex => new Complex(re*that.re + im*that.im, re*that.im + im*that.re);	
  }
  
  def /(that: Any):Complex = that match {
    case Double => new Complex(re/that, im/that);
	case Complex => new Complex((re*that.re + im*that.im)/(that.re*that.re + that.im*that.im), (im*that.re - re*that.im)/(that.re*that.re + that.im*that.im));
  }
  
  def abs = sqrt(re*re + im*im);
  
  def arg = atan2(im, re);
  
  def *: Complex = new Complex(re, -im);
 
}

object Complex {
  def sqrt(c: Complex): Complex = {
    val gamma = java.lang.Math.sqrt((re+sqrt(re*re + im*im))/2);
	val gamma = signum(im)*java.lang.Math.sqrt((-re+sqrt(re*re + im*im))/2)
  
  }
}
