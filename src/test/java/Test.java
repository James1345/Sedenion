import sedenion.*;

public class Test{

	public static void p(Object... args){
		for(Object arg : args){
			System.out.print(arg.toString() + " ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		Matrix m1 = new Matrix(new double[] {1,2,3,4}, 2);
		Matrix m2 = new Matrix(new double[] {1,2,3,4}, 2);
		p(m1.equals(m2));
		
	}

}
