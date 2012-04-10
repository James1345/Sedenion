package org.sedenion.complex;

/**
 * A general purpose Vector class.
 * 
 * A vector represents a point in Cartesian space (alternatively they can be
 * used to represent the line that is equivalent to moving from the origin to
 * that point).
 * <br />
 * It is represented as a Matrix with only one column and as many rows as the 
 * Cartesian space has dimensions (normally 2 or 3). As such standard matrix
 * methods may be applied (multiplying by or adding specific matrices can represent
 * transformations (rotations, enlargements, translations)) as well as methods
 * specific to Vectors.
 * <p />
 * This class should be extended to create a Vector of the correct dimensions to
 * avoid clashes with java.util.Vector and to allow for methods specific to those
 * dimensions (see Vector2D and Vector3D).
 * 
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class Vector extends Matrix {
	
	/**
	 * Vector Constructor
	 * 
	 */
	public Vector(double... array) {
		super(array, 1);
	}
	
	protected static Vector fromMatrix(Matrix m){
		if(1 != m.cols) throw new IllegalArgumentException("Matrix dimensions cannot be cast to vector"); 
		return new Vector(m.clone().array); // As is a sublclass Vector can access protected variable. Using clone to avoid sharing an array
	}
	
	public double get(int row){
		return get(row, 1);
	}
	
	public int getDimensions(){
		return cols;
	}

	/**
	 * The magnitude of a Vector.
	 * 
	 * The magnitude of a Vector is calculated by taking the square root of the sum of the 
	 * squares of its values. It is equivalent to the length of the line that represents the
	 * vector in cartesian space.
	 * @return
	 */
	public double getMagnitude(){
		double acc = 0.0; //create accumulator
		
		//Pythagoras' theorem
		for(double value : array){
			acc += Math.pow(value, 2);
		}
		return Math.pow(acc, 0.5);
	}
	
	/**
	 * Calculates the dot product of two vectors.
	 * 
	 * This method calculates the dot product (also known as the scalar product) of two vectors
	 * of equal array. This is equal to the sum of the products of corresponding pairs of values
	 * in the vectors. (It is also equal to the product of the magnitudes of the vectors and the
	 * angle between them).
	 * 
	 * @param that The vector to be dotted with this
	 * @return The value of the dot product
	 * @throws ArrayIndexOutOfBoundsException if the Vectors are different lengths. 
	 */
	public double dot(Vector that) {
		if (this.rows != that.rows) //check lengths
			throw new IllegalArgumentException("Vector Lengths must match");
		
		double dot = 0.0; //create accumulator
		for (int i = 0; i < this.rows; i++){
			dot += this.get(i, 0)*that.get(i, 0);
		}
		return dot;
	}
	
	
	/**
	 * Calculates the angle between two Vectors of equal dimensions.
	 * The angle between two vectors is calculated by taking the dot product
	 * of the two vectors (see {@link #dot(Vector)}), dividing it by the product
	 * of the magnitudes (see {@link #getMagnitude()}), then taking the inverse cosine of
	 * this.
	 * <br />
	 * Although this calculation can be performed on vectors with an arbitrary number of
	 * dimensions, interpreting the 'angle' between two vectors with 4 or more dimensions
	 * is left as an exercise for the reader.
	 * @param that the vector for which the angle with this is to be calculated
	 * @return The (acute) angle between the two vectors, in <i>Radians</i>
	 */
	public double angle(Vector that){
		return Math.acos(this.dot(that)/(this.getMagnitude()*that.getMagnitude()));
	}
	
	// FROM Vector2D
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
	public Vector rotate(double angle){
		return Vector.fromMatrix(Vector.rotation2D(angle).multiply(this));
	}
	
	// From Vector3D
	public Vector cross(Vector that){
	
		if(this.getDimensions() != 3 || that.getDimensions() != 3) throw new IllegalArgumentException("Can only cross 3D vectors");
	
		double x = this.get(1)*that.get(2)-this.get(2)*that.get(1);
		double y = this.get(2)*that.get(0)-this.get(0)*that.get(2);
		double z = this.get(0)*that.get(1)-this.get(1)*that.get(2);
		
		return new Vector(x,y,z);
	
	}
	
	/*Class Methods*/
	
	// FROM Vector2D
	/**
	 * Rotation Matrix.
	 * 
	 * Returns the Matrix that can 'left-multiply' a Vector to rotate it anticlockwise by
	 * the given angle, about the axis of the 3rd dimension (the 'z' axis). Only really makes sense for a 2D matrix atm
	 * 
	 * @param angle The angle of rotation (in <i>Radians</i>)
	 * @return The Matrix of rotation.
	 */
	public static Matrix rotation2D(double angle){
		double[] array = {Math.cos(angle), -Math.sin(angle),Math.sin(angle), Math.cos(angle)};
		return new Matrix(array, 2);
	}
	

}
