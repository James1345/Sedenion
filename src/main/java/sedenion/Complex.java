package sedenion;

public class Complex implements Extendable{


	// Immutable instance vars

	public final double re;
	public final double im;

	// Constructor
	
	public Complex(double re, double im){
		this.re = re;
		this.im = im;
	}

	// Lazy vals

	protected double abs = Double.NAN;
	protected Complex conj = null;
	protected String toString = null;
	protected Matrix toMatrix = null;
	protected double arg = Double.NAN;

	public Complex add(Complex that){
		return new Complex(this.re + that.re, this.im + that.im);
	}
	public Complex subtract(Complex that){
		return new Complex(this.re - that.re, this.im - that.im);
	}

	public Complex multiply(Complex that){
		return new Complex(this.re*that.re-this.im*that.im, this.re*that.im+this.im*that.re);
	}

	public Extendable conj(){
		if(null == conj) conj = new Complex(re, -im);
		return conj;
	}

	public Matrix toMatrix(){
		if(null == toMatrix) toMatrix = new Matrix( new double[] {re, -im, im, re}, 2);
		return toMatrix;
	}

	public double abs(){
		if(Double.NAN == abs) abs = Math.sqrt(re*re + im*im);
		return abs; 
	}

	public Complex divide(Complex that){
		new Complex((re*that.re + im*that.im)/(that.re*that.re + that.im*that.im), (im*that.re - re*that.im)/(that.re*that.re + that.im*that.im));
	}
}
