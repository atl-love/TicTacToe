package main.java.board;

import java.util.ArrayList;

import main.java.ui.UserInterface;

/** Approach: Decided on a singleton that represents the tic tac board, since
 *  only one instance of the board is needed. A class can be used, but I chose
 *  a singleton because I want to ensure only one instance is created to avoid
 *  inconsistent states. Even though in this scenario, there isn't  concern for
 *  concurrency on a singleton, I am designing with the intention that this could
 *  be "production quality." In a production version of this game, managing a 
 *  singleton will be more thoroughly considered. For example: (1) how is the
 *  singleton created? (2) does synchronization need to be managed? (3) can this
 *  class violate 'Single Responsibility Principle'and how to appropriately work
 *  around this (i.e. dependency injection).
 *  
 *   NOTE: The goal of this code is that this approach works for any n*m scenario.
 * 
 * @author ATL
 *
 */
public class TicTacToeBoard {

	public final char X_ICON = UserInterface.HUMAN_ICON;
	public final char O_ICON = UserInterface.ROBOT_ICON;
	public final int BOARD_SIZE_ROW = 3;
	public final int BOARD_SIZE_COL = 3;
	public ArrayList<Integer> availableCells = new ArrayList<Integer>();
	private final int NUM_BOARD_CELLS = BOARD_SIZE_ROW*BOARD_SIZE_COL;//enables working with any size board
	private static TicTacToeBoard game;//Production would have more sophisticated singleton instantiations
	private char[][] board;
	private int cellsPlayed;

	private TicTacToeBoard() {//can be decided @ runtime with dependency injection. would change if reqs changed.

		board = new char[BOARD_SIZE_ROW][BOARD_SIZE_COL];
		cellsPlayed = 0;

		//initialize board to have EMPTY_ICON as default value
		initializeBoard();

	}

	public static synchronized TicTacToeBoard getInstance() {
		if (game == null) {
			game = new TicTacToeBoard();
		}
		return game;
	}

	public void initializeBoard() {
		//initialize board to have EMPTY_ICON as default value
		for(int row = 0; row < BOARD_SIZE_ROW; row++) {
			for(int col = 0; col < BOARD_SIZE_COL; col++ ) {
				board[row][col] = UserInterface.EMPTY_ICON;
			}
		}

		/*Minimizes effort to find cell in case TicTacToe board ever changes. This
		  is a performance based design decision. For ex., if cell '3' and '5' are available
		  out of N possible cells, approach randomly chooses from '3' and '5' instead of
		  waiting until the robot randomly picks the number 3 or 5. Doesn't make a noticeable impact now,
		  but tradeoffs are minimal.*/
		availableCells.clear();
		for(int i=1; i <= NUM_BOARD_CELLS;i++) {
			availableCells.add(i);
		}
	}

	public void clearCellsPlayed() {

		cellsPlayed = 0;

	}
	public char[][] getBoard() {
		return board;
	}

	public boolean setBoard(int cell, char icon) {

		if(availableCells.contains(cell)) {
			board[translateCellInputToRow(cell)][translateCellInputToCol(cell)] = icon;
			availableCells.remove(availableCells.indexOf(cell));
			cellsPlayed++;
			return true;
		}

		return false;
	}

	public boolean setBoardRowCol(int row, int col, char icon) {

		int cell = row*game.BOARD_SIZE_ROW+col+1;
		if(availableCells.contains(cell)) {
			board[row][col] = icon;
			availableCells.remove(availableCells.indexOf(cell));
			cellsPlayed++;
			return true;
		}

		return false;
	}

	public char getItemAtRowCol(int row, int col) {

		if(row >= 0 && row < BOARD_SIZE_ROW && col >=0 && col < BOARD_SIZE_COL) {
			return board[row][col];
		}

		return '\u0000';

	}
	public ArrayList<Integer> getAvailableCells(){
		return availableCells;
	}

	public int getCellsPlayed() {

		return cellsPlayed;

	}

	/** Translates 1-9 input to corresponding row
	 * 
	 * @param playerInput
	 * @return row for cell entered or -1 for not found
	 */
	int translateCellInputToRow(int playerInput) {

		//if not within available cells return -1 for not found
		if(playerInput < 1 || playerInput > NUM_BOARD_CELLS) {
			return -1;
		}

		return (playerInput-1)/BOARD_SIZE_ROW;
	}

	/** Translates 1-9 input to corresponding col
	 * 
	 * @param playerInput
	 * @return col for cell entered or -1 for not found
	 */
	int translateCellInputToCol(int playerInput) {

		//if not within available cells return -1 for not found
		if(playerInput < 1 || playerInput > NUM_BOARD_CELLS) {
			return -1;
		}

		return (playerInput-1)%BOARD_SIZE_COL;
	}

	/** Marks selected cell as used on TicTacToe board
	 * 
	 * @param playerInput cell 1-9
	 * @param playerIcon
	 * @return true if cell is available and can be marked
	 */
	public boolean makeMoveAndMarkCell(int playerInput, char playerIcon) {

		//checks to see if playerInput is between 1-9
		if(playerInput < 1 || playerInput > 9) {
			UserInterface.nonExistentCellMessage();
			return false;
		}

		//translate selected cell ==> [row, col]
		int row = translateCellInputToRow(playerInput);
		int col = translateCellInputToCol(playerInput);
		if(board[row][col] == UserInterface.EMPTY_ICON) {
			cellsPlayed++;
			board[row][col] = playerIcon;
			availableCells.remove(availableCells.indexOf(playerInput));
			return true;
		}

		//if cell has not been marked, then cell is not available
		UserInterface.cellNotAvailableMessage();
		return false;
	}


	/** Display current state of Tic Tac Toe Board
	 * 
	 */
	public void displayBoard() {

		UserInterface.displayBoard(game);

	}		

	/**Determines if a player has won the game by looking to see if there are 3 of the same
	 * character in a row diagonally, horizontally, or vertically. Could have done this in
	 * one call to save whitespace and have o(n^2) solution, but decided on this approach.
	 * 
	 * @return X_ICON for human winner, O_ICON for robot winner, and EMPTY_ICON for no winner
	 */
	public char isWinner() {

		char winner = UserInterface.EMPTY_ICON;
		char rowWinner = isRowWinner();
		char colWinner = isColWinner();
		char diagWinner = isDiagonalWinner();

		//if less than 3 cells selected, there can't be a winner, hence exit
		if(cellsPlayed <3) return UserInterface.EMPTY_ICON;

		//check rows for winner
		winner = rowWinner == UserInterface.EMPTY_ICON ? winner : rowWinner;

		//check cols for winner		
		winner = colWinner == UserInterface.EMPTY_ICON ? winner : colWinner;

		//check diagonals for winner
		winner = diagWinner == UserInterface.EMPTY_ICON ? winner : diagWinner;

		return winner;
	}

	/**Checks to see if there is a winner along rows 0-2
	 * 
	 * @return X_ICON for human winner, O_ICON for robot winner, and EMPTY_ICON for no winner
	 */
	char isRowWinner() {

		//loop through rows and see if there are 3 of the same character
		for(int row = 0; row < BOARD_SIZE_ROW; row++) {

			//if any space has not been used, then there cannot be a winner for that row
			if(game.board[row][0] == UserInterface.EMPTY_ICON || game.board[row][1] == UserInterface.EMPTY_ICON || game.board[row][2] == UserInterface.EMPTY_ICON) {
				continue;
			}

			if(game.board[row][0] == X_ICON && game.board[row][1] == X_ICON && game.board[row][2] == X_ICON) {
				return X_ICON;
			}

			if(game.board[row][0] == O_ICON && game.board[row][1] == O_ICON && game.board[row][2] == O_ICON) {
				return O_ICON;
			}
		}

		return UserInterface.EMPTY_ICON;
	}

	/**Checks to see if there is a winner along cols 0-2
	 * 
	 * @return X_ICON for human winner, O_ICON for robot winner, and EMPTY_ICON for no winner
	 */
	char isColWinner() {

		//loop through rows and see if there are 3 of the same character
		for(int col = 0; col < BOARD_SIZE_COL; col++) {

			//if any space has not been used, then there cannot be a winner for that row
			if(game.board[0][col] == UserInterface.EMPTY_ICON || game.board[1][col] == UserInterface.EMPTY_ICON || game.board[2][col] == UserInterface.EMPTY_ICON) {
				continue;
			}

			if(game.board[0][col] == X_ICON && game.board[1][col] == X_ICON && game.board[2][col] == X_ICON) {
				return X_ICON;
			}

			if(game.board[0][col] == O_ICON && game.board[1][col] == O_ICON && game.board[2][col] == O_ICON) {
				return O_ICON;
			}
		}

		return UserInterface.EMPTY_ICON;
	}

	/**Checks to see if there is a winner along diagonals
	 * 
	 * @return X_ICON for human winner, O_ICON for robot winner, and EMPTY_ICON for no winner
	 */
	char isDiagonalWinner() {

		//check diagonal from left i.e. 0,0 1,1, 2,2
		if(game.board[0][0] == X_ICON && game.board[1][1] == X_ICON && game.board[2][2] == X_ICON) { return X_ICON; }
		if(game.board[0][0] == O_ICON && game.board[1][1] == O_ICON && game.board[2][2] == O_ICON) {	return O_ICON; }

		//check diagonal from right i.e. 0,2 1,1, 2,0
		if(game.board[0][2] == X_ICON && game.board[1][1] == X_ICON && game.board[2][0 ] == X_ICON) { return X_ICON; }
		if(game.board[0][2] == O_ICON && game.board[1][1] == O_ICON && game.board[2][0] == O_ICON) {	return O_ICON; }


		return UserInterface.EMPTY_ICON;
	}

	/** Check to see if all cells have been played and the game is over.
	 *  If the game is over, display a message showing that there is a tie.
	 * 
	 * @return true if no more cells are available
	 */
	public boolean isGameOver() {

		if(cellsPlayed >= NUM_BOARD_CELLS) {
			UserInterface.displayTieMessage();
			return true;
		}

		return false;
	}

	/** Display game rules
	 * 
	 */
	public void showRules() {
		UserInterface.showRules();
	}



}
