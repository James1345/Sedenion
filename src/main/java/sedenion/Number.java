public class Number{
	
	protected Number child1;
	protected Number child2;
	
	protected final boolean isLeaf;
	protected final double leafVal;
	
	// Tree Stuff
	
	public Number(Number child1, Number child2) throws IllegalArgumentException{
		if((child1.depth() != child2.depth())) {
			throw new IllegalArgumentException("Branch depths must be equal!");
		}
		this.child1=child1;
		this.child2 = child2;
		isLeaf=false;
		
		leafVal = 0;
	}
	
	public Number(double value){
		this.leafVal = value;		
		isLeaf=true;
		
		child1 = null;
		child2 = null;
	}
	
	public int depth(){
		boolean leaf = isLeaf;
		Number currentNode = this;
		int depth = 0;
		while(!leaf){
			depth++;
			currentNode = currentNode.child1;
			leaf = currentNode.isLeaf;
		}
		return depth;
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
				return new Number(this.child1.add(that), this.child2);
			} 
			
			// If the depth of THAT tree is greater than the depth of THIS tree, step down one level and leave the second half alone
			else if(this.depth() < that.depth()){
				return new Number(this.add(that.child1), that.child2);
			}
			
			// If the depths are the same, add the two halfs
			else {
				return new Number(this.child1.add(that.child1), this.child2.add(that.child2));
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
			return new Number(child1.$minus(), child2.$minus());
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
			return new Number(child1.conjugate(), child2.$minus());
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
				return new Number(this.child1.multiply(that), this.child2.multiply(that));
			} 
			
			// If the depth of THAT tree is greater than the depth of THIS tree
			else if(this.depth() < that.depth()){
				return new Number(this.multiply(that.child1), this.multiply(that.child2));
			}
			
			// If the depths are the same, multiply the two halfs
			else {
				return new Number(this.child1.multiply(that.child1).subtract(that.child2.conjugate().multiply(this.child2)), that.child2.multiply(this.child1).add(this.child2.multiply(that.child1.conjugate())));
			}
		}
	}
	
	
	public String toString(){
		int length = (int) Math.pow(2, depth());
		double[] parts = new double[length];
		
		/*
		 * Need to do tree walk
		 */
		
		return "";
	}
}