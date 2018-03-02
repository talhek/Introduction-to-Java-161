/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester
{

	private static boolean testPassed = true;
	private static int testNum = 0;
	
	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		// Each function here should test a different class.
		testValueToken();
		testPostfixCalculator();
		testAddOp();
		testSubtractOp();
		testMultiplyOp();
		testDivideOp();
		testPowOp();
		testCalculator();
		testExpTokenizer();
		testParseException();
			
		// Notifying the user that the code have passed all tests. 
		if (testPassed)
		{
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked. 
	 * In addition, if a test fails the function will print the error message.  
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg)
	{
		testNum++;

		if (!exp)
		{
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}
	
	/**
	 * Checks the ValueToken class.
	 */
	private static void testValueToken()
	{
		
		//declerations block
		ValueToken t1 = new ValueToken(5.1);
		ValueToken t2 = new ValueToken(-4);
		ValueToken t3 = new ValueToken(+0.0);
		ValueToken t4;
		t4 = new ValueToken(5-2+3.2);
		ValueToken t5 = new ValueToken(10.0/4);
		ValueToken t6 = new ValueToken(2.5*2);
		ValueToken t7 = new ValueToken(t4.getValue());
		ValueToken t8 = new ValueToken(t7.getValue()*t2.getValue());
		
		//tests block
		test(t1.getValue() == 5.1, "Value should be 5.1.");
		test(t1.toString().equals("5.1"), "Value toString should be 5.1.");
		test(t2.getValue() == -4, "Value should be -4.0");
		test(t2.toString().equals("-4.0"), "Value toString should be -4.0");
		test(t3.getValue() == 0.0, "Value should be 0.0");
		test(t3.toString().equals("0.0"), "Value toString should be 0.0");
		test(t4.getValue() == 6.2, "Value should be 6.2");
		test(t5.getValue() == 2.5, "Value should be 2.5");
		test(t3.getValue()/t5.getValue() == 0, "Value should be 0.0");
		test(Double.isInfinite(t5.getValue()/t3.getValue()) , "Value should be infinity");
		test(Double.isNaN(t3.getValue()/0), "Value should be Not-a-Number");
		test((int)t6.getValue() == 5, "Value should be 5.0");
		test(t7.getValue() == 6.2, "Value should be 6.2");
		test(t7.getValue() == t4.getValue(), "Value should be 6.2 on both ValueTokens");
		test(t8.getValue() == -24.8 ,"Value should be -24.8");
	
	}

	/**
	 * Checks the PostfixCalculator class.
	 */
	private static void testPostfixCalculator()
	{
		
		//declerations block
		PostfixCalculator pc1 = new PostfixCalculator();
		PostfixCalculator pc2 = new PostfixCalculator();
		PostfixCalculator pc3 = new PostfixCalculator();
		PostfixCalculator pc4 = new PostfixCalculator();
		PostfixCalculator pc5 = new PostfixCalculator();
		PostfixCalculator pc6 = new PostfixCalculator();
		PostfixCalculator pc7 = new PostfixCalculator();
		PostfixCalculator pc8 = new PostfixCalculator();
		PostfixCalculator oper = new PostfixCalculator();
		PostfixCalculator obj = new PostfixCalculator();
		PostfixCalculator invalid = new PostfixCalculator();
		
		//Different Pointers
		
		//operations block
		pc1.evaluate("2 3 +");;
		pc2.evaluate("3 5 -");;
		pc3.evaluate("2.5 4 *");
		pc4.evaluate("10 5 /");
		pc5.evaluate("2 3 ^");
		pc8.evaluate(".4");
		pc6.evaluate("");
		test(pc6.getCurrentResult() == 0, "The output should be 0.0");
		pc6.evaluate(null);
		test(pc6.getCurrentResult() == 0, "The output should be 0.0");
		pc7.evaluate("0 0 /");
		test(Double.isNaN(pc7.getCurrentResult()), "The output of ( 0 0 / ) should be Not-a-Number");
		pc7.evaluate("0 0 ^");
		test(pc7.getCurrentResult() == 1.0, "The output of ( 0 0 ^ ) should be 1.0");
		
		//tests block
		test(pc1.getCurrentResult() == 5.0, "The output of ( 2 3 + ) should be 3.0");
		test(pc2.getCurrentResult() == -2.0, "The output of ( 3 5 - ) should be -2.0");
		test(pc3.getCurrentResult() == 10, "The output of ( 2.5 4 * ) should be -2.0");
		test(pc4.getCurrentResult() == 2.0, "The output of ( 10 5 / ) should be -2.0");
		test(pc5.getCurrentResult() == 8.0, "The output of ( 2 3 ^ ) should be -2.0");
		test(pc8.getCurrentResult() == 0.4, "The output of ( .4 ) should be 0.4");
			//test operators
			try {oper.evaluate("+");} catch (ParseException m1){test((m1).getMessage().indexOf("cannot perform operation +")!=-1,"Not the right error");}
			try {oper.evaluate("-");} catch (ParseException m1){test((m1).getMessage().indexOf("cannot perform operation -")!=-1,"Not the right error");}
			try {oper.evaluate("*");} catch (ParseException m1){test((m1).getMessage().indexOf("cannot perform operation *")!=-1,"Not the right error");}
			try {oper.evaluate("/");} catch (ParseException m1){test((m1).getMessage().indexOf("cannot perform operation /")!=-1,"Not the right error");}
			try {oper.evaluate("^");} catch (ParseException m1){test((m1).getMessage().indexOf("cannot perform operation ^")!=-1,"Not the right error");}
		//test operations between objects
		obj.evaluate(pc1.getCurrentResult() + " " + pc3.getCurrentResult() + " -");
		test(obj.getCurrentResult() == -5, "The output of (2 3 + 2.5 4 * -) should be -5.0");
		obj.evaluate(pc5.getCurrentResult() + " " + pc4.getCurrentResult() + " ^");
		test(obj.getCurrentResult() == 64, "The output of (2 3 ^ 10 5 / ^) should be 64.0");
		obj.evaluate(pc2.getCurrentResult() + " " + pc4.getCurrentResult() + " + " + pc3.getCurrentResult() + " " +  pc1.getCurrentResult() + " " + pc4.getCurrentResult() + " * - /");
		test(Double.isNaN(obj.getCurrentResult()), "The output of (3 5 - 10 5 / 10 5 2 * -) equals (0 0 /) and should be Not-a-Number");
		
		//other invalid strings
		try { invalid.evaluate("7 8 & +"); }
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid token &")!=-1, "Not the right error");}
		try { invalid.evaluate("7 8 -&"); }
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid number")!=-1, "Not the right error");}
		try { invalid.evaluate("8.5.3"); }
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid number")!=-1, "Not the right error");}
		try {invalid.evaluate("-8.55.3"); }
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid number")!=-1, "Not the right error");}
		try {invalid.evaluate("6 7 8 9"); }
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid expression")!=-1, "Not the right error");}
		try {invalid.evaluate("2 4 * 6 5 + 4 8 /"); }
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid expression")!=-1, "Not the right error");}
	}
	
	/**
	 * Checks the AddOp class.
	 */
	private static void testAddOp()
	{
		//test AddOp
		AddOp a1 = new AddOp();
		test(a1.operate(3, 5) == 8, "The output should be 8.0");
		test(a1.operate(-3, 10*0.5) == 2.0, "The output should be 2.0");
		test(a1.toString().equals("+"), "The output should be '+'");
		//make sure AddOp extends BinaryOp
		BinaryOp b1 = new AddOp();
		test(b1 instanceof AddOp, "Test if AddOp extends BinaryOp - Should be true");
	}
	
	/**
	 * Checks the SubtractOp class.
	 */
	private static void testSubtractOp()
	{
		//test SubtractOp
		SubtractOp s1 = new SubtractOp();
		test(s1.operate(3, 5) == -2.0, "The output should be -2.0");
		test(s1.operate(8, -10*0.5) == 13.0, "The output should be 13.0");
		test(s1.toString().equals("-"), "The output should be '-'");
		//make sure SubtractOp extends BinaryOp
		BinaryOp b1 = new SubtractOp();
		test(b1 instanceof SubtractOp, "Test if SubstractOp extends BinaryOp - Should be true");
	}
	
	/**
	 * Checks the MultiplyOp class.
	 */
	private static void testMultiplyOp()
	{
		//test MultiplyOp
		MultiplyOp m1 = new MultiplyOp();
		test(m1.operate(3, 5) == 15.0, "The output should be 15.0");
		test(m1.operate(8, -10*0.5) == -40.0, "The output should be 13.0");
		test(m1.toString().equals("*"), "The output should be '*'");
		//make sure MultiplyOp extends BinaryOp
		BinaryOp b1 = new MultiplyOp();
		test(b1 instanceof MultiplyOp, "Test if MultiplyOp extends BinaryOp - Should be true");
	}
	
	/**
	 * Checks the DivideOp class.
	 */
	private static void testDivideOp()
	{
		//test DivideOp
		DivideOp d1 = new DivideOp();
		test(d1.operate(8, 2) == 4.0, "The output should be 4.0");
		test(d1.operate(10, -10*0.5) == -2.0, "The output should be -2.0");
		test(d1.operate(0, 5) == 0, "The output should be 0.0");
		test(d1.toString().equals("/"), "The output should be '/'");
		//make sure DivideOp extends BinaryOp
		BinaryOp b1 = new DivideOp();
		test(b1 instanceof DivideOp, "Test if DivideOp extends BinaryOp - Should be true");
	}
	
	/**
	 * Checks the PowOp class.
	 */
	private static void testPowOp()
	{
		//test PowOp
		PowOp p1 = new PowOp();
		test(p1.operate(2, 2) == 4.0, "The output should be 4.0");
		test(p1.operate(4, -0.5) == 0.5, "The output should be 0.5");
		test(p1.operate(0, 5) == 0, "The output should be 0.0");
		test(p1.operate(5, 0) == 1, "The output should be 1");
		test(p1.operate(0, 0) == 1, "The Output should be 1");
		test(p1.toString().equals("^"), "The output should be '^'");
		//make sure PowOp extends BinaryOp
		BinaryOp b1 = new PowOp();
		test(b1 instanceof PowOp, "Test if PowOp extends BinaryOp - Should be true");
	}
	
	/**
	 * Checks the Calculator class.
	 */
	private static void testCalculator()
	{
		//test Calculator
		Calculator c1 = new PostfixCalculator();
		//default value is 0
		test(c1.getCurrentResult()==0, "The output should be 0");
		c1.evaluate("5 3 +");
		test(c1.getCurrentResult()==8, "The output should be 8.0");

	}
	
	/**
	 * Checks the ExpTokenizer class.
	 */
	private static void testExpTokenizer()
	{
		//declerations with valid tokens
		String expr = "1.2 + - * / ^ -34";
		ExpTokenizer LtR1 = new ExpTokenizer(expr);
		ExpTokenizer LtR2 = new ExpTokenizer(expr, true);
		ExpTokenizer RtL = new ExpTokenizer(expr, false);
		//test if it's all good for different Legal tokens.
		test(((ValueToken) LtR1.nextElement()).getValue() == 1.2, "The first token should be '1.2'");
		test(((ValueToken) LtR2.nextElement()).getValue() == 1.2, "The first token should be '1.2'");
		test(((ValueToken) RtL.nextElement()).getValue() == -34, "The first token should be '-34'");
		test(LtR2.nextElement().toString().equals("+"), "The second token should be '+'");
		test(LtR2.nextElement().toString().equals("-"), "The third token should be '-'");
		test(LtR2.nextElement().toString().equals("*"), "The fourth token should be '*'");
		test(LtR2.nextElement().toString().equals("/"), "The fifth token should be '/'");
		test(LtR2.nextElement().toString().equals("^"), "The sixth token should be '^'");
		test(RtL.nextElement().toString().equals("^"), "The seventh token should be '^'");
		
		//declerations with invalid tokens: 1. more than one dot. 2. "-" not in the beginning. 3. illegal token(any token that is not a number or an operator. we already checked all operators work above)
		try { LtR2 = new ExpTokenizer("6.5.2"); } //2 dots
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid number 6.5.2")!=-1, "Not the right error");}
		try { LtR2 = new ExpTokenizer("3-2"); } //'-' not at the beginning
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid number 3-2")!=-1, "Not the right error");}
		try { LtR2 = new ExpTokenizer("!"); }
		catch (ParseException m1){test((m1).getMessage().indexOf("invalid token !")!=-1, "Not the right error");}		
	}
	
	/**
	 * Checks the ParseException class.
	 */
	private static void testParseException()
	{
		//test ParseException. Make sure the message is "SYNTAX ERROR: my message"
		ParseException m = new ParseException("All good!");
		test(m.getMessage().equals("SYNTAX ERROR: All good!"), "The output should be 'SYNTAX ERROR: All good!");
	}
	
}