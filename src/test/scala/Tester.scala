import sedenion._;

object Tester{
  def main(args: Array[String]) {
    
	var comp1 = new Complex( 4, 3 );
	var mat1 = comp1.toMatrix;
	var mat2 = new Matrix( Array( 2, 4, 6, 5), 2).add(mat1);
	
	println(mat2)
    }


}