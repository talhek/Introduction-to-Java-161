import java.util.Scanner;
public class Ex3 {
	public static void main(String[] args) {
		
		//This program will recieve a sequence of numbers as input from the user.
		//It will calculate and print the geometric mean of 4 numbers: the 2 biggest even numbers and the 2 biggest odd numbers.
		//Assumption: the input is always bigger than 0 (ecxept for the last 0). The user will insert at least 2 even and 2 odd numbers.
		Scanner sc = new Scanner(System.in);
		int n=1; //input
		int even1,even2,odd1,odd2; //biggest even and odd numbers
		even1=0;
		even2=0;
		odd1=0;
		odd2=0;
		float geo_mean; 
		String result; 
		
		//User input + find the biggest even and odd numbers.
		while (n>0)
		{
			n = sc.nextInt();
			if (n%2==0)
			{
				if (n>even1 | n>even2)
				{	
					if (even1>=even2)
						even2=n;
					else
						even1=n;
				}
			}
			else
			{
				if (n>odd1 | n>odd2)
				{	
					if (odd1>=odd2)
						odd2=n;
					else
						odd1=n;
				}
			}
		}
		
		//Calculate the geometric mean, put it in a string that only shows the first 3 decimal digits and print.
		geo_mean=(float) Math.sqrt(even1*even2*odd1*odd2);
		geo_mean=(float) Math.sqrt(geo_mean);
		result = String.format("%.3f", geo_mean);
		System.out.println(result);
		
	}
}
