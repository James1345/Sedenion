package xAbandoned.math;

import java.math.BigDecimal;

import com.alexandria.IllegalMatrixDimensionException;
import com.alexandria.MatrixSizeMissMatchException;

/**
 * A class to handle Matrices. Abandoned
 * 
 * This class deals with Matrices. A Matrix is a 2D array of numbers, with
 * special rules for performing mathematical operations on them. 
 * <br />
 * The matrix is stored as an array of rows. Therefore, coordinates select the <b>row</b> first,
 * then the column.
 * <br />
 * All instance and class methods return a <b>new</b> Matrix, without modifying any parameters
 * or the Matrix on which the method is called. Assigning these returns to a variable (even the 
 * original) must be done explicitly (weak imutability, needs to be extended so cannot declare final,
 * refers to array so care taken to avoid editing of array beyond constructor).
 * 
 * Currently a WIP. to replace original Matrix class eventually.
 * 
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class BDMatrix {
	
	/* Instance Variables. */
	
	/** The actual values held in the matrix.*/
	protected final BigDecimal[][] content;
	/** The number of columns */
	protected final int columns;
	/** the number of rows */
	protected final int rows;
	/** columns == rows */
	protected final boolean isSquare;
	
	/* Getters */
	
	/**
	 * Gets a value at the specified row/column as a double.
	 * @param row The row
	 * @param column The column
	 * @return The value at (row, column), a double
	 */
	public double doubleAt(int row, int column){
		return content[row][column].doubleValue();
	}
	
	/**
	 * Gets the BigDecimal at the given row/column.
	 * 
	 * Not a problem ad BigDecimals are imutable.
	 * 
	 * @param row The row
	 * @param column The column
	 * @return The BigDecimal at the given Row/Column
	 */
	public BigDecimal getAt(int row, int column){
		return content[row][column];
	}
	
	/**
	 * Returns the number of columns.
	 * @return the number of columns
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * Returns the number of rows.
	 * @return the number of rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Whether the matrix is square or not.
	 * 
	 * Returns true if columns == rows.
	 * Returns false otherwise.
	 * 
	 * @return columns == rows
	 */
	public boolean isSquare() {
		return isSquare;
	}
	
	
	/* Constructors */
	/**
	 * String Constructor.
	 * 
	 * Preferred Constructor. Creates a Matrix from an array of Strings (perfect accuracy is maintained).
	 *  
	 * @param content An array of String representations of the numbers to be used
	 * @throws IllegalMatrixDimensionException If length of rows in content is not constant.
	 * 
	 */
	public BDMatrix(String[][] content) throws IllegalMatrixDimensionException {
		//work out dimensions
		final int rows = content.length;
		final int columns = content[0].length;
		//Assign size
		this.content = new BigDecimal[rows][columns];
		this.isSquare = ((this.rows = rows) == (this.columns = columns));
		//Assign Values
		int i = 0;
		int j = 0;
		for(String[] row : content){
			if(row.length != columns){
				throw new IllegalMatrixDimensionException(); //For user io Class to handel
			}
			for(String value : row){
				this.content[i][j] = new BigDecimal(value);
				j++;
			}
			i++;
		}
	}
	
	/**
	 * Double Constructor.
	 * 
	 * Creates a Matrix from an array of doubles. Potential for inaccuracy (if the doubles
	 * are already inaccurate, e.g. {@link Math#sin(double)})
	 *  
	 * @param content An array of double representations of the numbers to be used
	 * @throws IllegalMatrixDimensionException If length of rows in content is not constant.
	 * 
	 */
	public BDMatrix(double[][] content) throws IllegalMatrixDimensionException {
		//work out dimensions
		final int rows = content.length;
		final int columns = content[0].length;
		//Assign size
		this.content = new BigDecimal[rows][columns];
		this.isSquare = ((this.rows = rows) == (this.columns = columns));
		//Assign Values
		int i = 0;
		int j = 0;
		for(double[] row : content){
			if(row.length != columns){
				throw new IllegalMatrixDimensionException(); //For user io Class to handel
			}
			for(double value : row){
				this.content[i][j] = BigDecimal.valueOf(value);
				j++;
			}
			i++;
		}
	}
	
	/* Utility Methods */
	
	/**
	 * Returns a String representation of the Matrix.
	 * 
	 * @return this matrix as a string
	 */
	@Override
	public String toString(){
		String str = "";
		for(BigDecimal[] row : content){
			
			str += "( ";
			
			for(BigDecimal value : row){
				str += value.toString() + " ";
			}
			
			str += ")\n";
		}
		return str;
	}
	
	/* Methods */
	
	/**
	 * Adds two Matrices.
	 * 
	 * Creates a new Matrix by adding corresponding elements in 2 Matrices of the same size.
	 * 
	 * @param m The matrix to add to this.
	 * @return The sum of the matrices.
	 * @throws MatrixSizeMissMatchException If the matrices have different dimensions.
	 * 
	 */
	public BDMatrix add(BDMatrix m) throws MatrixSizeMissMatchException{
		
		int rows = this.getRows();
		int cols = this.getColumns();
		
		//check sizes
		if (!(rows==m.getRows() && cols == m.getColumns()))
				throw new MatrixSizeMissMatchException();
		
		String[][] content = new String[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				content[i][j] = this.getAt(i,j).add(m.getAt(i,j)).toString();
			}
		}
		
		try {
			return new BDMatrix(content);
		} catch (IllegalMatrixDimensionException e) {
			/* not possible, both matrices must have been created properly, so this will never happen.
			try, catch and return here to please compiler */
			return null;
		}
	}
	
	public BDMatrix subtract(BDMatrix m) throws MatrixSizeMissMatchException{
		
		int rows = this.getRows();
		int cols = this.getColumns();
		
		//check sizes
		if (!(rows==m.getRows() && cols == m.getColumns()))
				throw new MatrixSizeMissMatchException();
		
		String[][] content = new String[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				content[i][j] = this.getAt(i,j).subtract(m.getAt(i,j)).toString();
			}
		}
		
		try {
			return new BDMatrix(content);
		} catch (IllegalMatrixDimensionException e) {
			/* not possible, both matrices must have been created properly, so this will never happen.
			try, catch and return here to please compiler */
			return null;
		}
	}
	
	/**
	 * Multiply this matrix by another matrix.
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
	public BDMatrix multiply(BDMatrix m) throws MatrixSizeMissMatchException{
		if (this.getColumns() != m.getRows())
			throw new MatrixSizeMissMatchException();
		String[][] content = new String[this.getRows()][m.getColumns()];
		
		for (int i = 0; i<this.getRows(); i++){ /*for each row in this */
			for (int j = 0; j<m.getColumns(); j++){ /* for each column in m */
				BigDecimal accumulator = new BigDecimal("0");
				for (int marker = 0; marker < this.getColumns(); marker++){ /* for each pair of values */
					accumulator = accumulator.add(this.getAt(i, marker).multiply(m.getAt(marker, j))); /* accumulate total */
				}
				content[i][j] = accumulator.toString(); /* put total in correct new position */
			}
		}
		
		try{
			return new BDMatrix(content);
		}catch (IllegalMatrixDimensionException e) {
			/* not possible, both matrices must have been created properly, so this will never happen.
			try, catch and return here to please compiler */
			return null;
		}
	}
	
	/**
	 * Multiplies each value in the matrix by a given scale factor (lambda).
	 * 
	 * @param lambda The BigDecimal factor to scale the matrix by (may be a fraction).
	 * @return The new, scaled matrix.
	 */
	public BDMatrix multiply(BigDecimal lambda){
		
		int rows = this.getRows();
		int cols = this.getColumns();
		
		String[][] content = new String[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				content[i][j] = this.content[i][j].multiply(lambda).toString();
			}
		}
		
		try{
			return new BDMatrix(content);
		}catch (IllegalMatrixDimensionException e) {
			/* not possible, the original matrix must have been created properly, so this will never happen.
			try, catch and return here to please compiler */
			return null;
		}
	}
	
	/**
	 * Multiplies each value in the matrix by a given scale factor (lambda).
	 * 
	 * {@link #multiply(BigDecimal)} is preferred for accuracy.
	 * 
	 * @param lambda The (double) factor to scale the matrix by (may be a fraction).
	 * @return The new, scaled matrix.
	 */
	public BDMatrix multiply(double lambda){
		
		int rows = this.getRows();
		int cols = this.getColumns();
		
		String[][] content = new String[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				content[i][j] = this.content[i][j].multiply(BigDecimal.valueOf(lambda)).toString();
			}
		}
		
		try{
			return new BDMatrix(content);
		}catch (IllegalMatrixDimensionException e) {
			/* not possible, the original matrix must have been created properly, so this will never happen.
			try, catch and return here to please compiler */
			return null;
		}
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
	public BigDecimal det() throws MatrixSizeMissMatchException{
		if (!this.isSquare())
			throw new MatrixSizeMissMatchException();
		
		/* For a 1x1 Matrix */
		if (this.getColumns() == 1)
			return this.getAt(0, 0);
		
		/* For a 2x2 matrix */
		if (this.getColumns() == 2)
			return (this.getAt(0, 0).multiply(this.getAt(1, 1))).subtract((this.getAt(0,1).multiply(this.getAt(1, 0))));
		
		/* For an nxn Matrix (where n>2) */
		BigDecimal acc = new BigDecimal("0"); /* Create accumulator */
		for(int j = 0; j < this.getRows(); j++){/* In each column */
			/* Recursively add or subtract ('chess board') the parts of the determinant */
			if (j%2 == 0)
				acc = acc.add( this.getAt(0, j).multiply(this.sub(0,j).det()));
			else
				acc = acc.subtract(this.getAt(0, j).multiply(this.sub(0,j).det()));
		}
		return acc;
	}
	
	
	/**
	 * Constructs a new matrix, removing the row and column containing value (i, j).
	 * 
	 * @param row The row to be removed
	 * @param column The column to be removed
	 * @return The newly formed matrix.
	 */
	protected BDMatrix sub(int row, int column){
		String[][] newContent = new String[this.getColumns() - 1][this.getRows() - 1]; /* Construct new content */
		/* Copy correct values */
		for (int i = 0; i < row; i++){ /* for each row before row */
			for (int j = 0; j < column; j++){ /* For each column before column */
				newContent[i][j] = this.getAt(i, j).toString();
			}
			for (int j = column + 1; j < this.getRows(); j++){/* For each column After column */
				newContent[i][j-1] = this.getAt(i, j).toString();
			}
		}
		for (int i = row + 1; i < this.getColumns(); i++){ /* for each row after row */
			for (int j = 0; j < column; j++){ /* For each column before column */
				newContent[i-1][j] = this.getAt(i, j).toString();
			}
			for (int j = column + 1; j < this.getRows(); j++){/* For each column After column */
				newContent[i-1][j-1] = this.getAt(i, j).toString();
			}
		}
		
		try {
			return new BDMatrix(newContent);
		} catch (IllegalMatrixDimensionException e) {
			/* not possible, the original matrix must have been created properly, so this will never happen.
			try, catch and return here to please compiler */
			return null;
		}
	}
	
	
	
}
//////////////////////////////////////////////////////////////////////////////////////
//										END OF FILE 								//
//////////////////////////////////////////////////////////////////////////////////////
