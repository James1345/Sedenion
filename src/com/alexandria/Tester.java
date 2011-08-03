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
				return Math.atan(x);
			}
		};
		
		Plot2D plot = new Plot2D(sinX, -5, 5);
		plot.setPreferredSize(new Dimension(300, 300));
		
		JFrame test = new JFrame();
		test.setContentPane(plot);
		test.pack();
		test.setTitle("Test plot");
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
