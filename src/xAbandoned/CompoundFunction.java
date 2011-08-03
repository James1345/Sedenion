package xAbandoned;

/**
 * This class represents a pair of functions that are added or multiplied together to return a result.
 * (e.g. a polynomial). As it takes {@link BasicFunction} parameters, these may be child classes of
 * {@link BasicFunction}.
 * <br />
 * Used in conjunction with {@link BasicFunction} and {@link NestedFunction} it is possible to create a 
 * wide variety of functions. (note you have to create a function to make something negative by multiplying it by -1)
 * @author james
 *
 */
public class CompoundFunction extends BasicFunction {

	protected BasicFunction funcOne;
	protected BasicFunction funcTwo;
	protected Type type;
	
	public static enum Type{
		SUM, PRODUCT
	}
	
	public CompoundFunction(BasicFunction f1, BasicFunction f2, Type t){
		super(BasicFunction.Type.CONST, 1); //for the compiler, won't be used in eval anyway.
		this.funcOne = f1;
		this.funcTwo = f2;
		this.type = t;
	}
	
	@Override
	public double eval(double x){
		double ret = 0.0;
		if(type == Type.SUM){
			ret = funcOne.eval(x) + funcTwo.eval(x);
		}else if (type == Type.PRODUCT){
			ret = funcOne.eval(x) * funcTwo.eval(x);
		}else{
			//there has been a serious error :/
			ret = Math.tan(0); //Make it obvious(ish) there's an error
		}
		return ret;
	}

}
