package sedenion;


/** Create a new type, based on a Cayley-Dickson construct on the given Extendable type */
public class CayleyDickson<E extends Extendable> implements Extendable {
	
	public final E alpha;
	public final E beta;

	public CayleyDickson(E a, E b){
		alpha = a;
		beta = b;
	}

	protected double abs = Double.NaN;
	protected Extendable conj = null;
	protected String toString = null;
	
	/** Recursively lazily calculate absolute value */
	public double abs(){
		if(Double.NaN == abs) abs = Math.sqrt(alpha.abs()*alpha.abs() + beta.abs()*beta.abs());
		return abs;
	}

	public Extendable conj(){
		if(null == conj) conj = new CayleyDickson<Extendable>(alpha.conj(), beta.multiply(-1));
		return conj;
	}

	public Extendable multiply(double that){
		return new CayleyDickson<Extendable>(alpha.multiply(that), beta.multiply(that));
	}

}
