package com.alexandria.euclid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RubyParser implements Parser {

	private ScriptEngineManager mgr = new ScriptEngineManager();
    private ScriptEngine engine = mgr.getEngineByExtension("rb");
	
	public RubyParser(){
		//load bindings
		try {
			System.out.println("Source version");
			BufferedReader bindings = new BufferedReader(new FileReader("src/com/alexandria/euclid/bindings.rb"));
			String line = null;
			while((line = bindings.readLine())!=null){
				engine.eval(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public String parse(String input) {
		// TODO Auto-generated method stub
		return null;
	}

}
