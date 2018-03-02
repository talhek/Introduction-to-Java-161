/**
 * This class extends CalcToken and represents the values of numbers' tokens. 
 * @authors Dor and Tal
*/
public class ValueToken extends CalcToken
{
	private double value;
	
	/**
	 * Create a new ValueToken.
	 * @param value is the value of the number token.
 	*/	
	public ValueToken(double value)
	{
		this.value = value;
	}
	
	/**
	 * A getter for ValueToken.
	 * @return the value of the token.
	 */
	public double getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return String.valueOf(value);
	}
	
}
