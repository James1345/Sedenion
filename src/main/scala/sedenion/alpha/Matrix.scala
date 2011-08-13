package sedenion.alpha;

class Matrix( val array: Array[Double], val cols: Int) {

	// Constructor checking
	if (0 != array.length % cols) {
		throw new IllegalArgumentException;
	}
	
	val rows = array.length/cols;

	def get(row: Int, col: Int): Double = array(row*cols + col);
	
	def +(that: Matrix): Matrix = {
	    if ( rows != that.rows || cols != that.rows) {
			throw new IllegalArgumentException;
		}
		
		val newArray = Array[Double](array.length);
		for ( i <- 0 until array.length) {
			newArray(i) = array(i) + that.array(i);
		}
		new Matrix(newArray, cols);
	}
	
	def -(that: Matrix): Matrix = this + (that * -1);
	
	def *(that: Any): Matrix = that match {
		case that2: Double => new Matrix(array.map(_ * that2), cols);
		case that2: Float => new Matrix(array.map(_ * that2), cols);
		case that2: Int => new Matrix(array.map(_ * that2), cols);
		case that2: Long => new Matrix(array.map(_ * that2), cols);
		case that2: Short => new Matrix(array.map(_ * that2), cols);
		case that2: Matrix => {
			
		}
	}
	
	class 
}