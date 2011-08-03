package com.alexandria;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

/**
 * A Functional Plot2D. No abstract plot yet implemented but this works.
 * @author james
 *
 */
public class Plot2D extends JPanel implements ComponentListener{
	
	// Instance Variables.
	private static final long serialVersionUID = -7220817713786168906L;

	/** The function of x to plot. */
	protected Function function = null;
	
	/** The min and max x Values */
	protected double xMin = 0, xMax = 0;

	/** 
	 * Whether or not to invert the Y axis.
	 * Default to true (as screen x,y are the inverse of traditional x,y)
	 * TODO implement this, currently unused.
	 */
	protected boolean invertY = true;
	
	/**
	 * Create a new Plot2D panel.
	 * @param function
	 * @param xMin
	 * @param xMax
	 */
	public Plot2D(Function function, double xMin, double xMax){
		
		this.function = function;
		this.xMin = xMin;
		this.xMax = xMax;
		
		repaint(); // Paint the graph
		
		
		if(null == function){
			//TODO handle error.
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		double xStep = (xMax-xMin)/width;
		
		java.util.Vector<Double> y = new java.util.Vector<Double>();
		
		// Calculate y values.
		for(double i = xMin; i < xMax; i+=xStep){
			y.add(new Double(function.eval(i)));
		}
		
		double yMax = -Double.MAX_VALUE;
		double yMin = Double.MAX_VALUE;
		for(Double value : y){
			if(value > yMax) yMax = value;
			if(value < yMin) yMin = value;
		}
		
		double yStep = (yMax-yMin)/height;
		
		// Calculate the offsets for the graph
		int xOffset = (int) (0+(width/2)-(xMax+xMin)/(2*xStep));
		
		int yOffset = (int) (0+(height/2)+(yMax+yMin)/(2*yStep));
		
		// Set up graphics object.
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw Axes
		g2.setColor(Color.BLACK);
		g2.drawLine(0, yOffset, width, yOffset);
		g2.drawLine(xOffset, 0, xOffset, height);
		
		// Draw graph
		g2.setColor(Color.BLUE);
		for(int i = 0; i < y.size() - 1; i++){
			
			// Calculate y position for each x.
			/*
			 * Each x value has been mapped onto a pixel, map this onto y by
			 * - inverting (depending on the value of this.invertY) 
			 * - dividing by the ystep (how many pixels) 
			 * - shifting by the offset. 
			 */
			
			int yStart =  ((int) ((invertY ? -1 : 1) * y.get(i)/yStep + yOffset));
			int yEnd = ((int) ((invertY ? -1 : 1)  * y.get(i+1)/yStep + yOffset));
			
			g2.drawLine(i, yStart, i+1, yEnd);
		}
	}



	@Override
	public void componentResized(ComponentEvent e) {
		this.repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
	
}
