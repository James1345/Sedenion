package sedenion;

/** A class to represent dual numbers */
public class Dual{

	public final double real;
	public final double epsilon;

	public Dual(double r, double e){
		real = r;
		epsilon = e;
	}

	public Dual add(Dual that){
		return new Dual(this.real + that.real, this.epsilon+that.epsilon);
	}

	public Dual multiply(Dual that){
		return new Dual(this.real*that.real, this.real*that.epsilon + this.epsilon*that.real);
	}

	public double abs(){
		return eal;
	}

	public String toString(){
		return "("+real+", "+epsilon+")";
	}

}
