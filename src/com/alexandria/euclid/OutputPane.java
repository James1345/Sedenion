package com.alexandria.euclid;

import java.awt.Dimension;

import javax.swing.JEditorPane;

public class OutputPane extends JEditorPane implements Writable{

	private static final long serialVersionUID = 907552225484349668L;

	public OutputPane(){
		this.setEditable(false);
		this.setPreferredSize(new Dimension(300,300));
	}
	
	
	
	@Override
	public int write(String s) {
		return s.getBytes().length;
	}

}
