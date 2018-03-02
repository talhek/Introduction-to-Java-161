/**
 * This is a subclass of Calculator. It represents a Postfix Calculator.
 * @authors Dor and Tal.
 */
public class PostfixCalculator extends Calculator {
	
	private ExpTokenizer tokenizer;
	private StackAsArray tokensArr;
	private ValueToken evaluate;
	
	public void evaluate(String expr) throws ParseException
	{
		//if the string is empty, there is nothing to calculate. the result is 0 (like every calculator).
		boolean done = false;
		evaluate = new ValueToken(0);
		if (expr == "" || expr == null)
		{
			currentResult = 0;
			done = true;
		}
		
		if (!done)
		{
			// First, let's run through the string and calculate it's value according to the postfix calculator algorithm.
			tokenizer = new ExpTokenizer(expr, true); //this starts at index=0
			tokensArr = new StackAsArray();
			// while tokes remain:
			while (tokenizer.hasMoreTokens())
			{
				Object tokenizerType = tokenizer.nextElement();			
				//if the token is an operator, pop the top two elements of the stack, perform the operation and push the result onto the stack.
				if (tokenizerType instanceof BinaryOp)
				{
					ValueToken element1, element2;
					
					//if the stack is empty, it means the expression was invalid.
					if (tokensArr.isEmpty())
						throw new ParseException("cannot perform operation " + tokenizerType.toString());
					element2 = (ValueToken) tokensArr.pop();
				
					//if the stack is empty, it means the expression was invalid.
					if (tokensArr.isEmpty())
						throw new ParseException("cannot perform operation " + tokenizerType.toString());
					element1 = (ValueToken) tokensArr.pop();
				
					{
						evaluate = new ValueToken((((BinaryOp) (tokenizerType)).operate(element1.getValue(), element2.getValue())));
						tokensArr.push(evaluate);
					}
				}
				//if the token is a number, push the token onto the stack.
				else
				{
					evaluate = new ValueToken((((ValueToken) tokenizerType).getValue()));
					tokensArr.push(evaluate);
				}
				currentResult = evaluate.getValue();
			}
			//At this point the stack is supposed to be composed of only 1 number. otherwise, it means the expression was invalid.
			//To check this, we will: 1) pop the top and save it's value. 2)check if the stack is empty. 3)return the value. 4)act accordingly.
			boolean validation = true;
			if (tokensArr.isEmpty())
				validation = false;
			else
			{
				ValueToken saveValue = (ValueToken) tokensArr.pop();
				if(!tokensArr.isEmpty())
					validation = false;
				else			
					tokensArr.push(saveValue);
			}
			if (!validation)
				throw new ParseException("invalid expression");	
		}
	}
		

}