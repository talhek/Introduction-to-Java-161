/**
 * This is a subclass of BinaryOp. It represents subtraction between 2 operands.
 * @authors Dor and Tal
 */
public class SubtractOp extends BinaryOp {

	private double difference;
	
	public double operate(double left, double right)
	{
		difference = left - right;
		return difference;
	}
	
	public String toString()
	{
		return "-";
	}	
}
