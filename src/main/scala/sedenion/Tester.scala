import sedenion._;

object Tester{
  def main(args: Array[String]) {
    
	var c = new Complex(3.0, 4.0);
	var e = new Complex(5.0, 7.0);
	
	println("c = " + c.toString);
	println("e = " + e.toString);
	println("3**e = " + ((new Complex(3, 0))**e).toString);
	println("c**e = " + (c**e).toString);
    
    }


}