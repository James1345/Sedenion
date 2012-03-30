package sedenion;

import java.util.Arrays;

/**
 * A class to handle Matrices.
 * 
 * This class deals with Matrices. A Matrix is a 2D array of numbers, with
 * special rules for performing mathematical operations on them. 
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
		
		Matrix add = this.clone(); // deep clone Matrix
		double[] newArray = add.array; // Extract array for editing
		for(int i = 0; i < newArray.length; i++){ // For each value
			newArray[i] += that.array[i];
		}
		return add; // Return matrix with the values added
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
		
		Matrix subtract = this.clone(); // deep clone Matrix
		double[] newArray = subtract.array; // Extract array for editing
		for(int i = 0; i < newArray.length; i++){ // For each value
			newArray[i] -= that.array[i];
		}
		return subtract; // Return matrix with the values subtracted
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
	
	/**
	 * Calculates the determinant of a square matrix.
	 * 
	 * Calculates the <a href='http://en.wikipedia.org/wiki/Determinant'>determinant</a> of a square matrix.
	 * (See link for full explanation of what the determinant is).
	 * 
	 * For a 1x1 or 2x2 Matrix this method uses the relavent formula to calculate the determinant as quickly as possible (e.g. for a 2x2 matrix the classic ad-bc is used). For 4x4 or larger the Laplace formula is used (to be replaced with decomposition)
	 * 
	 * @return The determinant of this.
	 * @throws IllegalArgumentException If the matrix is not square.
	 */
	public double determinant() {
		if (!this.isSquare) throw new IllegalArgumentException("Matrix must be square to calculate determinant");
		
		// For a 1x1 Matrix
		if (1 == cols)return get(0, 0);
		
		// For a 2x2 matrix
		if (2 == cols) return (get(0, 0)*get(1, 1)) - (get(0,1)*get(1, 0));

		// For an nxn Matrix (where n>2)
		double acc = 0; //Create accumulator
		for(int j = 0; j < rows; j++){// In each column
			// Recursively add or subtract ('chess board') the parts of the determinant
			// Parts are calculate by multiplying the top number of a column (the pivot) by the determinant of the matrix of minors formed by removing the row and column containing the pivot.
			if (j%2 == 0) acc += get(0, j) * minor(0,j).determinant();
			else acc -= get(0, j) * minor(0,j).determinant();
		}
		return acc;
	}
	
	/**
	 * Transposes a matrix.
	 * 
	 * Forms the transpose of a Matrix. This is formed by reflecting about the leading diagonal, i.e.
	 * using columns as rows and vice versa. ((0,0) stays in place, (0,1) becomes (1,0) etc.)
	 * 
	 * @return The transposed matrix.
	 */
	public Matrix transpose(){
		double[] newArray = new double[cols*rows]; // create new array
		// Transpose the matrix
		for(int i = 0; i < rows; i++){	
			for(int j = 0; j < cols; j++){
				newArray[j*rows + i] = this.get(i, j);
			}
		}
		return new Matrix(newArray, rows);
		
	}
	

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
	 * @param col The col to be removed
	 * @return The newly formed matrix.
	 */
	public Matrix minor(int row, int col){
		int newCols = cols-1;
		double[] newArray = new double[(rows-1)*(newCols)]; // Construct new array
		// Copy correct values
		for (int i = 0; i < row; i++){ // for each row before row
			for (int j = 0; j < col; j++){ // For each col before col
				newArray[i*newCols + j] = get(i, j);
			}
			for (int j = col + 1; j < rows; j++){// For each col After col
				newArray[i*newCols + j-1] = get(i, j);
			}
		}
		for (int i = row + 1; i < cols; i++){ // for each row after row
			for (int j = 0; j < col; j++){ // For each col before col 
				newArray[(i-1)*newCols + j ] = this.get(i, j);
			}
			for (int j = col + 1; j < rows; j++){//For each col After col
				newArray[(i-1)*newCols + j - 1] = get(i, j);
			}
		}
		
		return new Matrix(newArray, newCols);
	}
	
	/**
	 * Transforms a matrix to row echelon form (also called triangular form) by Gaussian elimination.
	 * Optionally transforms it to reduced row echelon form.
	 *
	 * @param reduced true to perform Gauss-Jrodan elimination and put matrix to reduced form, false for Gaussian Elimination and only go to row echelon
	 * @return The matrix once it has been reduced to row-echelon form.
	 */
	protected Matrix toRowEchelonForm(boolean reduced) { 
		Matrix m = clone(); // Clone Matrix so algorithm may be performed in place.
		int i = 0, j = 0;
		while ( i < rows && j < cols){
			// look for target value in column
			int maxi = i;
			for( int k = i+1; k < rows; k++ )
				if( m.get(k, j) >  m.get(maxi, j) ) 
					maxi = k;
			if( m.get(maxi ,j) != 0) {
				// Swap pivot row into place
				m = m.swapRows(i, maxi); // m[i,j] now contains m[maxi, j]
				// Scale pivot row
				m = m.scaleRow(i, 1/(m.get(i, j))); //m[i,j] now = 1
				// fill all lower rows with 0
				for( int u = i+1; u < rows; u++)
					m = m.sumRows(u, i, -m.get(u, j)); // m[u,j] now = 0
				if (reduced) // If above columns need filling
					for( int u = i-1; u >= 0; u--)
					m = m.sumRows(u, i, -m.get(u, j)); // m[u,j] now = 0
				// next row
				i++;
			}
			// move to next column
			j++;
		}
		return m; //Return the transformed matrix.
	}
	
	/**
	 * Transform this matrix to row echelon form
	 * @return This matrix in Row echelon form
	 */
	public Matrix toRowEchelonForm(){
		return toRowEchelonForm(false);
	}
	
	/**
	 * Transform this matrix to reduced row echelon form
	 * @return This matrix in reduced row echelon form
	 */
	public Matrix toReducedRowEchelonForm(){
		return toRowEchelonForm(true);
	}
	
	
	// Utilities
	/**
	 * Tests for equality. Returns true if the matricies have the same number of elements, and the 
	 * element at each position are equal.
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof Matrix){
			return Arrays.equals(array, ((Matrix) o).array) && cols==((Matrix) o).cols;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return 0;
	}
	
}

//////////////////////////////////////////////////////////////////////////////////////
//										END OF FILE 								//
//////////////////////////////////////////////////////////////////////////////////////
