package sedenion;

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
public abstract class Vector extends Matrix {
	
	/**
	 * Vector Constructor
	 * 
	 */
	public Vector(double... array) {
		super(array, 1);
	}

	
	/**
	 * The magnitude of a Vector.
	 * 
	 * The magnitude of a Vector is calculated by taking the square root of the sum of the 
	 * squares of its values. It is equivalent to the length of the line that represents the
	 * vector in cartesian space.
	 * @return
	 */
	public double magnitude(){
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
	 * @param v The vector to be dotted with this
	 * @return The value of the dot product
	 * @throws ArrayIndexOutOfBoundsException if the Vectors are different lengths. 
	 */
	public double dot(Vector v) {
		if (this.rows != v.rows) //check lengths
			throw new IllegalArgumentException("Vector Lengths must match");
		
		double $dot = 0.0; //create accumulator
		for (int i = 0; i < this.rows; i++){
			$dot += this.get(i, 0)*v.get(i, 0);
		}
		return $dot;
	}
	
	/**
	 * Calculates the angle between two Vectors of equal dimensions.
	 * The angle between two vectors is calculated by taking the dot product
	 * of the two vectors (see {@link #dot(Vector)}), dividing it by the product
	 * of the magnitudes (see {@link #magnitude()}), then taking the inverse cosine of
	 * this.
	 * <br />
	 * Although this calculation can be performed on vectors with an arbitrary number of
	 * dimensions, interpreting the 'angle' between two vectors with 4 or more dimensions
	 * is left as an exercise for the reader.
	 * @param v the vector for which the angle with this is to be calculated
	 * @return The (acute) angle between the two vectors, in <i>Radians</i>
	 */
	public double angle(Vector v){
		return Math.acos(this.dot(v)/(this.magnitude()*v.magnitude()));
	}
	
	

}
