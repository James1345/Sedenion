package sedenion.alpha;

class Matrix( val array: Array[Double], val cols: Int){

	// Constructor checking
	if (0 != array.length % cols) {
		throw new IllegalArgumentException;
	}
	
	val rows = array.length/cols;

	def get(row: Int, col: Int): Double = array(row*cols + col);
	
	def +(that: Matrix): Matrix ={
	    if ( rows != that.rows || cols != that.cols) {
			throw new IllegalArgumentException;
		}
		
		val newArray = Array[Double](array.length);
		for ( i <- 0 until (array.length - 1)){
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
		case that2: Matrix =>{
			val cols2 = that2.cols; // Quicker to type.
			if(rows != cols2) throw new IllegalArgumentException("Matrix sizes incompatible.");
			var newArray = new Array[Double](rows * cols2);
			for ( i <- 0 until (rows-1) ) {
				for ( j <- 0 until (cols2 - 1) ) {
					newArray(i*cols2 + j) ={
						var element = 0.0;
						for ( k <- 0 until (cols-1) ) {
							element += get(i, k) * that2.get(k, j);
						}
						element;
					}
				}
			}
			new Matrix(newArray, cols2);
		}
	} 
	
	override def toString: String = {
		
		import java.lang.String.format;
		
		var thisString = "{ ";
		
		for ( i <- 0 until (cols - 1)) {
			for ( j <- 0 until (rows - 1)) {
				thisString += format("%4.2f,", get(i,j).asInstanceOf[AnyRef]); 
			}
			var length = thisString.length;
			thisString = thisString.substring(0, length - 2); // Chop last character (trailing ,)
			thisString += " }\n";
		}
		thisString;
	}
}