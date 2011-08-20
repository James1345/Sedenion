package sedenion;

/**
 * A class to handle 2-dimensional vectors (i.e. vectors in the x,y plane).
 * 
 * Vectors are a subclass of Matrix. As such, they may either be used as either Vectors or Matrices
 * (as explained in the {@link Vector} class). <br />
 * Vector2D stores both the Cartesian (x, y) and Polar (r, theta) representations of a Vector, which are
 * calculated at creation. It stores them in both the array[][] array (as defined by {@link Matrix}) and
 * also as its own public final instance variables (x, y, r and theta).
 * 
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class Vector2D extends Vector {
	
	/* Instance vars */
	/** The x component of this Vector */
	public final double x;
	
	/** The y component of this vector */
	public final double y;
	
	/**The magnitude (size) of this vector */
	public final double r;
	
	/** The argument (angle) of this vector, measured anticlockwise from the positive x axis */
	public final double theta;
	
	/* Constructor */
	
	/**
	 * Default Constructor.
	 * 
	 * Creates a new 2-dimensional vector with the given x and y values.
	 * 
	 * @param x The x value of the vector
	 * @param y The y value of the vector
	 * @throws IllegalMatrixDimensionException never, needed to keep compiler happy.
	 */
	public Vector2D(double[] xy)  {
		super(xy); //create Vector
		if(xy.length != 2) throw new IllegalArgumentException("Must have exactly 2 dimensions"); // Check dimensions
		//set x and y
		x = xy[0];
		y = xy[1];
		this.r = this.magnitude();
		this.theta = Math.atan2(y, x);
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
		double[] array = {Math.cos(angle), -Math.sin(angle),Math.sin(angle), Math.cos(angle)};
		return new Matrix(array, 2);
	}
	
	/*Instance Methods*/
	
	
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
		/*
		 * Multiply by rotation matrix.
		 * Done by hand. More efficient than actually constructing the rotation
		 * Matrix and then multiplying by it. (similar to using a Complex number for rotation)
		 */
		double[] xy = new double[2];
		xy[0] = Math.cos(angle)*x-Math.sin(angle)*y; //new x
		xy[1] = Math.sin(angle)*x+Math.cos(angle)*y; //new y
		return new Vector2D(xy);
	}

}
