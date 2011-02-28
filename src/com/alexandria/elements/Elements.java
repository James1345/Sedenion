package com.alexandria.elements;

public class Elements {
	
	
	private static String OPS = ".+(\\%|\\/|\\*|\\+|\\-).+";
	private static String COMPS = "<=|<|>=|>|!=|==";
	private static String FUNC = "\\w+\\(.+\\)";
	private static String VAR = "\\A\\w+\\z";
	private static String LIT = "\\A\\d+\\.\\d+\\z|\\A\\d+\\z";

	private Register r = null; //the register that contains user defined variables.
	
	public Elements(Register r){
		this.r = r;
	}
	
	/**
	 * Decides Which parse function to use and passes expr to that.
	 * 
	 * The function passed to will then split the expr around the correct point
	 * and call this on each half; recursing down until a number is obtained,
	 * and then popping back up to perform the operations.
	 * 
	 * @param expr The expression to parse.
	 * @return The result of parsing expr.
	 */
	public String parse(String expr){
		/*
		 * Start with lowest priority and work up
		 * (as when it recurses this will mean that it actually does give the right answer
		 */
		expr = expr.trim();
		
		
		//Literal number (all numbers to be treated as doubles)
		if(expr.matches(LIT)){
			System.out.println("NUMBER");
			return expr;
		}
		else if (expr.matches(OPS)){
			System.out.println("OP");
			return parseOp(expr);
		}
		
		
		
		
		return expr;
	}
	
	private String parseOp(String expr){
		
		
		//+
		String[] split = null;
		
		split = expr.split("\\+", 2);
		if (split.length > 1) {
			System.out.println("addition");
			return String.valueOf(Double.parseDouble(parse(split[0]))+Double.parseDouble(parse(split[1])));
		}
		//-
		split = expr.split("\\-", 2);
		if (split.length > 1) {
			System.out.println("minus");
			return String.valueOf(Double.parseDouble(parse(split[0]))-Double.parseDouble(parse(split[1])));
		}
		//*
		split = expr.split("\\*", 2);
		if (split.length > 1) {
			System.out.println("times");
			return String.valueOf(Double.parseDouble(parse(split[0]))*Double.parseDouble(parse(split[1])));
		}
		// /
		split = expr.split("\\/", 2);
		if (split.length > 1) {
			System.out.println("div");
			return String.valueOf(Double.parseDouble(parse(split[0]))/Double.parseDouble(parse(split[1])));
		}
		//*
		split = expr.split("\\%", 2);
		if (split.length > 1) {
			System.out.println("%");
			return String.valueOf(Double.parseDouble(parse(split[0]))%Double.parseDouble(parse(split[1])));
		}
		return expr;
	}
	
}
