package com.alexandria;

/**
 * This class represents the lowest level functions that can be applied to a variable x.
 * 
 * The functions will be applied to a given number when eval is called. Some functions require a
 * second variable (e.g. degree requires the power to which x should be raised). This number is set
 * when the function is constructed and cannot be changed after that.
 * <br /><br />
 * e.g.<br />
 * BasicFunction square = new BasicFunction(BasicFunction.Type.DEGREE, 2);<br />
 * double threeSquared = square.eval(3);<br />
 * System.out.println(threeSquared); <br />
 * <br /><br />
 * will print the number 9 to the console (3^2 = 9).
 * <br />
 * Function List:
 * <ul>
 * <li> sin(x)</li>
 * <li> cos(x)</li>
 * <li> tan(x)</li>
 * <li> arcsin(x)</li>
 * <li> arcos(x)</li>
 * <li> arctan(x)</li>
 * <li> sinh(x)</li>
 * <li> cosh(x)</li>
 * <li> tanh(x)</li>
 * <li> arsinh(x)</li>
 * <li> arcosh(x)</li>
 * <li> artanh(x)</li>
 * <li> abs(x) (i.e. |x|)
 * <li> constant (i.e. x + c)</li>
 * <li> degree (i.e x^d)</li>
 * <li> multiply (i.e. m*x)</li>
 * <li> exponential (i.e e^x, where e is an arbitrary number)</li>
 * <li> log(x)/ln(x) (i.e. natural logarithm of x)</li>
 * @author james
 *
 */
public class BasicFunction{
	
	public static enum Type{
		SIN, COS, TAN, ARCSIN, ARCOS, ARCTAN, SINH, COSH, TANH, ARSINH, ARCOSH, ARTANH, ABS, CONST, DEGREE, MULTIPLY, EXP, LN
	}
	
	protected Type type = null;
	protected double var = 0.0;
	
	/**
	 * Creates a new Basic Function using the given type. 
	 * 
	 * If the type expects a variable as well, assumes this is 1.0, and prints a warning to the error stream.
	 * However, it is not recommended to rely on this behaviour (treat it as an error).
	 * @param t the type of function to create
	 */
	public BasicFunction(Type t){
		switch(t){
		case CONST:
		case EXP:
		case DEGREE:
		case MULTIPLY:
			this.var = 1.0;
			System.err.print("Warning, no variable specified. Assuming 1.0");
			break;
		}
		this.type = t;
	}
	
	/**
	 * Assigns the type and variable of this funcion. Note that for many functions the variable  will be ignored.
	 * For these functions it is better to use {@link #BasicFunction(Type)}.
	 * @param t the function to apply
	 * @param v The variable, if required.
	 */
	public BasicFunction(Type t, double v){
		this.type = t;
		this.var = v;
	}
	
	/**
	 * Evaluates the function for a given x
	 * @param x the number to insert into the function
	 * @return The value of the expression
	 */
	public double eval(double x){
		switch(this.type){
		case SIN: return Math.sin(x);
		case COS: return Math.cos(x);
		case TAN: return Math.tan(x);
		case ARCSIN: return Math.asin(x);
		case ARCOS: return Math.acos(x);
		case ARCTAN: return Math.atan(x);
		case SINH: return Math.sinh(x);
		case COSH: return Math.cosh(x);
		case TANH: return Math.tanh(x);
		case ARSINH: return Math.log(x + Math.sqrt(x*x + 1));
		case ARCOSH: return Math.log(x + Math.sqrt(x+1)*Math.sqrt(x-1));
		case ARTANH: return Math.log((1+x)/(1-x))/2;
		case ABS: return Math.abs(x);
		case CONST: return x+var;
		case DEGREE: return Math.pow(x, var);
		case MULTIPLY: return x*var;
		case EXP: return Math.pow(var, x);
		case LN: return Math.log(x);
		default: return x; //should be unreachable
		}
	}
}
