package xAbandoned;

/**
 * This class represents a function of a function.
 * 
 * This class behaves in most ways as a {@link BasicFunction} except that it takes an extra
 * argument in its constructor, that represents the function to be nested. This function may be
 * a {@link BasicFunction} or any of its child classes (allowing functions of functions of functions of functions...)
 * <br />
 * Used in conjunction with {@link BasicFunction} and {@link CompoundFunction} it is possible to create a 
 * wide variety of functions.
 *  N.B. BE CAREFUL NOT T CREATE A FUNCTION LOOP.
 * @author james
 *
 */
public class NestedFunction extends BasicFunction {

	protected BasicFunction func;
	
	public NestedFunction(Type t, BasicFunction f) {
		super(t);
		this.func = f;
	}

	public NestedFunction(Type t, double v, BasicFunction f) {
		super(t, v);
		this.func = f;
	}
	
	@Override
	public double eval(double x){
		return super.eval(func.eval(x));//apply interior function first then this.
	}

}
