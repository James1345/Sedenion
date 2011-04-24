package com.alexandria.euclid;

/**
 * An interface implemented by any class that can write a string
 * @author james
 *
 */
public interface Writable {
	
	/**
	 * Writes the string in the appropriate manner and return the number of bytes of data written
	 * @param s The string to write
	 * @return The number of bytes in the string
	 */
	public int write(String s);
	
}
