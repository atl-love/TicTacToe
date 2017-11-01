package main.java.participant;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.board.TicTacToeBoard;

public class HumanPlayer implements Player {

	@Override
	public void makeMove(TicTacToeBoard game) {

		//initialize variables


		//get valid input from user
		int playerInput = getUserInput();


		//getMove.nextLine();
		//System.out.println("Woahh, that value is not an integer! Try again by entering a number.");
		//} while(!getMove.hasNextInt() && game.isCellAvailable(playerInput, getPlayerIcon()));

		//check that input is valid




	}

	/** Get valid input from human
	 * 
	 * @return Cell from tic tac board ranging from 1-9
	 */
	int getUserInput() {

		Scanner getMove = new Scanner(System.in);
		System.out.println("Make your move! Select an unused cell from 1-9: ");
		boolean isValidInput = false;
		int playerInput = -1;


		while (!getMove.hasNextInt() && !isValidInput ){
			getMove.next();

			if(getMove.hasNextInt()) {
				playerInput = getMove.nextInt();
				if(playerInput < 1 && playerInput > 9) {
					continue;
				}
			}
			System.out.print("Woahh, that value is not an integer between 1 and 9! Try again:");
		}

		//close stream
		getMove.close();

		return playerInput;
	}

	/** Verifies that cell input is between 1-9 and cell is available.
	 * 
	 * @return true if the user entered a valid cell or false if invalid input was received
	 */
	boolean isValidInput(int playerInput, int[][] board) {

		//checks to see if playerInput is between 1-9
		if(playerInput < 1 || playerInput > 9) return false;

		//translate cell into row and column then check that cell is available
		int row = playerInput<4 ? 0: (playerInput>6? 2: 1);
		int col = playerInput<4 ? playerInput-1:(playerInput>6? playerInput-7: playerInput-4);
		if(board[row][col] == '-') return false;

		return true;
	}

	@Override
	public char getPlayerIcon() {
		return 'X';
	}

}
