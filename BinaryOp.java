/**
 * Abstract class describing binary arithmetic operations.
 */
public abstract class BinaryOp extends CalcToken {

	/**
	 * Return the result of this operation on its operands.
	 * @param left the left operand.
	 * @param right the right operand.
	 * @return the result of the operation.
	 */
	public abstract double operate(double left, double right);  
}