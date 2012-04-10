package org.sedenion.types;

import java.math.BigInteger;

/**
 * A class to exactly represent Real numbers as a ratio of two arbitrary-precision integers.
 * 
 * This class internally uses java.math.BigInteger, and also implements methods required to compare to java.lang.Math class.
 * @author james
 *
 */
public class Exact implements Comparable<Exact>, Cloneable{

	/* Static Values */
	public static Exact ONE = new Exact(BigInteger.ONE);
	public static Exact TEN = new Exact(BigInteger.TEN);
	public static Exact ZERO = new Exact(BigInteger.ZERO);
	
	/** Numerator and Denominator */
	private BigInteger n, d;
	
	/* 
	 * Constructors
	 * 
	 * Constructors are chained for as much code reuse as possible
	 */
	public Exact(BigInteger n, BigInteger d){
		BigInteger gcd = gcd(n, d);
		this.n = n.divide(gcd);
		this.d = d.divide(gcd);
	}
	
	public Exact(BigInteger n){
		this(n, BigInteger.ONE);
	}

	public Exact(long n, long d){
		this(new BigInteger(String.format("%d", n)), new BigInteger(String.format("%d", d)));
	}
	
	public Exact(long n){
		this(n, 1L);
	}
	
	private BigInteger gcd(BigInteger a, BigInteger b){
		if(b.equals(BigInteger.ZERO)) return a;
		else return gcd(b, a.remainder(b));
	}
	
	/* Basic Operators */
	
	public Exact add(Exact addend){
		return new Exact(n.multiply(addend.d).add(addend.n.multiply(d)), d.multiply(addend.d));
	}
	
	public Exact subtract(Exact subtrahend){
		return new Exact(n.multiply(subtrahend.d).subtract(subtrahend.n.multiply(d)), d.multiply(subtrahend.d));
	}
	
	public Exact multiply(Exact multiplicand){
		return new Exact(n.multiply(multiplicand.n), d.multiply(multiplicand.d));
	}
	
	public Exact multiply(long l){
		return multiply(new Exact(l));
	}
	
	public Exact divide(Exact divisor){
		return new Exact(n.multiply(divisor.d), d.multiply(divisor.n));
	}
	
	/* Maths functions */
	
	public Exact abs(){
		BigInteger n = this.n;
		BigInteger d = this.d;
		if(n.doubleValue()<0) n = n.multiply(new BigInteger("-1"));
		if(d.doubleValue()<0) d = d.multiply(new BigInteger("-1"));
		return new Exact(n, d);
	}
	
	/**
	 * Raise this exact to the given index
	 * 
	 * Slow implementation
	 * TODO implement this with fewer calls to multiply.
	 * @param n
	 * @return
	 */
	public Exact pow(int n){
		Exact pow = this.clone();
		for(int i = 1; i < n; i++){
			pow = pow.multiply(pow);
		}
		return pow;
	}
	
	/**
	 * Return the signum function on this Exact
	 * @return
	 */
	public int signum(){
		if(compareTo(ZERO)==0) return 0;
		else if(compareTo(ZERO)>0)return 1;
		else return -1;
	}
	
	/* TODO fix this
	public Exact sqrt(){
		return new Exact()
	}
	*/
	
	/* Conversions */
	
	/** Deep clone this Exact */
	@Override
	public Exact clone(){
		return new Exact(new BigInteger(n.toString()), new BigInteger(d.toString()));
	}
	
	@Override
	public String toString(){
		if(d.equals(BigInteger.ONE)){
			return n.toString();
		}
		else {
			return String.format("%s/%s", n.toString(), d.toString());
		}
	}
	
	public Inexact toInexact(){
		// TODO update when Inexact is implemented
		return new Inexact(this);
	}
	
	/* comparisons */
	
	@Override
	public int compareTo(Exact arg0) {
		return n.multiply(arg0.d).compareTo(arg0.n.multiply(d));
	}
	
	/**
	 * Returns the maximum of this Exact and val
	 * 
	 * If the values are equal, this Exact is returned
	 * @param val
	 * @return
	 */
	public Exact max(Exact val){
		return compareTo(val) > 0 ? this : val;
	}
	
	/**
	 * Returns the minimum of this Exact and val
	 * 
	 * If the values are equal, this Exact is returned
	 * @param val
	 * @return
	 */
	public Exact min(Exact val){
		return compareTo(val) > 0 ? val : this;
	}
	
	@Override
	public int hashCode(){
		return n.hashCode() & d.hashCode();
	}
	
	@Override
	public boolean equals(Object arg0){
		if(arg0 instanceof Exact){
			Exact e = (Exact)arg0;
			return n==e.n && d==e.d;
		}
		else {
			return false;
		}
	}

}
