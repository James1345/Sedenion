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
 * specific to Vectors. (This does however leave it as a 2D array, so it is often useful to
 * create methods for specific values (as a vector is a 1D array, but the representation must 
 * be 2D to allow Matrix methods to be used)).
 * <p />
 * This class should be extended to create a Vector of the correct dimensions to
 * avoid clashes with java.util.Vector and to allow for Methods specific to those
 * dimensions (see Vector2D and Vector3D).
 * 
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public abstract class Vector extends Matrix {
	
	/**
	 * Vector Constructor
	 * 
	 * Constructs a 0 vector with the given number of dimensions.
	 * <br />
	 * 
	 * @param d the number of dimensions to give the vector
	 * @throws IllegalMatrixDimensionException never, needed to keep compiler happy
	 */
	public Vector(int dimensions) {
		super(makeConstructorArray(dimensions));
	}
	
	/**
	 * creates the array needed for calling the superclass' constructor
	 * 
	 * @param dimensions The number of rows for the array
	 * @return an array that can be used as the arguments in the constructor for {@link #Matrix}
	 */
	private static double[][] makeConstructorArray(int dimensions){
		double[][] ret = new double[dimensions][1];
		for(int i = 0; i < dimensions; i++){
			ret[i][0] = 0;
		}
		return ret;
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
		for(double[] row : array){
			for (double val : row){
				acc += Math.pow(val, 2);
			}
		}
		return Math.pow(acc, 0.5);
	}
	
	/**
	 * Calculates the dot product of two vectors.
	 * 
	 * This method calculates the dot product (also known as the scalar product) of two vectors
	 * of equal dimensions. This is equal to the sum of the products of corresponding pairs of values
	 * in the vectors. (It is also equal to the product of the magnitudes of the vectors and the
	 * angle between them).
	 * 
	 * @param v The vector to be dotted with this
	 * @return The value of the dot product
	 * @throws ArrayIndexOutOfBoundsException if the Vectors are different lengths. 
	 */
	public double dot(Vector v) throws ArrayIndexOutOfBoundsException{
		if (this.rows != v.rows) //check lengths
			throw new ArrayIndexOutOfBoundsException();
		
		double acc = 0.0; //create accumulator
		for (int i = 0; i < this.rows; i++){
			acc += this.get(i, 0)*v.get(i, 0);
		}
		return acc;
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
	 * @throws ArrayIndexOutOfBoundsException if the vectors are of different dimensions
	 */
	public double angle(Vector v)throws ArrayIndexOutOfBoundsException{
		return Math.acos(this.dot(v)/(this.magnitude()*v.magnitude()));
	}
	
	

}
