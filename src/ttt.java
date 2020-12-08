import java.util.Random;
import java.util.Scanner;

/*
 * Eric Wei 	May 20, 2017
 * ttt makes up a conosole version of tic tac toe with
 * an uneabtable AI using the minimax algorithmn
 */

/*
 * The ttt class has the game occur within it with its 
 * own board member variable to represent the game
 */
public class ttt {
	char[][] board = new char[3][3];
	boolean GameOver = false;

	public ttt() {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				board[i][k] = ' ';
			}
		}
	}

	public class Move {
		int row;
		int col;

		public Move(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	/*
	 * game funct starts and ends the game with a prompt for the 
	 * user to pick first or second move.
	 */
	public void game(Scanner sc) {
		System.out.println("If you wish to move first, please enter 1. "
				+ "Otherwise enter 0");
		int first = sc.nextInt();
		int game = 0;
		while (GameOver == false) {
			if (first == 1) {
				playerTurn(sc);
				this.print();
				game = this.checkGameOver();
				if (game != -1) {
					GameOver = true;
					if (game == 1) {
						System.out.println("CPU wins!");
					}
					if (game == 0) {
						System.out.println("Player wins!");
					}
					if (game == 2) {
						System.out.println("It's a draw!");
					}
					break;
				}
				System.out.println("AI: Oh boy! Here we go killing again!!!");
				computerTurn();
				this.print();
				game = this.checkGameOver();
				if (game != -1) {
					GameOver = true;
					if (game == 1) {
						System.out.println("CPU wins!");
					}
					if (game == 0) {
						System.out.println("Player wins!");
					}
					if (game == 2) {
						System.out.println("It's a draw!");
					}
					break;
				}
			}
			else {
				System.out.println("AI: Oh boy! Here we go killing again!!!");
				System.out.println(GameOver);
				computerTurn();
				System.out.println(GameOver);
				this.print();
				game = this.checkGameOver();
				System.out.println(GameOver);
				if (game != -1) {
					GameOver = true;
					if (game == 1) {
						System.out.println("CPU wins!");
					}
					if (game == 0) {
						System.out.println("Player wins!");
					}
					if (game == 2) {
						System.out.println("It's a draw!");
					}
					break;
				}
				playerTurn(sc);
				this.print();
				System.out.println("After print");
				game = this.checkGameOver();
				System.out.println(game);
				System.out.println(GameOver);
				if (game != -1) {
					GameOver = true;
					if (game == 1) {
						System.out.println("CPU wins!");
					}
					if (game == 0) {
						System.out.println("Player wins!");
					}
					if (game == 2) {
						System.out.println("It's a draw!");
					}
					break;
				}
			}
		}
	}
	
	/*
	 * playerTurn is the func that prompts user for 
	 * his or her move and places it
	 */
	public boolean playerTurn(Scanner sc) {
		if (GameOver == true) {
			return false;
		}
		while (true) {
			System.out.println("Print row and enter and print col and enter");
			int row = sc.nextInt();
			int col = sc.nextInt();
			if (row >= 3 || row < 0 || col >= 3 || col <0) {
				System.out.println("Invalid inputs");
				continue;
			}
			if (this.open(row, col)) {
				System.out.println("Space open! You have placed your O there");
				this.place(row, col, 0);
				return true;
			}
			System.out.println("Space not open. Please try again");
		}
	}
	
	/*
	 * computerTurn is the func that prompts the bot 
	 * to play its move
	 */
	public boolean computerTurn() {
		if (this.checkGameOver() != -1) {
			return false;
		}
		/*
		Random ran = new Random();
		while (true) {
			int n = ran.nextInt(3);
			int m = ran.nextInt(3);
			if (this.open(n, m)) {
				place(n, m, 1);
				return true;
			}
		}
		*/
		Move opMove = bestMove();
		place(opMove.row, opMove.col, 1);
		return true;
	}
	

	/*
	 * checkGameOver returns 0 if player has won,
	 * 1 if bot has won,
	 * 2 if a draw,
	 * and -1 if the game isn't over
	 */
	public int checkGameOver() {
		char [][] b = board;
		if (b[0][0] == b[0][1] && b[0][0] == b[0][2] && b[0][0] != ' ') {
			if (b[0][0] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (b[1][0] == b[1][1] && b[1][0] == b[1][2] && b[1][0] != ' ') {
			if (b[1][0] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (b[2][0] == b[2][1] && b[2][0] == b[2][2] && b[2][0] != ' ') {
			if (b[2][0] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (b[0][0] == b[1][0] && b[0][0] == b[2][0] && b[0][0] != ' ') {
			if (b[0][0] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (b[0][1] == b[1][1] && b[1][1] == b[2][1] && b[0][1] != ' ') {
			if (b[0][1] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (b[0][2] == b[1][2] && b[0][2] == b[2][2] && b[0][2] != ' ') {
			if (b[0][2] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (b[0][0] == b[1][1] && b[0][0] == b[2][2] && b[0][0] != ' ') {
			if (b[0][0] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (b[2][0] == b[1][1] && b[2][0] == b[0][2] && b[2][0] != ' ') {
			if (b[2][0] == 'X') {
				return 1;
			}
			else {
				return 0;
			}
		}
		if (this.full() == true) {
			return 2;
		}
		return -1;
	}

	/*
	 * minimax func finds the maximum value move the AI
	 * can afford to take while minimizing the value
	 * of the human's move
	 */
	public int minimax(int depth, boolean AIplayer) {
		if (this.checkGameOver() == 1) {
			return 10;
		}
		if (this.checkGameOver() == 0) {
			return -10;
		}
		if (this.checkGameOver() == 2) {
			return 0;
		}

		if (AIplayer == true) {
			int high = -100;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == ' ') {
						board[i][j] = 'X';
						high = Math.max(high, minimax(depth+1, !AIplayer));
						board[i][j] = ' ';
					}
				}
			}
			return high;
		}

		else {
			int low = 100;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == ' ') {
						board[i][j] = 'O';
						low = Math.min(low, minimax(depth+1, !AIplayer));
						board[i][j] = ' ';
					}
				}
			}
			return low;
		}
	}

	/*
	 * bestMove finds the best move possible by comparing the 
	 * potential gains in value of each possible move
	 */
	public Move bestMove() {
		int best = -100;
		Move bestMove = new Move(-1, -1);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					board[i][j] = 'X';
					int value = minimax(0, false);
					board[i][j] = ' ';
					if (value > best) {
						bestMove.row = i;
						bestMove.col = j;
						best = value;
					}
				}
			}
		}
		return bestMove;
	}

	/*
	 * Helper function thats checks is space is open
	 */
	public boolean open(int row, int column) {
		if (board[row][column] == ' ') {
			return true;
		}
		return false;
	}
	
	/* 
	 * Helper function that places human or bot's move
	 */
	public void place(int row, int column, int i) {
		if (i == 0) {
			this.board[row][column] = 'O';
		}
		if (i == 1) {
			this.board[row][column] = 'X';
		}
	}
	
	/*
	 * Helper function thats checks if the board is completely full
	 */
	public boolean full() {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if (board[i][k] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * Prints the game board and its current status
	 */
	public void print() {
		System.out.println("  0   1   2");
		for (int j = 0; j < 3; j++) {
			System.out.print(j + " ");
			for (int i = 0; i < 3; i++) {
				if (i == 2) {
					System.out.print(board[j][i]);
					break;
				}
				System.out.print(board[j][i] + " | ");
			}
			System.out.println();
			if (j != 2) {
				System.out.println("  _________");
			}
		}
	}

	public static void main(String args[]) {
		ttt g = new ttt();
		Scanner sc = new Scanner(System.in);
		g.game(sc);
		sc.close();
	}
	
}
