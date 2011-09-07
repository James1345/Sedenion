import sedenion.*;
import javax.swing.*;
import java.awt.*;

public class Test{

	public static void p(Object... args){
		for(Object arg : args){
			System.out.print(arg);
		}
	}

	public static void main(String[] args){
		
		CayleyDickson<Complex> quat = new CayleyDickson<Complex>(new Complex(1,3), new Complex(1,4));
		p(quat.conj());
		
	}

}
