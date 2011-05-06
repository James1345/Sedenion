package com.alexandria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A first attempt at creating a 2D plot
 * @author james
 *
 */
public class Plot2D extends JPanel {

	private final static Color bgColor = Color.WHITE;
	private final static Color axisColor = Color.BLACK;
	private final static Color plotColor = Color.RED;
	
	//integer representations of position of points
	private int[] xVals, yVals;
	
	public Plot2D( BasicFunction fx, double xMin, double xMax ){ 
		
		//assume resolution is 300px x 300px
		final int px = 300;
		
		//assume scale is 10px = 1 unit
		double unitsPerPixel = 0.1;
		
		//calculate x step
		double xStep = (xMax-xMin)/px;
		
		//calculate y values
		double[] y = new double[px];
		for(int i = 0; i< px; i++){
			y[i] = fx.eval(xMin + i*xStep);
		}
		
		/*
		 * calculate plot points
		 * 
		 * Calculate double (actual) value
		 * divide by scale
		 * invert y axis
		 * shift by origin offset
		 * 
		 * cast to int.
		 */
		//currently assumes origin is at the centre of the screen
		xVals = new int[px];
		yVals = new int[px];
		for (int i = 0; i < px; i++){
			xVals[i] = (int)(((xMin + i*xStep)/unitsPerPixel) + (px/2)); 
			yVals[i] = (int)(-1*(y[i]/unitsPerPixel) + (px/2));
		}
		
		//set up gui
		this.setPreferredSize(new Dimension(px, px));
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(plotColor); //set colour
		for(int i = 0; i < 299; i++){
			g2.drawLine(xVals[i], yVals[i], xVals[i+1], yVals[i+1]); //connect the dots
		}
		
	}
	
	
	
}
