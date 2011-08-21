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
	protected double[][] array;
	
	/** The number of columns */
	public final int columns;
	
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
		return array[row][column];
	}
	
	public double[][] toArray(){
		return array;
	}
	
	/**
	 * Constructor.
	 * 
	 * Constructs a new Matrix from the given 2-dimensional array.
	 * 
	 * Content is copied by value so later changes to the array will not affect the Matrix
	 * 
	 * @param array The array to be converted into a Matrix.
	 */
	public Matrix(double[][] array) {
		this.isSquare = (this.columns = array[0].length) == (this.rows = array.length); /*set instance variables */
		this.array = new double[rows][columns];
		//copy values and check length is correct (Exception thrown if incorrect
		try{
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					this.array[i][j] = array[i][j];
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			throw new IllegalArgumentException();
		}
	}
	
	
	public Matrix(int dimensions){
		double[][] array = new double[dimensions][1]; //new array with dimensions rows and 1 column
		for(double[] row : array)
			row[0] = 0;//initialize values
		this.rows = dimensions;
		this.columns = 1;
		this.isSquare = (rows == 1);
		this.array = array;
	}
	
	/**
	 * Construct the Matrix representation of a given Complex number.
	 * 
	 * @param c The Complex to be converted to a Matrix.
	 */
	public Matrix(Complex c) {
		this.isSquare = true;
		this.rows = this.columns = 2;
		double[][] array = { { c.re(), -c.im() }, { c.im(), c.re() } };
		this.array = array;
	}
	
	/* Class Methods */
	
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
	
	/* Row operations */
	
	/**
	 * Swaps two rows of a matrix.
	 * @param rowA First Row to be swapped
	 * @param rowB Second Row to be swapped
	 * @return A matrix with rows A and B swapped.
	 */
	public Matrix h(int rowA, int rowB){
		Matrix h = this.clone(); /* Deep clone the matrix */
		double[][] newContent = h.array; /* copy arrays */
		double[] temp = newContent[rowA]; /* Temporarily store rowA */
		newContent[rowA] = newContent[rowB]; /* Swap values */
		newContent[rowB] = temp;
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
		Matrix m = this.clone(); /* deep clone matrix */
		double[][] newContent = m.array; /* extract array for editing */
		for (int i = 0; i < m.columns; i++){ /* for each value in the row */
			newContent[row][i]*=lambda; /* multiply each value */
		}
		return m; /* Return matrix with the row scaled */
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
		double[][] newContent = f.array; /* copy arrays */
		for (int i = 0; i < f.columns; i++){ /* for each value in rowA */
			newContent[rowA][i]+=newContent[rowB][i]*lambda; /* Add the scaled rowB */
		}
		return f; /* Return matrix with the columns added */
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
	
	/* Matrix operations */

	/**
	 * Adds two Matrices.
	 * 
	 * This method adds two matrices of the same dimensions by adding each pair
	 * of elements that are at a particular set of (i,j) coordinates.
	 * i.e. (0,0) in the new matrix is the sum of the (0,0) elements in the two
	 * matrices being added.
	 * 
	 * @param m The matrix to add to this.
	 * @throws MatrixSizeMissMatchException An exception if the two matrices are of different sizes.
	 */
	public Matrix add(Matrix m) throws MatrixSizeMissMatchException{
		if (this.columns != m.columns || this.rows != m.rows)
			throw new MatrixSizeMissMatchException();
		
		Matrix added = this.clone(); /* deep clone Matrix */
		double[][] newContent = added.array; /* Extract arrays for editin */
		for(int i = 0; i < added.rows; i++){ /* For each value */
			for (int j = 0; j < added.columns; j++){
				newContent[i][j] += m.get(i, j); /* Add the corresponding value from m */
			}
		}
		return added; /* Return matrix with the values added */
	}
	
	/**
	 * Subtracts one matrix from another
	 * 
	 * As {@link #add(Matrix)} except that the parameter is subtracted from this
	 * rather than added.
	 * 
	 * @param m The matrix to subtract from this.
	 * @throws MatrixSizeMissMatchException An exception if the two matrices are of different sizes.
	 */
	public Matrix subtract(Matrix m) throws MatrixSizeMissMatchException{
		if (this.columns != m.columns || this.rows != m.rows)
			throw new MatrixSizeMissMatchException();
		
		Matrix minussed = this.clone(); /* deep clone Matrix */
		double[][] newContent = minussed.array; /* Extract arrays for editing */
		for(int i = 0; i < minussed.rows; i++){ /* For each value */
			for (int j = 0; j < minussed.columns; j++){
				newContent[i][j] -= m.get(i, j); /* Minus the corresponding value from m */
			}
		}
		return minussed; /* Return matrix with the values subtracted */
	}
	
	/**
	 * Multiplies two matrices.
	 * 
	 * Matrix multiplication is not commutative (i.e. A*B != B*A). It is also possible that A*B is possible
	 * where B*A is not. Therefore, this method 'right-multiplies' matrix m with this 
	 * (i.e. this*m; <b>NOT</b> m*this). 
	 * <br />
	 * Matrix multiplication only works when the number of columns in this is equal to the number of
	 * rows in m. A new matrix is formed. This is populated by numbers calculated from the original
	 * two matrices in the following manner. The value at the new (i,j) is taken by multiplying
	 * corresponding values in row i of this and column j of m, and then taking the sum of these
	 * products. 
	 * 
	 * @param m The matrix to multiply this by.
	 * @return The new matrix.
	 * @throws MatrixSizeMissMatchException If the dimensions of the two matrices do not allow
	 * for multiplication.
	 */
	public Matrix rightMultiply(Matrix m) throws MatrixSizeMissMatchException{
		if (this.columns != m.rows)
			throw new MatrixSizeMissMatchException();
		
		double[][] newContent = new double[this.rows][m.columns];
		for (int i = 0; i<this.rows; i++){ /*for each row in this */
			for (int j = 0; j<m.columns; j++){ /* for each column in m */
				double accumulator = 0;
				for (int marker = 0; marker < this.columns; marker++){ /* for each pair of values */
					accumulator += this.get(i, marker)*m.get(marker, j); /* accumulate total */
				}
				newContent[i][j] = accumulator; /* put total in correct new position */
			}
		}
		return new Matrix(newContent);
	}
	
	/**
	 * Scales a matrix.
	 * 
	 * Multiplies every value in a matrix by a given value (f).
	 * 
	 * @param f The amount by which to scale the matrix.
	 * @return The new, scaled matrix.
	 */
	public Matrix scale(double f){
		Matrix scaled = this.clone(); /* deep clone Matrix */
		double[][] newContent = scaled.array; /* Extract arrays for editing */
		for (int i = 0; i<scaled.rows; i++){ /*for each row in this */
			for (int j = 0; j<scaled.columns; j++){ /* for each column in this */
				newContent[i][j]*=f;
			}
		}
		return new Matrix(newContent);
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
	 * @throws MatrixSizeMissMatchException If the matrix is not square.
	 */
	public double det() throws MatrixSizeMissMatchException{
		if (!this.isSquare)
			throw new MatrixSizeMissMatchException();
		
		/* For a 1x1 Matrix */
		if (this.columns == 1)
			return this.get(0, 0);
		
		/* For a 2x2 matrix */
		if (this.columns == 2)
			return (this.get(0, 0)*this.get(1, 1)) - (this.get(0,1)*this.get(1, 0));
		
		/* For an nxn Matrix (where n>2) */
		double acc = 0; /* Create accumulator */
		for(int j = 0; j < this.rows; j++){/* In each column */
			/* Recursively add or subtract ('chess board') the parts of the determinant */
			if (j%2 == 0)
				acc += this.get(0, j) * this.sub(0,j).det();
			else
				acc -= this.get(0, j) * this.sub(0,j).det();
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
		double[][] newContent = new double[this.columns][this.rows]; /* create new dimensions */
		/* Transpose the matrix */
		for(int i = 0; i < this.rows; i++){	
			for(int j = 0; j < this.columns; j++){
				newContent[j][i] = this.get(i, j);
			}
		}
		return new Matrix(newContent);
		
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
		for (double[] row : this.array){
			returnString += "( ";
			for (double num : row){
				returnString += String.format("%5.2e ", num);
			}
			returnString += ")\n";
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
		double[][] arrayA = this.array;
		double[][] arrayB = new double[arrayA.length][arrayA[0].length];
		for (int i = 0; i < arrayA.length; i++){
			System.arraycopy(arrayA[i], 0, arrayB[i], 0, arrayA[0].length);
		}
		return new Matrix(arrayB);
	}
	
	/**
	 * Constructs a new matrix, removing the row and column containing value (i, j).
	 * 
	 * @param row The row to be removed
	 * @param column The column to be removed
	 * @return The newly formed matrix.
	 */
	protected Matrix sub(int row, int column){
		double[][] newContent = new double[this.columns - 1][this.rows - 1]; /* Construct new array */
		/* Copy correct values */
		for (int i = 0; i < row; i++){ /* for each row before row */
			for (int j = 0; j < column; j++){ /* For each column before column */
				newContent[i][j] = this.get(i, j);
			}
			for (int j = column + 1; j < this.rows; j++){/* For each column After column */
				newContent[i][j-1] = this.get(i, j);
			}
		}
		for (int i = row + 1; i < this.columns; i++){ /* for each row after row */
			for (int j = 0; j < column; j++){ /* For each column before column */
				newContent[i-1][j] = this.get(i, j);
			}
			for (int j = column + 1; j < this.rows; j++){/* For each column After column */
				newContent[i-1][j-1] = this.get(i, j);
			}
		}
		
		return new Matrix(newContent);
	}
	
	/**
	 * An error thrown when an operation is attempted on two Matrices when the sizes of the two
	 * does not allow it.
	 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
	 *
	 */
	public class MatrixSizeMissMatchException extends Exception {
		
		private static final long serialVersionUID = 6799388523538898562L;

		public MatrixSizeMissMatchException(){
			System.err.print("Matrix sizes incompatible.\n");
		}
	}
}

//////////////////////////////////////////////////////////////////////////////////////
//										END OF FILE 								//
//////////////////////////////////////////////////////////////////////////////////////
