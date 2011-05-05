import com.alexandria.math.BasicFunction;

/**
 * This class represents a collection of functions that are added together to return a result.
 * (e.g. a polynomial). As it takes {@link BasicFunction} parameters, these may be child classes of
 * {@link BasicFunction}.
 * <br />
 * Used in conjunction with {@link BasicFunction} and {@link NestedFunction} it is possible to create a 
 * wide variety of functions. (note you have to create a function to make something negative by multiplying it by -1)
 * @author james
 *
 */
public class CompoundFunction extends BasicFunction {

	protected BasicFunction[] funcs;
	
	public CompoundFunction(BasicFunction[] f){
		super(BasicFunction.Type.CONST, 1); //for the compiler, won't be used in eval anyway.
		this.funcs = f;
	}
	
	@Override
	public double eval(double x){
		double accumulator = 0.0;
		for(BasicFunction f : funcs){
			accumulator += f.eval(x);
		}
		return accumulator;
	}

}
