/**
 * This is a subclass of BinaryOp. It represents Exponentiation of an operand by another operand.
 * @authors Dor and Tal
 */

public class PowOp extends BinaryOp {

	private double power;
	
	public double operate(double left, double right)
	{
		power = Math.pow(left, right);
		return power;
	}
	
	public String toString()
	{
		return "^";
	}	
}
