package sedenion;

/**
  * A class to handle Complex numbers.
  * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a> */
class Complex( val re: Double, val im: Double ) {
  
  // Lazy values, those that may be, but are not always, useful.
  
  lazy val toMatrix : Matrix = new Matrix( Array(re, -im, im, re), 2 );
  
  lazy val abs = java.lang.Math.sqrt(re*re + im*im);
  
  lazy val arg = java.lang.Math.atan2(im, re);
  
  lazy val * = new Complex(re, -im); 
  
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
  
    import java.lang.Math.{pow, sin, cos, log, E};
  
	val that2 = parseComplex(that);
	val r = pow(abs, that2.re)*pow(E, -that2.im*arg); // The Magnitude of the result.
	val theta = that2.im*log(abs)+that2.re*arg; // The argument of the result.
	val real = r*cos(theta); // The real part of the result.
	val imaginary = r*sin(theta); // The imaginary part of the result.
	new Complex(real, imaginary);
  }
  
  override def toString() = re.toString() + " + i*" + im.toString();
 
}

object Complex {
  /**
   Returns the positive (primary) complex square root of a Complex Number (even if the number is entirely real or imaginary).
   
   The other square root can be found by -sqrt(myComplex)
   */
  def sqrtc(c: Complex): Complex = {
  
    import java.lang.Math.{sqrt, signum};
	// For the purposes of calculating a square root the signum function needs to return 1 if zero, as opposed to the normal 0
	def signumc( d: Double ) = if (0 == d) 1 else signum(d); //fix signum for use in this context.
    
	// Extract values and use common names
	val x = c.re;
	val y = c.im;
	val r = c.abs;
    val gamma = sqrt((r+x)/2);
	val delta = signumc(y)*sqrt((r-x)/2);
	new Complex(gamma, delta);
  }
}