import sedenion.Number;


public class TestNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Number a = new Number(7);
		Number b = new Number(3);
		Number c = new Number(a, b);
		Number d = new Number(5);
		Number e = new Number(4);
		Number f = new Number(d,e);
		Number g = new Number(c,f);
		System.out.println(g.toString());
		System.out.print("[");
		for(int i = 0; i < g.toArray().length - 1; i++){
			System.out.print(""+g.toArray()[i] + ", ");
		}
		System.out.println(""+g.toArray()[g.toArray().length-1]+"]");
		
	}

}
