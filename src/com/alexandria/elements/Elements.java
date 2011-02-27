package com.alexandria.elements;

public class Elements {
	public static void main(String[] args){
		Expression e = new Expression(new Register());
		e.parse("33");
	}
}
