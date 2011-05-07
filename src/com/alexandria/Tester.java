package com.alexandria;

import javax.swing.JFrame;

/**
 * Debug and testing class, remove from final release.
 * @author james
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(Math.tan(Math.PI/2) == Double.POSITIVE_INFINITY);
		
		
		BasicFunction f1 = new BasicFunction(BasicFunction.Type.TAN); //ln(x)
		BasicFunction f2 = new BasicFunction(BasicFunction.Type.MULTIPLY, 0); //(x)
		BasicFunction y = new CompoundFunction(f1, f2, CompoundFunction.Type.SUM); 
		Plot2D plot = new Plot2D(y, -15, 15);
		
		JFrame test = new JFrame();
		test.setContentPane(plot);
		test.pack();
		test.setTitle("Test plot");
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
