import java.util.Enumeration;
/**
 * This class describes a tokenizer and extends Enumeration<Object>.
 * It is able to go through a string and split it into tokens that are between spaces.
 * It can go through the string from left to right or vice versa. 
 * @authors Dor and Tal
 */
public class ExpTokenizer extends Object implements Enumeration<Object>  {
	//fields
	private String [] result;
	private boolean direction;
	private int index;
	
	/**
	 * This is a constructor for ExpTokenizer. It recieves a given string (expression) and splits it into tokens.
	 * The default direction for a tokenizer created using this constructor is left to right.
	 *  @param exp the string to split into tokens.
	 */
	public ExpTokenizer(String exp) {
		this.result = exp.split(" ");
		if (isLegal(result))
		{
			this.direction = true;
			this.index = 0;
		}
	}
	
	/**
	 * This is a constructor for ExpTokenizer. It recieves a given string (expression) and splits it into tokens.
	 * The direction for a tokenizer created using this constructer can be from left to right or vice versa.
	 * @param exp the string to split into tokens.
	 * @param direction the direction this tokenizer will go through the string. true-from left to right. false-from right to left.
	 */
	public ExpTokenizer(String exp,boolean direction) {
		result = exp.split(" ");
		if (isLegal(result))
		{
			this.direction = direction;
			if(!this.direction)
				this.index=result.length-1;
			else 
				this.index = 0;
		}
	}
	
	//This is a helper method to check if a character is numeric.	
	private boolean isNum(char n)
	{
		boolean ans = false;
		String numbers = "0123456789";
		if (numbers.indexOf(n) != -1)
			ans = true;
		
		return ans;
	}
	//This is a helper method to check if a character is a legal operator.
	private boolean isOp(char o)
	{
		boolean ans = false;
		String operators = "+-*/^";
		if (operators.indexOf(o) != -1)
			ans = true;
		
		return ans;
	}
	
	//This is a helper method to check if the input is legal. if it isn't, it will throw a ParseException
	private boolean isLegal(String[] input) throws ParseException
	{
		boolean legal = true;
		//Let's check if all the tokens are legal.
		//Run through the array of tokens. 
		for (int i=0; i<input.length && legal; i++)
		{
			//Each time, run through the token itself.
			String inputi = input[i];
			int dotCount = 0; //this will be used if needed, to make sure there is no more than 1 dot in each token.

			for (int j=0; j<inputi.length() && legal; j++)
			{
				// if it's only 1 character, it should be a digit, or an operator.
				if (inputi.length() == 1)
				{
					if (!isNum(inputi.charAt(0)) && !isOp(inputi.charAt(0)))
					{
						legal = false;
						throw new ParseException("invalid token " + inputi);
					}
				}
				else
				{
					// if it's more than 1 character, it should be either a negative number or a multiple-digits number, or there's a dot.
					char current = inputi.charAt(j);
					//if the first character is not a digit, it can only be '-' or a dot.
					if (j==0)
					{
						if (!isNum(current) && current!='-' && current !='.')
						{
							legal = false;
							throw new ParseException ("invalid char " + current + " at token " + inputi);
						}
					}
					else
					{
						//if the first character is ok, we need to make sure that: there is up to one dot, and all the other chars are digits. 						
						if (!isNum(current))
						{
							if (current == '.' && dotCount<1)
								dotCount++;
							else
							{
								legal = false;
								throw new ParseException("invalid number " + inputi);
							}
						}	
					}
				}
			}
		}
		return legal;
	} 
	/**
	 * @return the next element of the string used for ExpTokenizer. 
	 */
	public Object nextElement() {
		CalcToken resultToken = null;
		String token =  nextToken();
		if (token.equals("+"))
			resultToken =  new AddOp();
		else if (token.equals("*"))
			resultToken =  new MultiplyOp();
			else if (token.equals("-"))
					resultToken = new SubtractOp();
				else if (token.equals("/"))
					resultToken = new DivideOp();
					else if (token.equals("^"))
						resultToken = new PowOp();

		else 
				resultToken = new ValueToken(Double.parseDouble(token));
		
		return resultToken;	
	}

	/**
	 * Tests if the string used for ExpTokenizer contains more elements.
	 * @returns true if and only if the string used for this ExpTokenizer contains at least one more element to provide; false otherwise.
	 */
	@Override
	public boolean hasMoreElements() {
		if(this.direction)
			return (this.index != this.result.length);
		else
			return (this.index >= 0);
	}
	
	/**
	 * @return the next token of the string used for this ExpTokenizer.
	 */
	public String nextToken() {
		String ret;
		if(this.direction){
			ret= this.result[this.index];
			this.index++;
		}
			
		else{
			ret= this.result[this.index];
			this.index--;
		}
		return ret;
	}

	/**
	 * Tests if the string used for ExpTokenizer contains more tokens.
	 * @returns true if and only if the string used for this ExpTokenizer contains at least one more token to provide; false otherwise.
	 */
	public boolean hasMoreTokens() {
		return hasMoreElements();
	}

	/**
	 * This method counts how many tokens are left in the string used for this ExpTokenizer.
	 * @return the number of tokens left.
	 */
	public int countTokens() {
		if(this.direction)
			return (this.result.length - this.index);
		else
			return (this.index+1);
	}
	

}