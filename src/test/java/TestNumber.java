
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Number a = new Number(7);
		Number b = new Number(4);
		Number c = new Number(5);
		Number d = new Number(12);
		
		Number q = new Number(new Number(a, b), new Number(c, d));
		q = q.multiply(q.conjugate());
		
	}

}
