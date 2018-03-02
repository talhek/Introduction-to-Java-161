/**
 * This is a subclass of BinaryOp. It represents division between 2 operands.
 * @authors Dor and Tal
 */

public class DivideOp extends BinaryOp {
	
	private double quotient;
	
	public double operate(double left, double right)
	{
		quotient = left / right;
		return quotient;
	}
	
	public String toString()
	{
		return "/";
	}
}
