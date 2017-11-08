package main.java.participant;

import java.util.NoSuchElementException;
import java.util.Scanner;

import main.java.board.TicTacToeBoard;
import main.java.ui.UserInterface;

public class HumanPlayer implements Player {

	private static Scanner input = new Scanner(System.in);

	@Override
	public boolean makeMove(TicTacToeBoard game) {

		//initialize variables
		boolean isValidInput = false;
		UserInterface.requestMoveFromUser();

		//get valid input from user
		while(!isValidInput) {
			String playerInput = input.next();

			//if input is not a number between 1-9, get new input from user
			try{
				isValidInput = game.makeMoveAndMarkCell(Integer.parseInt(playerInput), getPlayerIcon());
				input.nextLine();

				if(!isValidInput) {
					UserInterface.askUserToRetrySelectingCell();
				}

			}catch(NumberFormatException | NoSuchElementException e) {
				UserInterface.cellInputNotWithinRange();
			}

		}

		//show updated board and close stream
		UserInterface.tellUserValidMoveEnteredMessage();
		game.displayBoard();
		return isValidInput;
	}

	@Override
	public void congratulatePlayer() {
		UserInterface.congratulateHuman();
	}

	@Override
	public char getPlayerIcon() {
		return UserInterface.HUMAN_ICON;
	}

	/** Gets level of play desired from user, where input is 'easy' or 'medium.'
	 * 
	 */
	public static PlayerType getLevelOfPlay() {

		boolean isValidInput = false;
		PlayerType level = PlayerType.EASYROBOT;

		//get level of play from user
		UserInterface.getLevelFromUserMessage();
		
		//make sure user input is valid
		while(!isValidInput) {
			String robotLevel = input.nextLine();

			//if input is not 1 or 2, get new input from user
			switch(robotLevel.toUpperCase()) {
			case "EASY": {
				level = PlayerType.EASYROBOT; 
				isValidInput = true; 
				break;
			}
			case "MEDIUM": {
				level = PlayerType.MEDROBOT; 
				isValidInput = true; 
				break;
			}
			default: 
				UserInterface.invalidLevelFromUserMessage();
			}
		}

		return level;
	}
}
