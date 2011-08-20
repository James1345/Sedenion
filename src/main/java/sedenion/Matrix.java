package sedenion;

/**
 * A class to handle Matrices.
 * 
 * This class deals with Matrices. A Matrix is a 2D array of numbers, with
 * special rules for performing mathematical operations on them. 
 * <br />
 * The matrix is stored as an array of rows. Therefore, coordinates select the <b>row</b> first,
 * then the column.
 * <br />
 * All instance and class methods return a <b>new</b> Matrix, without modifying any parameters
 * or the Matrix on which the method is called. Assigning these returns to a variable (even the 
 * original) must be done explicitly.
 * 
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class Matrix implements Cloneable{
	
	/* Instance Variables. */
	
	/** The actual values held in the matrix, use {@link #get(int, int)} to access.*/
	protected double[] array;
	
	/** The number of columns */
	public final int cols;
	
	/** the number of rows*/
	public final int rows;
	
	/** columns == rows*/
	public final boolean isSquare;
	
	/* Getters */
	
	/**
	 * Gets a value at the specified row/column.
	 * @param row The row
	 * @param column The column
	 * @return The value at (row, column), a double
	 */
	public double get(int row, int column){
		return array[row*cols + column];
	}
	
	public Matrix(double[] array, int cols) {
		if( 0 != array.length % cols) throw new IllegalArgumentException("Matrix size impossible.");
		this.cols = cols;
		this.rows = array.length/cols;
		this.isSquare = rows==cols;
		this.array = array;
	}
	
	/* Class Methods */
	/* Class methods currently broken */
	
	/**
	 * Swap Matrix.
	 * 
	 * This method returns a matrix that, when used to 'left-multiply' a matrix (m), will swap rowA and rowB
	 * of m (rowA and rowB being the parameters) To be used this matrix must have the same number of
	 * rows as m does columns.
	 * 
	 *  @param size the size of the return matrix (always square, i.e. columns = rows)
	 *  @param rowA The first row to be swapped.
	 *  @param rowB The second row to be swapped.
	 */
	/*
	public static Matrix h(int size, int rowA, int rowB){
		double[][] h = new double[size][size];
		//construct identity matrix
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				h[i][j] = i==j ? 1 : 0 ;
			}
		}
		
		//swap rows
		double[] swap = h[rowA];
		h[rowA] = h[rowB];
		h[rowB] = swap;
		
		return new Matrix(h);
	}
	
	//TODO javadoc
	public static Matrix M(int size, int row, double lambda){
		return null;
	}
	
	//TODO javadoc
	public static Matrix F(int size, int rowA, int rowB, double lambda){
		return null;
	}
	*/
	
	/* Row operations */
	
	/**
	 * Swaps two rows of a matrix.
	 * @param rowA First Row to be swapped
	 * @param rowB Second Row to be swapped
	 * @return A matrix with rows A and B swapped.
	 */
	public Matrix h(int rowA, int rowB){
		Matrix h = this.clone(); // Deep clone the matrix
		double[] newArray = h.array; //Extract array
		System.arraycopy(array, rowA*cols, newArray, rowB*cols, cols); // Copy rowA into new position
		System.arraycopy(array, rowB*cols, newArray, rowA*cols, cols); // Copy rowB into new position
		return h;
	}
	
	/**
	 * Scales a row of a matrix.
	 * 
	 * This method multiplies every value in a given row by a scale factor (lambda).
	 * @param row The row to be scaled
	 * @param lambda The scale factor (amount to be multiplied by)
	 * @return The new Matrix with the row scaled
	 */
	public Matrix m(int row, double lambda){
		Matrix m = this.clone(); // deep clone matrix
		double[] newArray = m.array; // extract array
		for(int i = row*cols; i < (row+1)*cols; i++) newArray[i]*=lambda;
		return m; // Return matrix with the row scaled
	}
	
	/**
	 * Performs a sum on two rows.
	 * 
	 * This method takes each value in row B, multiplies it by a scale factor (lambda), and then adds
	 * it to the corresponding value in row A. The Matrix returned contains these new values in row A,
	 * while row B is unaffected by the operation.
	 * 
	 * @param rowA The row to be added to.
	 * @param rowB The row to add.
	 * @param lambda The scale factor.
	 * @return A new matrix, with the columns changed appropriately.
	 */
	public Matrix f(int rowA, int rowB, double lambda){
		Matrix f = this.clone();
		double[] newArray = f.array; // extract array for editing
		for (int i = 0; i < cols; i++) // for each value in rowA
			newArray[rowA*cols + i]+=array[rowB*cols + i]*lambda ; // Add the scaled rowB
		return f; // Return matrix with the rows added
	}
	
	/* aliases with nicer names */
	
	/**
	 * Swaps two rows of a matrix.
	 * 
	 * Calls {@link #h(int, int)}
	 * 
	 * @param rowA First Row to be swapped
	 * @param rowB Second Row to be swapped
	 * @return A matrix with rows A and B swapped.
	 */
	public Matrix swapRows(int rowA, int rowB){
		return h(rowA, rowB);
	}
	
	/**
	 * Scales a row of a matrix.
	 * 
	 * This method multiplies every value in a given row by a scale factor (lambda).
	 * Calls {@link #m(int, double)}
	 * 
	 * @param rowA The row to be scaled
	 * @param lambda The scale factor (amount to be multiplied by)
	 * @return The new Matrix with the row scaled
	 */
	public Matrix scaleRow(int rowA, double lambda){
		return m(rowA, lambda);
	}
	
	/**
	 * Performs a sum on two rows.
	 * 
	 * This method takes each value in row B, multiplies it by a scale factor (lambda), and then adds
	 * it to the corresponding value in row 2. The Matrix returned contains these new values in row 2,
	 * while row B is unaffected by the operation.
	 * Calls {@link #f(int, int, double)}
	 * 
	 * @param rowA The row to be added to.
	 * @param rowB The row to add.
	 * @param lambda The scale factor.
	 * @return A new matrix, with the columns changed appropriately.
	 */
	public Matrix sumRows(int rowA, int rowB, double lambda){
		return f(rowA, rowB, lambda);
	}
	
	// Matrix operations

	/**
	 * Adds two Matrices.
	 * 
	 * This method adds two matrices of the same dimensions by adding each pair
	 * of elements that are at a particular set of (i,j) coordinates.
	 * i.e. (0,0) in the new matrix is the sum of the (0,0) elements in the two
	 * i.e. (0,0) in the new matrix is the sum of the (0,0) elements in the two
	 * matrices being added.
	 * 
	 * @param m The matrix to add to this.
	 * @throws IllegalArgumentException An exception if the two matrices are of different sizes.
	 */
	public Matrix add(Matrix that) throws IllegalArgumentException{
		if (this.cols != that.cols || this.rows != that.rows) throw new IllegalArgumentException("Matrix Dimensions must match");
		
		Matrix minussed = this.clone(); // deep clone Matrix
		double[] newArray = minussed.array; // Extract array for editing
		for(int i = 0; i < newArray.length; i++){ // For each value
			newArray[i] += that.array[i];
		}
		return minussed; // Return matrix with the values added
	}
	
	/**
	 * Subtracts one matrix from another
	 * 
	 * As {@link #add(Matrix)} except that the parameter is subtracted from this
	 * rather than added.
	 * 
	 * @param that The matrix to subtract from this.
	 * @throws IllegalArgumentException An exception if the two matrices are of different sizes.
	 */
	public Matrix subtract(Matrix that) throws IllegalArgumentException{
		if (this.cols != that.cols || this.rows != that.rows) throw new IllegalArgumentException("Matrix Dimensions must match");
		
		Matrix minussed = this.clone(); // deep clone Matrix
		double[] newArray = minussed.array; // Extract array for editing
		for(int i = 0; i < newArray.length; i++){ // For each value
			newArray[i] -= that.array[i];
		}
		return minussed; // Return matrix with the values subtracted
	}
	
	/**
	 * Multiplies two matrices.
	 * 
	 * Matrix multiplication is not commutative (i.e. A*B != B*A). It is also possible that A*B is possible
	 * where B*A is not. Therefore, this method 'right-multiplies' matrix that with this 
	 * (i.e. this*that; <b>NOT</b> that*this). 
	 * <br />
	 * Matrix multiplication only works when the number of columns in this is equal to the number of
	 * rows in that. A new matrix is formed. This is populated by numbers calculated from the original
	 * two matrices in the following manner. The value at the new (i,j) is taken by multiplying
	 * corresponding values in row i of this and column j of that, and then taking the sum of these
	 * products. 
	 * 
	 * @param that The matrix to multiply this by.
	 * @return The new matrix.
	 */
	public Matrix multiply(Matrix that) {
		if (this.cols != that.rows) throw new IllegalArgumentException("MatrixSizesIncompatible.");
		double[] newArray = new double[that.cols*rows];
		for(int i = 0; i < this.rows; i++){
			for (int j = 0; j < that.cols; j++) {
				newArray[i*that.cols + j] = 0.0;
				for(int k = 0; k < this.cols; k++){
					newArray[i*that.cols + j] += this.get(i, k)*that.get(k,j);
				}
			}
		}
		return new Matrix(newArray, that.cols);
	}
	
	/**
	 * Scales a matrix.
	 * 
	 * Multiplies every value in a matrix by a given value (lambda).
	 * 
	 * @param lambda The amount by which to scale the matrix.
	 * @return The new, scaled matrix.
	 */
	public Matrix scale(double lambda){
		Matrix scaled = this.clone(); // deep clone Matrix
		double[] newArray = scaled.array; // Extract array for editing
		for (int i = 0; i < array.length; i++){
			newArray[i] = array[i]*lambda; // Multiply and store in new Matrix
		}
		return scaled;
	}
	
	//TODO Make det use LU matrix reduction http://en.wikipedia.org/wiki/LU_decomposition
	/**
	 * Calculates the determinant of a square matrix.
	 * 
	 * Calculates the <a href='http://en.wikipedia.org/wiki/Determinant'>determinant</a> of a square matrix.
	 * (See link for full explanation of what the determinant is).
	 * 
	 * Currently uses the Laplace algorithm and recursion. Not good. need to fix cos this is sloooooow.
	 * 
	 * @return The determinant of this.
	 * @throws IllegalArgumentException If the matrix is not square.
	 */
	 /*
	public double det() throws IllegalArgumentException{
		if (!this.isSquare)
			throw new IllegalArgumentException();
		
		// For a 1x1 Matrix
		if (this.columns == 1)
			return this.get(0, 0);
		
		// For a 2x2 matrix
		if (this.columns == 2)
			return (this.get(0, 0)*this.get(1, 1)) - (this.get(0,1)*this.get(1, 0));
		
		// For an nxn Matrix (where n>2)
		double acc = 0; //Create accumulator
		for(int j = 0; j < this.rows; j++){// In each column
			// Recursively add or subtract ('chess board') the parts of the determinant
			if (j%2 == 0)
				acc += this.get(0, j) * this.sub(0,j).det();
			else
				acc -= this.get(0, j) * this.sub(0,j).det();
		}
		return acc;
	}*/
	
	/**
	 * Transposes a matrix.
	 * 
	 * Forms the transpose of a Matrix. This is formed by reflecting about the leading diagonal, i.e.
	 * using columns as rows and vice versa. ((0,0) stays in place, (0,1) becomes (1,0) etc.)
	 * 
	 * @return The transposed matrix.
	 */
	 /*
	public Matrix transpose(){
		double[][] newArray = new double[this.columns][this.rows]; // create new dimensions
		// Transpose the matrix
		for(int i = 0; i < this.rows; i++){	
			for(int j = 0; j < this.columns; j++){
				newArray[j][i] = this.get(i, j);
			}
		}
		return new Matrix(newArray);
		
	}*/
	

	/* Utility methods */
	
	/**
	 * Returns the Matrix as a printable String.
	 * 
	 * This Method returns a String that represents the matrix in a printable form.
	 */
	@Override
	public String toString(){
		String returnString = "";
		for (int i = 0; i < rows; i++){
			returnString += "{ ";
			for(int j = 0; j < cols-1; j++) {
				returnString += String.valueOf(get(i, j)) + ", ";
			}
			returnString += String.valueOf(get(i, cols-1)) + " }\n";
		}
		return returnString;
	}
	
	/**
	 * Copies the Matrix.
	 * 
	 * Provides a 'Deep' clone of the matrix. Therefore, changes to values in the returned matrix will
	 * not affect the original.
	 * 
	 * Used in methods of Matrix (and its subclasses) to leave the original unchanged when methods are
	 * called.
	 * 
	 * @return Matrix clone.
	 */
	@Override
	protected Matrix clone(){
		double[] arrayB = new double[array.length];
		for ( int i = 0; i < array.length; i++ ) {
			arrayB[i] = array[i];
		}
		return new Matrix(arrayB, this.cols);
	}
	
	/**
	 * Constructs a new matrix, removing the row and column containing value (i, j).
	 * 
	 * @param row The row to be removed
	 * @param column The column to be removed
	 * @return The newly formed matrix.
	 */
	 /*
	protected Matrix sub(int row, int column){
		double[][] newArray = new double[this.columns - 1][this.rows - 1]; // Construct new array
		// Copy correct values
		for (int i = 0; i < row; i++){ // for each row before row
			for (int j = 0; j < column; j++){ // For each column before column
				newArray[i][j] = this.get(i, j);
			}
			for (int j = column + 1; j < this.rows; j++){// For each column After column
				newArray[i][j-1] = this.get(i, j);
			}
		}
		for (int i = row + 1; i < this.columns; i++){ // for each row after row
			for (int j = 0; j < column; j++){ // For each column before column 
				newArray[i-1][j] = this.get(i, j);
			}
			for (int j = column + 1; j < this.rows; j++){//For each column After column
				newArray[i-1][j-1] = this.get(i, j);
			}
		}
		
		return new Matrix(newArray);
	}*/
}

//////////////////////////////////////////////////////////////////////////////////////
//										END OF FILE 								//
//////////////////////////////////////////////////////////////////////////////////////
