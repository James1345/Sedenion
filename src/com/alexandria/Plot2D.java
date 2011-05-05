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

	private double xMax = 0, xMin = 0, yMax = 0, yMin = 0;
	private int xRes = 0, yRes = 0;
	private Color bgColor = Color.WHITE;
	private Color axisColor = Color.BLACK;
	private Color plotColor = Color.RED;
	private BasicFunction fx = null;
	private double[] y = null;
	
	public Plot2D( BasicFunction fx, double xMin, double xMax){
		
		//time for magic numbers
		int res = 700;
		double xStep = (xMax - xMin)/res;
		y = new double[700];
		for(int i = 0; i< res; i++){
			y[i] = fx.eval(xMin + i*xStep);
		}
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(plotColor);
		for(int i = 0; i < 699; i++){
			g2.drawLine(i, (int)y[i], i+1, (int)y[i+1]);
		}
		
	}
	
	
	
}
