package sedenion;


/**
 *  An interface implemented by classes that can be extended by a Cayley-Dickson construction
 * 
 * A number that can be extended requires the methods for +, -, *, and conjugation
 */
public interface Number{

	public Number add(Number that);
	
	public Number subtract(Number that);

	public Number multiply(Number that);

	/** Return the conjugate of the number */
	public Number conj();

	
}
