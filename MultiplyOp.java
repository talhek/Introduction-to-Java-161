/**
 * This is a subclass of BinaryOp. It represents multiplication between 2 operands.
 * @authors Dor and Tal
 */

public class MultiplyOp extends BinaryOp {
	
	private double product;
	
	public double operate(double left, double right)
	{
		product = left * right;
		return product;
	}
	
	public String toString()
	{
		return "*";
	}
}
