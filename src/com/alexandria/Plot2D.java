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

	private static final long serialVersionUID = -1381595546860856585L;

	private Color bgColor = Color.WHITE;
	private Color axisColor = Color.BLACK;
	private Color plotColor = Color.RED;
	
	//integer representations of position of points
	private int[] xVals, yVals;
	
	//Values of y points. Corresonds to yVals[]
	private double[] y;
	
	//default scale is 10px = 1 unit
	private double unitsPerPixel = 0.1;
	
	//default resolution is 300px x 300px
	private int px = 300;
	
	//default origin offset = 0, 0
	/** The amount the origin is shifted from the centre of the screen (measured in same units as Vals)*/
	private int[] OriginOffset = {0,0};
	
	public Plot2D( BasicFunction fx, double xMin, double xMax ){ 
		
		
		//calculate x step
		double xStep = (xMax-xMin)/px;
		
		//calculate y values
		y = new double[px];
		for(int i = 0; i< px; i++){
			y[i] = fx.eval(xMin + i*xStep);
		}
		
		/*
		 * calculate plot points
		 * 
		 * Calculate double (actual) value
		 * Offset by OriginOffset (from centre)
		 * 
		 * divide by scale
		 * invert y axis
		 * cast to int.
		 * 
		 * shift origin by half resolution (so if it were not offset it would be central)
		 * 
		 * 
		 */
		//currently assumes origin is at the centre of the screen
		xVals = new int[px];
		yVals = new int[px];
		for (int i = 0; i < px; i++){
			xVals[i] = (int)((xMin + i*xStep)/unitsPerPixel) + (px/2); 
			yVals[i] = (int)(-1*(y[i]/unitsPerPixel)) + (px/2);
			
		}
		
		//set up gui
		this.setPreferredSize(new Dimension(px, px));		
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//draw background
		g2.setColor(bgColor);
		g2.fillRect(0, 0, px, px);
	
		//draw axes
		g2.setColor(axisColor);
		g2.drawLine(px/2, 0, px/2, px);
		g2.drawLine(0, px/2, px, px/2);
		
		//draw plot
		g2.setColor(plotColor);
		for(int i = 0; i < 299; i++){
			if(canPlot(i)) //doesn't work :/
				g2.drawLine(xVals[i], yVals[i], xVals[i+1], yVals[i+1]); //connect the dots
		}
	}
	
	/**
	 * A function to test if a given index (i) maps to be a point that can be plotted. (i.e. if it's on the screen)
	 * Currently assumes origin is centred.
	 * @param index the index to test.
	 * @return True if plotable, otherwise false.
	 */
	private boolean canPlot(int index){
		double yLimit = (px/2) * unitsPerPixel;
		if(y[index] > yLimit
				|| y[index] < -yLimit
				|| y[index+1] > yLimit
				|| y[index+1] < -yLimit)
			return false;
		else
			return true;
		
	}
	
	
	
}
