import java.util.Scanner;
public class Ex7 {
	public static void main (String args[]){
		//This program will recieve an input (n) and print a sand clock with (2*n-1) lines.
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //input
		
		//Check if n is bigger than 0. otherwise print "illegal input" and wait for a new input.
		while (n<1)
		{
			System.out.println("illegal input");
			n = sc.nextInt();
		}
		
		int dup = n; //duplicate of n
		
		//First part of the sand clock.		
		for (int i=0; i<n; i=i+1) //for each line of the first part
		{
			for (int space=0; space<i; space=space+1) //print as many spaces as needed.
				System.out.print(" ");
			for (int j=0; j<2*dup-1; j=j+1) //print as many * as needed.
				System.out.print("*");
			System.out.println();
			dup = dup-1;
		}

		//second part
		dup = 3; //reset the duplicate. this time. we start from 3.
		for (int i=n-1; i>0; i=i-1) //for each line of the second part
		{
			for (int space=1; space<i; space=space+1) //print as many spaces as needed.
				System.out.print(" ");
			for (int j=0; j<dup; j=j+1) //print as many * as needed.
				System.out.print("*");
			System.out.println();
			dup=dup+2;
		}
	}
}
