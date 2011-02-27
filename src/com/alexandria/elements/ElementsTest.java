package com.alexandria.elements;

public class ElementsTest {
	public static void main(String[] args){
		Elements e = new Elements(new Register());
		System.out.print(e.parse("2-3-4"));

	}
}
