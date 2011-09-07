import sedenion.*;
import javax.swing.*;
import java.awt.*;

public class Test{

	public static void p(Object... args){
		for(Object arg : args){
			System.out.print(arg + " ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		p(Complex.sin((new Complex(3,4))));
	}

}
