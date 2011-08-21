package sedenion;

//TODO well, everything. Rotations, cross products. so much fun
public class Vector3D extends Vector {
	
	/* Instance vars */
	/** The x component of this Vector */
	public final double x;
	
	/** The y component of this vector */
	public final double y;
	
	public final double z;
	
	/**The magnitude (size) of this vector */
	public final double r;
	
	
	/**
	 * Default Constructor.
	 * 
	 * Creates a new 3-dimensional vector with the given x and y values.
	 * 
	 * @param x The x value of the vector
	 * @param y The y value of the vector
	 * @param z The z value of the vector
	 * @throws IllegalMatrixDimensionException never, needed to keep compiler happy.
	 */
	public Vector3D(double[] xyz) {
		super(xyz); //create Vector
		if(xyz.length != 3) throw new IllegalArgumentException("Must have exactly 3 dimensions"); // Check dimensions
		//set x, y and z
		x = xyz[0];
		y = xyz[1];
		z = xyz[2];
		this.r = this.magnitude();
	}
	
	//Instance Methods

}
