package com.alexandria.elements;
import java.util.Vector;
/**
* A class to track user defined Variables.
*/
public class Register {
	
	private Vector<String> key = new Vector<String>();
	private Vector<Object> value = new Vector<Object>();

	public void let(String var, Object value){
		key.add(var);
		this.value.add(value);
	}
	
	public Object get(String var){
		return value.elementAt(key.indexOf(var));
	}

	public void set(String var, Object value){
		this.value.set(key.indexOf(var), value);
	}


}
