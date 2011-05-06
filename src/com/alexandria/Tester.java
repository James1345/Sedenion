package com.alexandria;

import java.awt.Dimension;

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
		
		
		BasicFunction y = new BasicFunction(BasicFunction.Type.SIN);
		Plot2D plot = new Plot2D(y, -15, 15);
		
		JFrame test = new JFrame();
		test.setContentPane(plot);
		test.pack();
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
