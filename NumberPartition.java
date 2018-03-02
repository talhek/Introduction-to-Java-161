/**
* This class devides the group of numbers [1,2,3,...,n] into 2 groups.
* The devision is made according to characteristic strings, composed of ones and zeroes.
* After the division, the program will check weather 3 terms are fulfilled:
* 1. The number of members in each group is equal. 2. the sum of each group's members is equal. 3. the sum of the group's memebers' squared is equal.
* The program finds and prints all the characteristic strings in which the 3 terms co-exist. 
* 
* @author Dor and Tal.
*/
public class NumberPartition
{

    /*
	* Check if a characteristic string defines a partition 
    * of the numbers 1...n  such that:
    * (1) both sides are of equal length
    * (2) both sides are of equal sum
    * (3) both sides have equal sums of squared elements.
    * returns false if arguments are null / "incorrect"
	*/
	
	/**
	* @param s is the characteristic strings.
	* @param n is the string length.
	* @return true if the characteristic string fulfills the 3 terms above, flase otherwise.
	*/
    public static boolean isNumberPartition(int n, String s)
    {
        // This function will recieve a given characteristic string and it's length(n).
    	// It will cehck if the input is legal and if terms (1), (2) and (3) co-exist.
    	// In accordance to the result, it will return TRUE if they exist of FALSE if they don't.
    
    	boolean ans = true;
    	int countOnes=0;
    	int countZeroes=0;
    	   	
    	// First, Let's check if the input is legal. Later, we will check if the given string is composed of 1's and 0's only.
    	if (n%2==1 || s==null || s.length()!=n)
    		ans = false;
    	// If it is, start checking if the terms are fulfilled. Also, it will make sure the string contains only 1's and 0's.
    	else
    	{
    		//First, check how many numbers are in each group (and weather the string is composed only of 1's and 0's.
    		for (int i=0; i<n & ans; i=i+1)
    		{
    			if(s.charAt(i)=='1')
    				countOnes=countOnes+1;
    			if(s.charAt(i)=='0')
    				countZeroes=countZeroes+1;    			
    		}
    		if (countOnes + countZeroes!=n || countOnes != countZeroes)
    			ans = false;
    		// If term (1) is TRUE and the input is correct, check terms (2) and (3).
    		else
    		{
    			int[] Ones = new int[countOnes];
    			int[] Zeroes = new int [countZeroes];
    			int sum1=0, sum2=0, square1=0, square2=0;
    			int index1=0, index2=0;
    			// Let's create arrays that contain the two groups:
    			for (int i=0; i<n; i=i+1)
    			{
    				if (s.charAt(i)=='1')
    				{
    					Ones[index1]=i+1;
    					sum1 = sum1 + Ones[index1];
    					square1 = (int) (square1 + Math.pow(Ones[index1],2));
    					index1 = index1+1;
    				}
    				if (s.charAt(i)=='0')
    				{
    					Zeroes[index2]=i+1;
    					sum2 = sum2 + Zeroes[index2];
    					square2 = (int) (square2 + Math.pow(Zeroes[index2],2));
    					index2=index2+1;
    				}
    			}
				if (sum1!=sum2 || square1!=square2)
					ans = false;
    		}
    	}
    	
    	return ans;
    }

    /**
     * This function looks and prints for all the valid characteristic strings of a a given length that fulfill all the temrs above.
     * @param n is the length of the strings.
     */
	// This function calls a recursive function in order to print all the valid characteristic strings that fulfill terms (1), (2) and (3) from above.
    public static void numberPartition(int n)
    {
		if (n > 0)
		{
			numberPartition(n, "");
		}
    }
	
	// This is a recursvie function will find all the valid characteristic strings that fulfill all the terms above.
    private static void numberPartition(int n, String s)
    {
        if (s.length() < n)
        {
        	
        	numberPartition(n, s+"0");
        	numberPartition(n, s+"1");
        }
        else
        {
        	if (isNumberPartition(n, s))
        		System.out.println(s);
        }
    }
}
