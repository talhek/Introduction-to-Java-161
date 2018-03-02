/**
 * Abstract class describing the characters of a calculator.
 * @authors Dor and Tal
 *
 */
public abstract class Calculator
{
	protected double currentResult = 0; //default value is 0, in case there is nothing to calculate.
	
	/**
	 * Evaluates an arithmetic expression.
	 * @param the expression that needs to be calculated.
	 */
	public abstract void evaluate(String expr);
	
	/**
	 * @return the evaluated result of an arithmetic expression.
	 * 0 is the default if no expression was given.
	 */
	public double getCurrentResult()
	{
			return currentResult;
	}
}
