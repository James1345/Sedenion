import org.sedenion.complex.*;
import org.sedenion.complex.Number;
import org.sedenion.types.Exact;

public class Test{

	public static void p(Object... args){
		for(Object arg : args){
			System.out.print(arg.toString() + " ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		Number n = new Number(new Exact(5,50));
		System.out.println(n.multiply(new Number(new Exact(4))));
		
	}

}
