package com.alexandria.euclid;

import java.awt.Dimension;
import java.awt.Toolkit;


public class Euclid extends REPL {
	
	public Euclid(Parser p) {
		super(p);
		this.setTitle("Euclid");
		
		// Get the size of the default screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(0.2*dim.width), (int)(0.1*dim.height));
	}
	
	public static void main(String[] args){
		Euclid main = new Euclid(new JSParser());
	}

}
