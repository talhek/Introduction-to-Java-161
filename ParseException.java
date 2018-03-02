/**
 * This is a subclass of RuntimeException. It represents a ParseException, made when invalid strings are used as input.
 * @authors Dor and Tal
 */
public class ParseException extends RuntimeException
{	
	
	/**
	 * This is a constructor for ParseException. It creates a new ParseException as such: "SYNTAX ERROR: message"
	 * @param message is a given message to be used for the specific ParseException.
	 */
	public ParseException(String message)
	{
		super("SYNTAX ERROR: " + message);
	}
}
