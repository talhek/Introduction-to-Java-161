import java.util.Scanner;
public class Ex2 {
	public static void main(String[] args) {
		
		//This program will get an input from the user, and print the prime factorization of the number.
		//Assumption: the input is an integer bigger than 0.
		Scanner sc = new Scanner(System.in);
		
		//User input
		int n = sc.nextInt();
		
		// isPrime - boolean
		boolean isPrime = true;
		//Check if the number is prime. if it is, isPrime will be true, and we can just print the number.
		for (int i=2; (i*i<=n) & (isPrime); i=i+1)
		{
			if(n%i==0)
				isPrime = false;
		}
		if (isPrime)
			System.out.println(n);
		else
			//At this point, the number is not prime. we will find and print the factorization.
			for (int j=2; j<=n; j=j+1)
			{
				while(n%j==0)
				{
					System.out.println(j);
					n = n/j;
				}
			}
	}
}
