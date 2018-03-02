import java.util.Scanner;
public class Ex5 {
	public static void main(String[] args) {
		//This program will recieve an integer from the user.
		//It will then find all the multiples of 11 from 1 to the number.
		//For eacvh multiple of 11, it will sum the digits, and multiple the result with the next sum.
		//The program will print the result.
		//Assumption: the input is bigger than 0.
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //input
		int multiple; //This will find the multiples of 11.
		int result=1; //This will be the final product.
		
		if (n<11) //if the input is smaller than 11 the result is 0.
			System.out.println(0);
		else
		{
			multiple = n;
			//Finding the multiple of 11, closest to the number.
			for (int i=0; i<10 & multiple%11!=0; i=i+1)
			{
				multiple=multiple-1;
			}
			//Now we have the biggest multiple of 11.
			//The program will calculate the sum of it's digits and move to the next multiple of 11, and do the same.
			while (multiple>=11)
			{
				int tempM=multiple; // saving memomry of the multiple.
				int sum = 0; //sum of the digits.
				while (tempM>0) //calculates the sum of the digits of each multiple of 11.
				{
					sum = sum + (tempM%10);
					tempM=tempM/10;	
				}
				result = sum*result;
				multiple = multiple - 11;
			}
			System.out.println(result); //print the result.
		}
	}
}
