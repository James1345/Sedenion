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
		
		Function sinX = new Function(){
			@Override
			public double eval(double x) {
				return x*x*x + 2*x*x + 4*x + 3;
			}
		};
		
		Plot2D plot = new Plot2D(sinX, -10, 10);
		plot.setPreferredSize(new Dimension(300, 300));
		
		JFrame test = new JFrame();
		test.setContentPane(plot);
		test.pack();
		test.setTitle("Test plot");
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
