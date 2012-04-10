package org.sedenion.types;

import java.util.BitSet;

/**
 * A Quad precision floating point decimal number.
 * 
 * @author james
 *
 */
public class Inexact {

	// Use bitsets?
	private boolean sign = false;
	private BitSet exponent = new BitSet(15);
	private BitSet significand = new BitSet(112);
	
	public Inexact(Exact e){};
}
