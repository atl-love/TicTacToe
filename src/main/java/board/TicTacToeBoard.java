package main.java.board;

import java.util.ArrayList;

/** A singleton that represents the tic tac board.
 * 
 * @author ATL
 *
 */
public class TicTacToeBoard {

	private final int NUM_BOARD_CELLS = 9;
	private static TicTacToeBoard game = new TicTacToeBoard();
	public ArrayList<Integer> availableCells = new ArrayList<Integer>();
	public char[][] board;
	public final int BOARD_SIZE_ROW = 3;
	public final int BOARD_SIZE_COL = 3;
	private int cellsPlayed;

	private TicTacToeBoard() {
		board = new char[BOARD_SIZE_ROW][BOARD_SIZE_COL];
		cellsPlayed = 0;

		//initialize board to have '-' default value
		for(int row = 0; row < BOARD_SIZE_ROW; row++) {
			for(int col = 0; col < BOARD_SIZE_COL; col++ ) {
				board[row][col] = '-';
			}
		}
		
		//initialize available cells to all possible
		for(int i=1; i < NUM_BOARD_CELLS+1;i++) {
			availableCells.add(i);
		}
	}

	public static TicTacToeBoard getInstance()
	{
		return game;
	}

	/** Verifies that cell input is between 1-9 and cell is available.
	 * 
	 * @return true if the user entered a valid cell or false if invalid input was received
	 */
	public boolean isCellAvailable(int playerInput, char playerIcon) {

		//checks to see if playerInput is between 1-9
		if(playerInput < 1 || playerInput > 9) return false;

		//translate cell into row and column then check that cell is available
		int row = (playerInput-1)/3;
		int col = (playerInput-1)%3;
		
		//if cell is available increment cellsPlayed and remove from available list
		if(board[row][col] == '-') {
			cellsPlayed++;
			board[row][col] = playerIcon;
			availableCells.remove(availableCells.indexOf(playerInput));
			return true;
		}

		return false;
	}


	/** Display current state of Tic Tac Toe Board
	 * 
	 */
	public void displayBoard() {

		for(int row = 0; row < BOARD_SIZE_ROW; row++) {
			System.out.print("\t\t|");
			for(int col = 0; col < BOARD_SIZE_COL; col++) {
				System.out.print(board[row][col]+ "|");
			}
			System.out.println();
		}
		
		System.out.println();

	}		

	/**Determines if a player has won the game by looking to see if there are 3 of the same
	 * character in a row diagonally, horizontally, or vertically.
	 * 
	 * @return 'X' for human winner, 'O' for robot winner, and '-' for no winner
	 */
	public char isWinner() {
		
		char winner = '-';
		char rowWinner = isRowWinner();
		char colWinner = isColWinner();
		char diagWinner = isDiagonalWinner();
		
		//if less than 3 cells selected, there can't be a winner, hence exit
		if(cellsPlayed <3) return '-';

		//check rows for winner
		winner = rowWinner == '-' ? winner : rowWinner;
		
		//check cols for winner		
		winner = colWinner == '-' ? winner : colWinner;

		//check diagonals for winner
		winner = diagWinner == '-' ? winner : diagWinner;

		return winner;
	}

	/**Checks to see if there is a winner along rows 0-2
	 * 
	 * @return 'X' for human winner, 'O' for robot winner, and '-' for no winner
	 */
	char isRowWinner() {

		//loop through rows and see if there are 3 of the same character
		for(int row = 0; row < BOARD_SIZE_ROW; row++) {

			//if any space has not been used ('-'), then there cannot be a winner for that row
			if(game.board[row][0] == '-' || game.board[row][1] == '-' || game.board[row][2] == '-') {
				continue;
			}

			if(game.board[row][0] == 'X' && game.board[row][1] == 'X' && game.board[row][2] == 'X') {
				return 'X';
			}

			if(game.board[row][0] == 'O' && game.board[row][1] == 'O' && game.board[row][2] == 'O') {
				return 'O';
			}
		}

		return '-';
	}

	/**Checks to see if there is a winner along cols 0-2
	 * 
	 * @return 'X' for human winner, 'O' for robot winner, and '-' for no winner
	 */
	char isColWinner() {

		//loop through rows and see if there are 3 of the same character
		for(int col = 0; col < BOARD_SIZE_COL; col++) {

			//if any space has not been used ('-'), then there cannot be a winner for that row
			if(game.board[0][col] == '-' || game.board[1][col] == '-' || game.board[2][col] == '-') {
				continue;
			}

			if(game.board[0][col] == 'X' && game.board[1][col] == 'X' && game.board[2][col] == 'X') {
				return 'X';
			}

			if(game.board[0][col] == 'O' && game.board[1][col] == 'O' && game.board[2][col] == 'O') {
				return 'O';
			}
		}

		return '-';
	}

	/**Checks to see if there is a winner along diagonals
	 * 
	 * @return 'X' for human winner, 'O' for robot winner, and '-' for no winner
	 */
	char isDiagonalWinner() {

		//check diagonal from left i.e. 0,0 1,1, 2,2
		if(game.board[0][0] == 'X' && game.board[1][1] == 'X' && game.board[2][2] == 'X') { return 'X'; }
		if(game.board[0][0] == 'O' && game.board[1][1] == 'O' && game.board[2][2] == 'O') {	return 'O'; }

		//check diagonal from right i.e. 0,2 1,1, 2,0
		if(game.board[0][2] == 'X' && game.board[1][1] == 'X' && game.board[2][0 ] == 'X') { return 'X'; }
		if(game.board[0][2] == 'O' && game.board[1][1] == 'O' && game.board[2][0] == 'O') {	return 'O'; }


		return '-';
	}

	/** Check to see if all cells have been played and the game is over.
	 *  If the game is over, display a message showing that there is a tie.
	 * 
	 * @return true if no more cells are available
	 */
	public boolean isGameOver() {
		
		if(cellsPlayed >= NUM_BOARD_CELLS) {
			System.out.println("It's a tie! Robots and humans live in harmony.");
			return true;
		}
		
		return false;
	}

	/** Display game rules
	 * 
	 */
	public void showRules() {

		System.out.println("\r\n" + 
				".------..------..------.    .------..------..------.    .------..------..------.\r\n" + 
				"|T.--. ||I.--. ||C.--. |    |T.--. ||A.--. ||C.--. |    |T.--. ||O.--. ||E.--. |\r\n" + 
				"|      ||      ||     ||    |      ||      ||     ||    |      ||      ||     ||\r\n" + 
				"|  T   ||  I   || C   ||    |  T   ||  A   ||  C  ||    |  T   ||   O  ||  E  ||\r\n" + 
				"|      ||      ||     ||    |      ||      ||     ||    |      ||      ||     ||\r\n" + 
				"| '--'T|| '--'I|| '--'C|    | '--'T|| '--'A|| '--'C|    | '--'T|| '--'O|| '--'E|\r\n" + 
				"`------'`------'`------'    `------'`------'`------'    `------'`------'`------'\r\n" + 
				"");
		System.out.println("How to Play:\n"
				+ "    Welcome to TicTac Toe. Try to get 3 in a row! Enter 1-9 to select a cell.\n"
				+ "    First row is 1-3, second row is 4-6, and lasty row is 7-9. You're 'X' and\n"
				+ "    the robot is 'O'. Good luck!\n");
	}



}
