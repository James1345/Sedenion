import sedenion.alpha.Matrix;

object Tester{
  def main(args: Array[String]) {
    
	var arr = new Array[Double](4);
	arr(0) = 1;
	arr(1) = 2;
	arr(2) = 3;
	arr(3) = 4;
	
	var arr2 = new Array[Double](4);
	arr2(0) = 3;
	arr2(1) = 1;
	arr2(2) = 4;
	arr2(3) = 1;
	
	var mat1 = new Matrix(arr, 2);
	var mat2 = new Matrix(arr2, 2);
    
	println((mat1).toString);
    }


}