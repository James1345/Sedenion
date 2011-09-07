package sedenion;


/** Create a new type, based on a Cayley-Dickson construct on the given Number type */
public class CayleyDickson implements Number {
	
	/** The parts of the number, held as the two components */
	protected final Number a, b;

	/** Create a new instance with the given two numbers as parameters */
	public CayleyDickson(Number a, Number b){
		parts = new double[a.parts()*2];
		
		// copy loop
		int partlen = a.parts();
		int i = 0, j = partlen;
		while(i < partlen){
			parts[i] = a.part(i);
			parts[j] = b.part(i);
			i++;
			j++;
		}
		this.a = a;
		this.b = b;
	}
	
	/** A protected constructor only to be used by this classes methods internally */
	protected CayleyDickson(double[] parts){
		this.parts = parts;
		int partlen = (parts()+1)/2;
		double[] alpha = new double[partlen];
		double[] beta = new double[partlen];
		
		// copy loop
		int i = 0, j = partlen;
		while(i < partlen){
			alpha[i] = parts[i];
			beta[i] = parts[j];
			i++;
			j++;
		}
		a = new Number(alpha);
		b = new Number(beta);
		
	}
	
	public Number add(Number that){
			double[] add = new double[parts()];
			for(int i = 0; i < parts(); i++)
				add[i] = part(i)+that.part(i);
			return new CayleyDickson<Number>(add);
	}
	public Number subtract(Number that){
		return this.add(that.multiply(-1));
	}
	
	public Number multiply(Number that){
		return new CayleyDickson<Number>((this.a.multiply(that.a)).subtract(that.b.conj().multiply(this.b)), (this.a.multiply(that.b)).add(this.b.multiply(that.a.conj())));
	}
	
	public Number multiply(double that){
		double[] multiply = new double[parts()];
		for(int i = 0; i < parts(); i++)
			multiply[i] = part(i)*that;
		return new CayleyDickson<Number>(multiply);
	}
	
	/** Return the conjugate of the number */
	public Number conj(){
		return new CayleyDickson<Number>(a.conj(), b.multiply(-1));
	}

	/** 
	 * Return the part with the given index, where 0 is real, 1 is i, 2 is j...
	 * Should return 0 if the part does not exist (Numbercc.g. accessing part 5 of a Complex)
	 */
	public double part(int index){
		return parts[index];
	}
	
	/** Return the number of parts the Number has */
	public int parts(){
		return parts.length;
	}

}
