package com.alexandria.math;

//TODO more stats... boring though they are.
//TODO consider using R to provide statistics
public class Statistics {
	
	public static double mean(double[] data){
		double acc = 0.0;
		for (double datum : data)
			acc += datum;
		return acc/data.length;
	}
	
	public static double sd(double[] data){
		double acc = 0.0;
		int n = data.length;
		double x = mean(data);
		for (double datum : data)
			acc += Math.pow(datum-x, 2);
		acc /= n;
		return Math.sqrt(acc);
	}
	
	
}
