import java.util.Scanner;
public class Ex4 {
	public static void main(String[] args) {
		//This program will recieve an input "n" from the user and print the first n numbers of Fibonacci sequence.
		//Assumption: the input is bigger than 0.
		int m1=1,m2=1,mem=1;
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //input
		
		System.out.println(m1);
		System.out.println(m2);
		for (int i=3; i<=n; i=i+1)
		{
			m2=m2+m1;
			m1=mem;
			mem=m2;
			System.out.println(m2);
		}
	}
}
