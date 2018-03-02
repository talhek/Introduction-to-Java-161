import java.util.Scanner;


public class EnglishCheckers {

	// Global constants
	public static final int RED   = 1;
	public static final int BLUE  = -1;
	public static final int EMPTY = 0;

	public static final int SIZE  = 8;

	// You can ignore these constants
	public static final int MARK  = 3;
	public static EnglishCheckersGUI grid;

	public static Scanner getPlayerFullMoveScanner = null;
	public static Scanner getStrategyScanner = null;

	public static final int RANDOM			= 1;
	public static final int DEFENSIVE		= 2;
	public static final int SIDES				= 3;
	public static final int CUSTOM			= 4;


	public static void main(String[] args) {

		// ******** Don't delete ********* 
		// CREATE THE GRAPHICAL GRID
		grid = new EnglishCheckersGUI(SIZE);
		// ******************************* 
		
		int[][] board = createBoard();
		showBoard(board);
		//printMatrix(board);

		interactivePlay();
		//twoPlayers();


		/* ******** Don't delete ********* */    
		if (getPlayerFullMoveScanner != null){
			getPlayerFullMoveScanner.close();
		}
		if (getStrategyScanner != null){
			getStrategyScanner.close();
		}
		/* ******************************* */

	}

	
	public static void fill4dArray(int[][] arr, int r, int row, int col, int nextrow, int nextcol)
	{
		//This function fills the second dimention of an array with given integers.
		arr[r][0]=row;
		arr[r][1]=col;
		arr[r][2]=nextrow;
		arr[r][3]=nextcol;
	}
	
	
	//This function will create a new english checkrs board.
	public static int[][] createBoard()
	{
		int[][] board = null;
		
		//This function will create a new english checkrs board.
		board = new int[SIZE][SIZE];
		//The function will go over all of the squares in the board (the array pointers).
		//If there should be a red disc, it will put 1 in it's value. if there should be a blue disc, it will put -1.
		//If the square is empty, it will put 0.
		for (int i=7; i>=0; i=i-1)
		{
			for (int j=0; j<SIZE; j=j+1)
			{
				if (i>4)
				{
						if(i%2==1)
						{
							if(j%2==1)
								board[i][j]=BLUE;
							else
								board[i][j]=EMPTY;
						}
						else
						{
							if(j%2==0)
								board[i][j]=BLUE;
							else
								board[i][j]=EMPTY;
						}
				}
				else
				{
					if(i>2)
						board[i][j]=EMPTY;
					else
					{
						if(i%2==0)
						{
							if(j%2==0)
								board[i][j]=RED;
							else
								board[i][j]=EMPTY;
						}
						else
						{
							if(j%2==1)
								board[i][j]=RED;
							else
								board[i][j]=EMPTY;
						}
					}
				}
			}
		}
		return board;
	}


	public static int[][] playerDiscs(int[][] board, int player)
	{
		
		//This function will return an array that contains the location of the requested player's disc, in the board state that was given as input.
		int[][] positions = null;

		//It will check all the squares on the board and count how many discs he has. if he has none, it will return an empty array.
		int n = 0;
		for (int j=0; j<SIZE; j=j+1)
			for(int i=0; i<SIZE; i=i+1)
				if (board[i][j]==player | board[i][j]==2*player)
					n = n+1;
				
		if (n==0)
			positions = new int[0][0];
		else //If there are discs of the requested player, It will check where exactly the discs are and add the locations to the array.
		{
			int row = 0;
			positions = new int[n][2];
			for (int j=0; j<SIZE; j=j+1)
				for(int i=0; i<SIZE; i=i+1)
					if(board[i][j]==player | board[i][j]==2*player)
					{
						positions[row][0]=i;
						positions[row][1]=j;
						row=row+1;
					}
		}

		return positions;
	}


	public static boolean isBasicMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol)
	{
		//This function will check whether or not the move is a basic valid move.
		boolean ans = false;

		//First, it will check if all the locations are on the board, and if so, if the "to" square is empty.
		if (fromRow >=0 & fromRow<SIZE & toRow>=0 & toRow<SIZE & fromCol>=0 & fromCol<SIZE & toCol>=0 & toCol<SIZE && board[toRow][toCol]==0)
		{
			//Now, it will determine if the disc is normal or a queen.
			if(board[fromRow][fromCol] == player) //if it's a normal disc, it can only move forward and diagonally.
				if((toRow == fromRow+player) & (toCol == fromCol + 1 | toCol==fromCol - 1))
					ans=true;
			if(board[fromRow][fromCol] == 2*player) //if it's a queen, it can move anywhere diagonally.
				if((toRow == fromRow+player | toRow == fromRow-player) & (toCol == fromCol + 1 | toCol==fromCol - 1))
					ans=true;
		}
		
		return ans;
	}


	public static int[][] getAllBasicMoves(int[][] board, int player)
	{
		//This function will find all the basic moves possible for a requested player.
		int[][] moves = null;

		//First, it will find the positions of all of the player's discs.
		int[][] positions;
		positions = playerDiscs(board, player);
		
		//Now, for each positions, check if the disc can move. if it can, count it.
		int countMoves=0;
		for (int i=0; i<positions.length; i=i+1)
		{
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]+player, positions[i][1]+1))
				countMoves=countMoves+1;
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]-player, positions[i][1]+1))
				countMoves=countMoves+1;
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]+player, positions[i][1]-1))
				countMoves=countMoves+1;
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]-player, positions[i][1]-1))
				countMoves=countMoves+1;
		}
		//Now, we can know how many moves are available. The program will put the available moves in an array.
		moves = new int[countMoves][4];
		int row=0;
		for (int j=0; j<positions.length; j=j+1)
		{
			if(isBasicMoveValid(board, player, positions[j][0], positions[j][1], positions[j][0]+player, positions[j][1]+1))
			{
					fill4dArray(moves, row, positions[j][0], positions[j][1], positions[j][0]+player, positions[j][1]+1);
					row=row+1;
			}		
			if(isBasicMoveValid(board, player, positions[j][0], positions[j][1], positions[j][0]-player, positions[j][1]+1))
			{
					fill4dArray(moves, row, positions[j][0], positions[j][1], positions[j][0]-player, positions[j][1]+1);
					row=row+1;
			}		
			if(isBasicMoveValid(board, player, positions[j][0], positions[j][1], positions[j][0]+player, positions[j][1]-1))
			{
					fill4dArray(moves, row, positions[j][0], positions[j][1], positions[j][0]+player, positions[j][1]-1);
					row=row+1;
			}
			if(isBasicMoveValid(board, player, positions[j][0], positions[j][1], positions[j][0]-player, positions[j][1]-1))
			{
					fill4dArray(moves, row, positions[j][0], positions[j][1], positions[j][0]-player, positions[j][1]-1);
					row=row+1;
			}
		}
		return moves;
	}


	public static boolean isBasicJumpValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol)
	{
	//This function will check whether or not the move is a basic valid jump.
	boolean ans = false;

	//First, it will check if all the locations are on the board.
	if (fromRow >=0 & fromRow<SIZE & toRow>=0 & toRow<SIZE & fromCol>=0 & fromCol<SIZE & toCol>=0 & toCol<SIZE && board[toRow][toCol]==0)
	{
		//Now, it will determine if the disc is normal or a queen.
		if(board[fromRow][fromCol] == player) //if it's a normal disc, it can only jump forward and diagonally.
		{
			if((toRow == fromRow+2*player) & (toCol == fromCol + 2) && ((board[fromRow+player][fromCol+1]==-player) | (board[fromRow+player][fromCol+1]==-2*player)))
				ans=true;
			if((toRow == fromRow+2*player) & (toCol == fromCol - 2) && ((board[fromRow+player][fromCol-1]==-player) | (board[fromRow+player][fromCol-1]==-2*player)))
				ans=true;
		}
		if(board[fromRow][fromCol] == player+player) //if it's a queen, it can jump anywhere diagonally.
		{
			if(((toRow == fromRow+2*player) & (toCol == fromCol + 2)) && ((board[toRow-player][toCol-1]==-player) | (board[toRow-player][toCol-1]==-2*player)))
				ans=true;
			if(((toRow == fromRow+2*player) & (toCol == fromCol - 2)) && ((board[toRow-player][toCol+1]==-player) | (board[toRow-player][toCol+1]==-2*player)))
				ans=true;
			if(((toRow == fromRow-2*player) & (toCol == fromCol + 2)) && ((board[toRow+player][toCol-1]==-player) | (board[toRow+player][toCol-1]==-2*player)))
				ans=true;
			if(((toRow == fromRow-2*player) & (toCol == fromCol - 2)) && ((board[toRow+player][toCol+1]==-player) | (board[toRow+player][toCol+1]==-2*player)))
				ans=true;
		}
	}
	return ans;
	}


	public static int [][] getRestrictedBasicJumps(int[][] board, int player, int row, int col)
	{
		//This function will check how many basic jump moves a disc can do at a specific state.
		int[][] moves = null;

		//First, let's count how many moves as decribed are possible.
		int countBjumps=0;
		for (int i=-2; i<=2; i=i+4)
			for (int j=-2; j<=2; j=j+4)
				if (isBasicJumpValid(board, player, row, col, row+i, col+j))
					countBjumps = countBjumps+1;
		//Now, we can put this moves in an array.
		moves = new int[countBjumps][4];
		int r=0;
		for (int i=-2; i<=2; i=i+4)
			for (int j=-2; j<=2; j=j+4)
				if (isBasicJumpValid(board, player, row, col, row+i, col+j))
				{
					fill4dArray(moves, r, row, col, row+i, col+j);
					r=r+1;
				}
		return moves;
	}


	public static int[][] getAllBasicJumps(int[][] board, int player)
	{
		//This function will check and return an array with all the basic jumps available for a requested player.
		int[][] moves = null;

		//First, let's find all of the player's discs.
		int[][] positions;
		positions = playerDiscs(board, player);
		
		//Let's check how many basic jumps are possible.
		int countAllBJumps=0;
		for(int i=0; i<positions.length; i=i+1)
		{
			int[][] RestrictedJumps;
			RestrictedJumps = getRestrictedBasicJumps(board, player, positions[i][0], positions[i][1]);
			countAllBJumps = countAllBJumps + RestrictedJumps.length;
		}
		
		//Let's put all the available jumps in an array.
		int r2=0;
		moves = new int[countAllBJumps][4];
			for(int i=0; i<positions.length; i=i+1)
			{
				int[][] RestrictedJumps;
				RestrictedJumps = getRestrictedBasicJumps(board, player, positions[i][0], positions[i][1]);
					for (int j=0; j<RestrictedJumps.length; j=j+1)
					{
						fill4dArray(moves, r2, RestrictedJumps[j][0], RestrictedJumps[j][1], RestrictedJumps[j][2], RestrictedJumps[j][3]);
						r2=r2+1;
					}
			}
		return moves;
	}


	public static boolean canJump(int[][] board, int player)
	{
		//This function will check if a requested player can make a jump or not.
		boolean ans = false;
		
		if(getAllBasicJumps(board, player).length != 0)
			ans = true;
		
		return ans;
	}


	public static boolean isMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol)
	{
		//This Function will test if a specific move is valid.
		boolean ans = false;

		//Firt, it will check if the move is a valid basic jump.
		if(isBasicJumpValid(board, player, fromRow, fromCol, toRow, toCol))
			ans=true;
		//If not, it will check if the player can make any basic jump move. If he can, the move is invalid.
		else
			if (!canJump(board, player))
		//if he can't, it will check if it's a valid basic move.
				if(isBasicMoveValid(board, player, fromRow, fromCol, toRow, toCol))
					ans=true;

		return ans;
	}


	public static boolean hasValidMoves(int[][] board, int player)
	{
		//This function will test weather the requested player is able to do any valid moves.
		boolean ans = false;

		//First, it will check if he can do any basic jumps.
		if (canJump(board,player))
			ans = true;
		else
		{
			if(getAllBasicMoves(board, player).length != 0)
				ans = true;
		}

		return ans;
	}


	public static int[][] playMove(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol)
	{
		//This function will change the board according to the action that was made by the player.
		int step=Math.abs(toRow-fromRow);
		//First, it will check if the move was a basic move. If so, it will just move the disc.
		if (step==1)
		{
			board[toRow][toCol]=board[fromRow][fromCol];
			board[fromRow][fromCol]=0;
		}
		//If not, the move was a jump. It will move the disc and burn the required opponent's disc.
		else
		{
			board[toRow][toCol]=board[fromRow][fromCol];
			board[fromRow][fromCol]=0;
			if (toRow-fromRow>0)
			{
				if(toCol-fromCol>0)
					board[toRow-1][toCol-1]=0;
				else
					board[toRow-1][toCol+1]=0;
			}
			else
			{
				if(toCol-fromCol>0)
					board[toRow+1][toCol-1]=0;
				else
					board[toRow+1][toCol+1]=0;
			}
		}
			
		//Last, it will check if a new queen was nominated.
		if (player == 1)
			if(toRow==7)
			{
				board[toRow][toCol]=2;
			}
		if (player == -1)
			if(toRow==0)
			{
				board[toRow][toCol]=-2;
			}
			
		return board;
	}


	public static boolean gameOver(int[][] board, int player)
	{
		//This function will determine weather the game has ended or not.
		boolean ans = false;

		//First, it will check if any of the players is out of discs.
		if (playerDiscs(board, RED).length!=0)
		{
			if(playerDiscs(board, BLUE).length==0)
				ans = true;
		}
		else
			ans = true;
		
		//if not, it will check if the current player still has valid moves to do.
		if (!ans)
		{
			if (!hasValidMoves(board, player))
			ans=true;
		}

		return ans;
	}


	public static int findTheLeader(int[][] board)
	{
		//This function will check who is the leader.
		int ans = 0;

		//It will go through the board and count points.
		int countRED=0;
		int countBLUE=0;
		for(int i=0; i<SIZE; i=i+1)
		{
			for(int j=0; j<SIZE; j=j+1)
			{
				if(board[i][j]==RED)
					countRED=countRED+1;
				if(board[i][j]==2*RED)
					countRED=countRED+2;
				if(board[i][j]==BLUE)
					countBLUE=countBLUE+1;
				if(board[i][j]==2*BLUE)
					countBLUE=countBLUE+2;
			}
		}
		if (countRED>countBLUE)
			ans=RED;
		else
		{
			if(countBLUE>countRED)
				ans=BLUE;
			else
				ans=0;
		}
		
		return ans;
	}
	
	
	public static int[][] randomPlayer(int[][] board, int player)
	{
		//This function will return the board after a random valid move of the requested player.

		//First, it will check if the player has any valid moves to do.
		if (hasValidMoves(board, player))
		{
			int random;
			//if he can't jump, he will make a basic move;
			if (!canJump(board,player))
			{
				int[][] anybasic = getAllBasicMoves(board, player);
				random = (int)(Math.random()*anybasic.length);
				playMove(board, player, anybasic[random][0], anybasic[random][1], anybasic[random][2], anybasic[random][3]);
			}
			//if he can jump, he will jump
			else
			{
				int[][] anyjump = getAllBasicJumps(board,player);
				random = (int)(Math.random()*anyjump.length);
				int[][] restrictedjump = getRestrictedBasicJumps(board, player, anyjump[random][0], anyjump[random][1]);
				int discState = board[(anyjump[random][0])][(anyjump[random][1])];
				boolean newQueen = false;
					while (restrictedjump.length !=0 & !newQueen)
					{
						random = (int)(Math.random()*restrictedjump.length);
						playMove(board, player, restrictedjump[random][0], restrictedjump[random][1], restrictedjump[random][2], restrictedjump[random][3]);
						int newRow = restrictedjump[random][2];
						int newCol = restrictedjump[random][3];
						restrictedjump = getRestrictedBasicJumps(board, player, restrictedjump[random][2], restrictedjump[random][3]);
						//If after jumping the disc becomes a queen, it can't jump anymore at this turn.
						if((board[newRow][newCol]) != discState)
						{
							newQueen = true;
						}			
					}
			}
		}
		return board;
	}


	public static boolean isDefensiveMove(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol)
	{
		//This Function will check if a move is a defensive move.
		boolean isDefensive = true;
		
		//First, it will make a duplicate of the board.
		int[][] testMove = new int[board.length][];
		for (int i=0; i<testMove.length; i=i+1)
		{
			testMove[i]= new int[board[i].length];
			for(int j=0; j<testMove[i].length; j=j+1)
				testMove[i][j] = board[i][j];
		}
		
		
		//If the enemy can jump after this move, it means this move is not defensive. If he can't, it is.
		playMove(testMove, player, fromRow, fromCol, toRow, toCol);
		if (canJump(testMove, -player))
			isDefensive=false;
		
		return isDefensive;
	}
	
	public static int[][] getAllDefensiveMoves(int[][] board, int player)
	{
		//This function will find and return all the possible defensive moves.

		//First, it will check all the possible basic moves. Then it will check how many of them are defensive.
		int[][] anybasic = getAllBasicMoves(board, player);
		int countDefensive = 0;
		for (int i=0; i<anybasic.length; i=i+1)
		{
			if(isDefensiveMove(board, player, anybasic[i][0], anybasic[i][1], anybasic[i][2], anybasic[i][3]))
				countDefensive = countDefensive+1;
		}
		//Now, it will build an array of all the possible defensive moves.
		int[][] anyDefensive = new int[countDefensive][4];
		int dr = 0;
		for (int i=0; i<anybasic.length; i=i+1)
		{
			if(isDefensiveMove(board, player, anybasic[i][0], anybasic[i][1], anybasic[i][2], anybasic[i][3]))
			{
				fill4dArray(anyDefensive, dr, anybasic[i][0], anybasic[i][1], anybasic[i][2], anybasic[i][3]);
				dr = dr+1;
			}
		}
		return anyDefensive;
	}
	
	public static int[][] defensivePlayer(int[][] board, int player)
	{
		//This function will return the board after a defensive valid move of the requested player.
		
		//First, it will check if the player has any valid moves to do.
		if (hasValidMoves(board, player))
		{
			//If he can jump, he will jump.
			int random;
			if(canJump(board,player))
			{
				int[][] anyjump = getAllBasicJumps(board,player);
				random = (int)(Math.random()*anyjump.length);
				int[][] restrictedjump = getRestrictedBasicJumps(board, player, anyjump[random][0], anyjump[random][1]);
				int discState = board[(anyjump[random][0])][(anyjump[random][1])];
				boolean newQueen = false;
				while (restrictedjump.length !=0 & !newQueen)
				{
					random = (int)(Math.random()*restrictedjump.length);
					playMove(board, player, restrictedjump[random][0], restrictedjump[random][1], restrictedjump[random][2], restrictedjump[random][3]);
					int newRow = restrictedjump[random][2];
					int newCol = restrictedjump[random][3];
					restrictedjump = getRestrictedBasicJumps(board, player, restrictedjump[random][2], restrictedjump[random][3]);
					//If after jumping the disc becomes a queen, it can't jump anymore at this turn.
					if((board[newRow][newCol]) != discState)
					{
						newQueen = true;
					}		
				}
			}
			//If he can't jump, he will look for the a defensive move he can do.
			else
			{
				int[][] anyDefensive = getAllDefensiveMoves(board, player);
				//If there is any defensive move to do, pick randomly;
				if (anyDefensive.length != 0)
				{
					random = (int)(Math.random()*anyDefensive.length);
					playMove(board, player, anyDefensive[random][0], anyDefensive[random][1], anyDefensive[random][2], anyDefensive[random][3]);				
				}
				//If not, pick a random basic move.
				else
				{
					int[][] anybasic = getAllBasicMoves(board, player);
					random = (int)(Math.random()*anybasic.length);
					playMove(board, player, anybasic[random][0], anybasic[random][1], anybasic[random][2], anybasic[random][3]);
				}
			}
		}
		return board;
	}

	public static int[][] sidesPlayer(int[][] board, int player)
	{
		//This function will return the board after the player "did his best" to move his discs to the sides of the board.

		//First, it will check if the player has any valid moves to do.
		if (hasValidMoves(board, player))
		{
			//If he can jump, he will jump.
			int random;
			if(canJump(board,player))
			{
				int[][] anyjump = getAllBasicJumps(board,player);
				random = (int)(Math.random()*anyjump.length);
				int[][] restrictedjump = getRestrictedBasicJumps(board, player, anyjump[random][0], anyjump[random][1]);
				int discState = board[(anyjump[random][0])][(anyjump[random][1])];
				boolean newQueen = false;
				while (restrictedjump.length !=0 & !newQueen)
				{
					random = (int)(Math.random()*restrictedjump.length);
					playMove(board, player, restrictedjump[random][0], restrictedjump[random][1], restrictedjump[random][2], restrictedjump[random][3]);
					int newRow = restrictedjump[random][2];
					int newCol = restrictedjump[random][3];
					restrictedjump = getRestrictedBasicJumps(board, player, restrictedjump[random][2], restrictedjump[random][3]);
					//If after jumping the disc becomes a queen, it can't jump anymore at this turn.
					if((board[newRow][newCol]) != discState)
					{
						newQueen = true;
					}	
				}
			}
			//If he can't jump, he will try to move his discs to the sides of the board.
			else
			{
			//This part will consist of 3 loops. 
			//The first one will check what is the shortest distance from a disc that can move to the sides.
			//The second will count how many moves as such there are.
			//The third will put all this moves in one array.
			int[][] anybasic = getAllBasicMoves(board, player);
			int[][] sideMoves;
			int shortest=7;
			int countSides = 0;
			for (int i=0; i<anybasic.length & shortest!=0; i=i+1)
			{
				if(7-anybasic[i][3]<shortest)
					shortest=7-anybasic[i][3];
				if(anybasic[i][3]<shortest)
					shortest=anybasic[i][3];
			}
			for (int i=0; i<anybasic.length; i=i+1)
			{
				if(7-anybasic[i][3]==shortest | anybasic[i][3]==shortest)
					countSides=countSides+1;
			}
			sideMoves = new int[countSides][4];
			int sr=0;
			for (int i=0; i<anybasic.length; i=i+1)
			{
				if(7-anybasic[i][3]==shortest | anybasic[i][3]==shortest)
				{
					fill4dArray(sideMoves, sr, anybasic[i][0], anybasic[i][1], anybasic[i][2], anybasic[i][3]);
					sr=sr+1;
				}
			}
			//Now, it will pick a random possible move to the sides and do it.
			random = (int)(Math.random()*sideMoves.length);
			playMove(board, player, sideMoves[random][0], sideMoves[random][1], sideMoves[random][2], sideMoves[random][3]);
			}
		}
		return board;
	}

	
	


	
	
	
	
	
	//******************************************************************************//

	/* ---------------------------------------------------------- *
	 * Play an interactive game between the computer and you      *
	 * ---------------------------------------------------------- */
	public static void interactivePlay() {
		int[][] board = createBoard();
		showBoard(board);

		System.out.println("Welcome to the interactive Checkers Game !");

		int strategy = getStrategyChoice();
		System.out.println("You are the first player (RED discs)");

		boolean oppGameOver = false;
		while (!gameOver(board, RED) && !oppGameOver) {
			board = getPlayerFullMove(board, RED);

			oppGameOver = gameOver(board, BLUE);
			if (!oppGameOver) {
				EnglishCheckersGUI.sleep(200);

				board = getStrategyFullMove(board, BLUE, strategy);
			}
		}

		int winner = 0;
		if (playerDiscs(board, RED).length == 0  |  playerDiscs(board, BLUE).length == 0){
			winner = findTheLeader(board);
		}

		if (winner == RED) {
			System.out.println();
			System.out.println("\t *************************");
			System.out.println("\t * You are the winner !! *");
			System.out.println("\t *************************");
		}
		else if (winner == BLUE) {
			System.out.println("\n======= You lost :( =======");
		}
		else
			System.out.println("\n======= DRAW =======");
	}


	/* --------------------------------------------------------- *
	 * A game between two players                                *
	 * --------------------------------------------------------- */
	public static void twoPlayers() {
		int[][] board = createBoard();
		showBoard(board);

		System.out.println("Welcome to the 2-player Checkers Game !");

		boolean oppGameOver = false;
		while (!gameOver(board, RED)  &  !oppGameOver) {
			System.out.println("\nRED's turn");
			board = getPlayerFullMove(board, RED);

			oppGameOver = gameOver(board, BLUE);
			if (!oppGameOver) {
				System.out.println("\nBLUE's turn");
				board = getPlayerFullMove(board, BLUE);
			}
		}

		int winner = 0;
		if (playerDiscs(board, RED).length == 0  |  playerDiscs(board, BLUE).length == 0)
			winner = findTheLeader(board);

		System.out.println();
		System.out.println("\t ************************************");
		if (winner == RED)
			System.out.println("\t * The red player is the winner !!  *");
		else if (winner == BLUE)
			System.out.println("\t * The blue player is the winner !! *");
		else
			System.out.println("\t * DRAW !! *");
		System.out.println("\t ************************************");
	}


	/* --------------------------------------------------------- *
	 * Get a complete (possibly a sequence of jumps) move        *
	 * from a human player.                                      *
	 * --------------------------------------------------------- */
	public static int[][] getPlayerFullMove(int[][] board, int player) {
		// Get first move/jump
		int fromRow = -1, fromCol = -1, toRow = -1, toCol = -1;
		boolean jumpingMove = canJump(board, player);
		boolean badMove   = true;
		getPlayerFullMoveScanner = new Scanner(System.in);//I've modified it
		while (badMove) {
			if (player == 1){
				System.out.println("Red, Please play:");
			} else {
				System.out.println("Blue, Please play:");
			}

			fromRow = getPlayerFullMoveScanner.nextInt();
			fromCol = getPlayerFullMoveScanner.nextInt();

			int[][] moves = jumpingMove ? getAllBasicJumps(board, player) : getAllBasicMoves(board, player);
			markPossibleMoves(board, moves, fromRow, fromCol, MARK);
			toRow   = getPlayerFullMoveScanner.nextInt();
			toCol   = getPlayerFullMoveScanner.nextInt();
			markPossibleMoves(board, moves, fromRow, fromCol, EMPTY);

			badMove = !isMoveValid(board, player, fromRow, fromCol, toRow, toCol); 
			if (badMove)
				System.out.println("\nThis is an illegal move");
		}

		// Apply move/jump
		board = playMove(board, player, fromRow, fromCol, toRow, toCol);
		showBoard(board);

		// Get extra jumps
		if (jumpingMove) {
			boolean longMove = (getRestrictedBasicJumps(board, player, toRow, toCol).length > 0);
			while (longMove) {
				fromRow = toRow;
				fromCol = toCol;

				int[][] moves = getRestrictedBasicJumps(board, player, fromRow, fromCol);

				boolean badExtraMove = true;
				while (badExtraMove) {
					markPossibleMoves(board, moves, fromRow, fromCol, MARK);
					System.out.println("Continue jump:");
					toRow = getPlayerFullMoveScanner.nextInt();
					toCol = getPlayerFullMoveScanner.nextInt();
					markPossibleMoves(board, moves, fromRow, fromCol, EMPTY);

					badExtraMove = !isMoveValid(board, player, fromRow, fromCol, toRow, toCol); 
					if (badExtraMove)
						System.out.println("\nThis is an illegal jump destination :(");
				}

				// Apply extra jump
				board = playMove(board, player, fromRow, fromCol, toRow, toCol);
				showBoard(board);

				longMove = (getRestrictedBasicJumps(board, player, toRow, toCol).length > 0);
			}
		}
		return board;
	}


	/* --------------------------------------------------------- *
	 * Get a complete (possibly a sequence of jumps) move        *
	 * from a strategy.                                          *
	 * --------------------------------------------------------- */
	public static int[][] getStrategyFullMove(int[][] board, int player, int strategy) {
		if (strategy == RANDOM)
			board = randomPlayer(board, player);
		else if (strategy == DEFENSIVE)
			board = defensivePlayer(board, player);
		else if (strategy == SIDES)
			board = sidesPlayer(board, player);

		showBoard(board);
		return board;
	}


	/* --------------------------------------------------------- *
	 * Get a strategy choice before the game.                    *
	 * --------------------------------------------------------- */
	public static int getStrategyChoice() {
		int strategy = -1;
		getStrategyScanner = new Scanner(System.in);
		System.out.println("Choose the strategy of your opponent:" +
				"\n\t(" + RANDOM + ") - Random player" +
				"\n\t(" + DEFENSIVE + ") - Defensive player" +
				"\n\t(" + SIDES + ") - To-the-Sides player player");
		while (strategy != RANDOM  &  strategy != DEFENSIVE
				&  strategy != SIDES) {
			strategy=getStrategyScanner.nextInt();
		}
		return strategy;
	}


	/* --------------------------------------- *
	 * Print the possible moves                *
	 * --------------------------------------- */
	public static void printMoves(int[][] possibleMoves) {
		for (int i = 0;  i < 4;  i = i+1) {
			for (int j = 0;  j < possibleMoves.length;  j = j+1)
				System.out.print(" " + possibleMoves[j][i]);
			System.out.println();
		}
	}


	/* --------------------------------------- *
	 * Mark/unmark the possible moves          *
	 * --------------------------------------- */
	public static void markPossibleMoves(int[][] board, int[][] moves, int fromRow, int fromColumn, int value) {
		for (int i = 0;  i < moves.length;  i = i+1)
			if (moves[i][0] == fromRow  &  moves[i][1] == fromColumn)
				board[moves[i][2]][moves[i][3]] = value;

		showBoard(board);
	}


	/* --------------------------------------------------------------------------- *
	 * Shows the board in a graphic window                                         *
	 * you can use it without understanding how it works.                          *                                                     
	 * --------------------------------------------------------------------------- */
	public static void showBoard(int[][] board) {
		grid.showBoard(board);
	}


	/* --------------------------------------------------------------------------- *
	 * Print the board              					                           *
	 * you can use it without understanding how it works.                          *                                                     
	 * --------------------------------------------------------------------------- */
	public static void printMatrix(int[][] matrix){
		for (int i = matrix.length-1; i >= 0; i = i-1){
			for (int j = 0; j < matrix.length; j = j+1){
				System.out.format("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}

}
