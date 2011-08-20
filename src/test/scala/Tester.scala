import sedenion._;

object Tester{
  def main(args: Array[String]) {
    
	var comp1 = new Complex( 2.0, 5.0 );
	var mat1 = comp1.toMatrix;
    
	println(mat1.toString);
    }


}