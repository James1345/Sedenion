import sedenion.*;

public class Test{

	public static void p(Object... args){
		for(Object arg : args){
			System.out.print(arg + " ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		p(Integer.numberOfLeadingZeros(5));
		p(Integer.rotateLeft(5, Integer.numberOfLeadingZeros(5)));
	}

}
