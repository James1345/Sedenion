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
	public Vector3D(double x, double y, double z) {
		super(x, y, z); //create Vector
		//set x, y and z
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = this.magnitude();
	}
	
	//Instance Methods

	public Vector3D cross(Vector3D that){
	
		double x = this.y*that.z-this.z*that.y;
		double y = this.z*that.x-this.x*that.z;
		double z = this.x*that.y-this.y*that.z;
		
		return new Vector3D(x,y,z);
	
	}
	
}
