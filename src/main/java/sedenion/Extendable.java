package sedenion;


/** An interface implemented by classes that can be extended by a Cayley-Dickson construction */
public interface Extendable{

	/** Return the conjugate of the number */
	public Extendable conj();

	/** Return the absolute value of the number */
	public double abs();

	/** Return the number multiplied by a real number */
	public Extendable multiply(double that);
}
