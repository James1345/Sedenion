package com.alexandria.math;

/**
 * A class to handle 2-dimensional vectors (i.e. vectors in the x,y plane).
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class Vector2D extends Vector {
	
	/* Constructor */
	
	/**
	 * Default Constructor.
	 * 
	 * Creates a new 2-dimensional vector with the given x and y values.
	 * 
	 * @param x The x value of the vector
	 * @param y The y value of the vector
	 */
	public Vector2D(double x, double y) {
		super(2, 0); //create Vector of correct dimensions
		this.content[0][0] = x;
		this.content[1][0] = y;
	}
	
	/*Getters*/
	/**
	 * Quick Method to get x value of Vector.
	 * @return The x value.
	 */
	public double x(){
		return this.content[0][0];
	}
	
	/**
	 * Quick Method to get y value of Vector.
	 * @return The y value.
	 */
	public double y(){
		return this.content[1][0];
	}
	
	/*Class Methods*/
	
	/**
	 * Rotation Matrix.
	 * 
	 * Returns the Matrix that can 'left-multiply' a Vector2D to rotate it anticlockwise by
	 * the given angle.
	 * 
	 * @param angle The angle of rotation (in <i>Radians</i>)
	 * @return The Matrix of rotation.
	 */
	public static Matrix rotational(double angle){
		double[][] content = {{Math.cos(angle), -Math.sin(angle)},{Math.sin(angle), Math.cos(angle)}};
		return new Matrix(content);
	}
	
	/*Instance Methods*/
	
	/**
	 * Calculates the polar representation of The vector.
	 * 
	 * This method returns an array [r, t]; where r is the magnitude of the vector and t is
	 * the argument (in <i>Radians</i>).
	 * <br />
	 * The Polar representation of a vector uses the length (magnitude) of the vector, and the 
	 * angle (argument) to the positive x axis to uniquely describe the position of the vector,
	 * rather than x,y positions. The two representations are equivalent, and useful for different purposes.
	 * 
	 * @return The (r, t) Polar coordinates of the vector
	 */
	public double[] toPolar(){
		double r = this.magnitude();
		double t = Math.atan2(this.content[1][0], this.content[0][0]);
		double[] rt = {r,t};
		return rt;
	}
	
	/**
	 * Rotates a Vector.
	 * 
	 * Rotates a vector around the origin (0,0) by a given angle (in <i>Radians</i>).
	 * <br />
	 * Assuming that x increases from left to right and y from bottom to top, the rotation
	 * is anticlockwise (rotating by a negative angle will rotate clockwise).
	 * <br />
	 * Currently slightly inaccurate (due to limited accuracy of doubles). Multiple, smaller rotations
	 * to achieve one larger rotation improve accuracy (but increase number of operations).
	 * 
	 * @param angle the angle to rotate by.
	 * @return The rotated vector.
	 */
	public Vector2D rotate(double angle){
		Vector2D rotated = new Vector2D(0,0); //create new return vector
		/*
		 * Multiply by rotation matrix.
		 * Done by hand. More efficient than actually constructing the rotation
		 * Matrix and then multiplying by it.
		 */
		rotated.content[0][0] = Math.cos(angle)*this.content[0][0]-Math.sin(angle)*this.content[1][0]; //new x
		rotated.content[1][0] = Math.sin(angle)*this.content[0][0]+Math.cos(angle)*this.content[1][0]; //new y
		return rotated;
	}

}
