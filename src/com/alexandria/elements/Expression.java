package com.alexandria.elements;

public class Expression {
	
	
	private static String OPS = "\\%|\\/|\\*|\\+|\\-";
	private static String COMPS = "<=|<|>=|>|!=|==";
	private static String FUNC = "\\w+\\(.+\\)";
	private static String VAR = "\\A\\w+\\z";
	private static String LIT = "\\d+\\.\\d+|\\d+";

	private Register r = null;
	
	public Expression(Register r){
		this.r = r;
	}
	
	public String parse(String expr){
	
		if(expr.matches(LIT)){
			System.out.print("NUM!");
			return expr;
		}
		return expr;
		

	}
}
