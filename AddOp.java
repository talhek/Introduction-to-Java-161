/**
 * This is a subclass of BinaryOp. It represents addition between 2 operands.
 * @authors Dor and Tal
 */
public class AddOp extends BinaryOp {

	private double sum;

	public double operate(double left, double right)
	{
		sum = left + right;
		return sum;
	}
	
	public String toString()
	{
		return "+";
	}
}