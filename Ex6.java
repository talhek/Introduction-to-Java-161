import java.util.Scanner;
public class Ex6 {
	public static void main(String[] args) {
		//This program will recieve an input (n) from the user.
		//the input represents the amount of squares on an n*n board.
		// The program will calculate and print the amount of squares with an odd size of edge.
		//Assumption: the input is bigger than 0.
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt(); //input
		int squares = 0; //this represents the number of squares
			while (n>0) //calculate how many squares withh an odd size of edge can fit the board.
			{
				squares = n*n + squares;
				n=n-2;
			}
			System.out.println(squares); //print the result
	}
}
