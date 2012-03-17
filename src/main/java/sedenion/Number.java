package sedenion;

import java.util.ArrayList;
import java.util.List;

public class Number{
	
	// Children of this node
	protected final Number[] children = new Number[2];
	
	// Leaf information
	protected final boolean isLeaf;
	protected final double leafVal;
	
	// Lazy values
	protected double[] toArray = null;
	protected String toString = null;
	protected double absSqr = -1;
	protected double abs = -1;
	
	// Tree Stuff
	
	public Number(Number child0, Number child1) throws IllegalArgumentException{
		if((child0.depth() != child1.depth())) {
			throw new IllegalArgumentException("Branch depths must be equal!");
		}
		this.children[0]=child0;
		this.children[1] = child1;
		isLeaf=false;
		
		leafVal = 0;
	}
	
	public Number(double value){
		this.leafVal = value;		
		isLeaf=true;
		
		children[0] = null;
		children[1] = null;
	}
	
	public int depth(){
		boolean leaf = isLeaf;
		Number currentNode = this;
		int depth = 0;
		while(!leaf){
			depth++;
			currentNode = currentNode.children[0];
			leaf = currentNode.isLeaf;
		}
		return depth;
	}
	
	/**
	 * Walks the tree representation of this Number and converts it into a flat array.
	 * 
	 * While a flat array is a useful representation for some operations, most (such as
	 * addition of numbers with differing depths) require the tree representation. This method is
	 * lazy (Numbers are immutable)
	 * 
	 * @return This Number as an array of values.
	 */
	public double[] toArray(){
		
		if (toArray == null){
		
			// special list to recurse down tree
			List<Number> list = new ArrayList<Number>(){
	
				private static final long serialVersionUID = 1226049117990822895L;
				
				/**
				 * Checks if the passed number is a leaf. If it is, its value is added to the
				 * list. Otherwise, this is recursively called on the children of the number.
				 * 
				 * @param number the number to test
				 */
				public boolean add(Number number){
					if(number.isLeaf) {
						return super.add(number); // if this is a leaf, add it
					}
					else {
						return (add(number.children[0]) && add(number.children[1])); //call on children
					}
						
				}
				
			};
			
			list.add(this);
			Object[] classArray = list.toArray();
			toArray = new double[classArray.length];
			for(int i = 0; i < toArray.length; i++){
				toArray[i] = ((Number)classArray[i]).leafVal;
			}
		}
		
		return toArray;
	}
	
	/**
	 * Returns the string representation of this number.
	 * 
	 * Returns in the format ae0+be1+ce2+... where a, b, c are the
	 * double representations of each part of the number.
	 */
	public String toString(){
		
		if(toString == null){
			StringBuilder builder = new StringBuilder("");
			for(int i = 0; i < toArray().length; i++){
				builder
					.append(toArray()[i])
					.append('e')
					.append(i)
					.append('+');
			}
			builder.deleteCharAt(builder.lastIndexOf("+")); // remove trailing +
			toString = builder.toString();
		}
		
		return toString;
	}
	
	// Maths stuff
	
	/**
	 * Add
	 * @param that
	 * @return
	 */
	public Number add(Number that){
		
		// If they are both leaves, sum directly
		if(isLeaf && that.isLeaf){
			return new Number(this.leafVal + that.leafVal);
		} 
		
		// They are not both leaves
		else {
			// If the depth of this tree is greater than the depth of that tree, step down one level and leave the second half alone
			if(this.depth() > that.depth()){
				return new Number(this.children[0].add(that), this.children[1]);
			} 
			
			// If the depth of THAT tree is greater than the depth of THIS tree, step down one level and leave the second half alone
			else if(this.depth() < that.depth()){
				return new Number(this.add(that.children[0]), that.children[1]);
			}
			
			// If the depths are the same, add the two halfs
			else {
				return new Number(this.children[0].add(that.children[0]), this.children[1].add(that.children[1]));
			}
		}
	}
	
	/**
	 * Unary Minus
	 * @return
	 */
	public Number $minus(){
		if(isLeaf){
			return new Number(-leafVal);
		}
		else {
			return new Number(children[0].$minus(), children[1].$minus());
		}
	}
	
	/**
	 * Add -that
	 * @param that
	 * @return
	 */
	public Number subtract(Number that){
		return this.add(that.$minus());
	}
	
	/**
	 * Conjugate
	 * @return
	 */
	public Number conjugate(){
		if(isLeaf){
			return new Number(leafVal);
		} 
		else {
			return new Number(children[0].conjugate(), children[1].$minus());
		}
	}
	
	/**
	 * Multiply
	 * @param that
	 * @return
	 */
	public Number multiply(Number that){
		// If they are both leaves
		if(isLeaf && that.isLeaf){
			return new Number(this.leafVal * that.leafVal);
		} 
		
		// They are not both leaves
		else {
			// If the depth of this tree is greater than the depth of that tree
			if(this.depth() > that.depth()){
				return new Number(this.children[0].multiply(that), this.children[1].multiply(that));
			} 
			
			// If the depth of THAT tree is greater than the depth of THIS tree
			else if(this.depth() < that.depth()){
				return new Number(this.multiply(that.children[0]), this.multiply(that.children[1]));
			}
			
			// If the depths are the same, multiply the two halfs
			else {
				return new Number(this.children[0].multiply(that.children[0]).subtract(that.children[1].conjugate().multiply(this.children[1])), that.children[1].multiply(this.children[0]).add(this.children[1].multiply(that.children[0].conjugate())));
			}
		}
	}
	
	/**
	 * Return the absolute value squared of this Number
	 */
	public double absoluteSquared(){
		if(absSqr == -1)
			for(double d : toArray())
				abs+=Math.pow(d, 2);
		
		return absSqr;
	}
	
	/**
	 * Return the absolute value (length) of this Number
	 */
	public double absolute(){
		if(abs == -1) abs = Math.sqrt(absoluteSquared());
		return abs;
	}
	
	
}