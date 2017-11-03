package main.java.participant;

import java.util.NoSuchElementException;
import java.util.Scanner;

import main.java.board.TicTacToeBoard;

public class HumanPlayer implements Player {

	private final char HUMAN_ICON = 'X';
	private static Scanner input = new Scanner(System.in);


	@Override
	public void makeMove(TicTacToeBoard game) {

		//initialize variables
		boolean isValidInput = false;
		System.out.print("Make your move! Select an unused cell from 1-9: ");

		//get valid input from user
		while(!isValidInput) {
			String playerInput = input.next();

			//if input is not a number between 1-9, get new input from user
			try{
				isValidInput = game.isCellAvailable(Integer.parseInt(playerInput), getPlayerIcon());
				input.nextLine();

				if(!isValidInput) {
					System.out.print("Make sure that cell is unused AND the number is from 1-9: ");
				}

			}catch(NumberFormatException | NoSuchElementException e) {
				System.out.print("Woahh, that value is not an integer between 1 and 9! Try again:");//Handle error here
			}

		}

		//show updated board and close stream
		System.out.println("\nGood move! Here's the new board:");
		game.displayBoard();
	}

	@Override
	public void congratulatePlayer() {
		System.out.println(""
				+ "\n\n\n`8.`8888.      ,8'  ,o888888o.     8 8888      88           `8.`888b                 ,8'  ,o888888o.     b.             8\r\n" + 
				" `8.`8888.    ,8'. 8888     `88.   8 8888      88            `8.`888b               ,8'. 8888     `88.   888o.          8\r\n" + 
				"  `8.`8888.  ,8',8 8888       `8b  8 8888      88             `8.`888b             ,8',8 8888       `8b  Y88888o.       8\r\n" + 
				"   `8.`8888.,8' 88 8888        `8b 8 8888      88              `8.`888b     .b    ,8' 88 8888        `8b .`Y888888o.    8\r\n" + 
				"    `8.`88888'  88 8888         88 8 8888      88               `8.`888b    88b  ,8'  88 8888         88 8o. `Y888888o. 8\r\n" + 
				"     `8. 8888   88 8888         88 8 8888      88                `8.`888b .`888b,8'   88 8888         88 8`Y8o. `Y88888o8\r\n" + 
				"      `8 8888   88 8888        ,8P 8 8888      88                 `8.`888b8.`8888'    88 8888        ,8P 8   `Y8o. `Y8888\r\n" + 
				"       8 8888   `8 8888       ,8P  ` 8888     ,8P                  `8.`888`8.`88'     `8 8888       ,8P  8      `Y8o. `Y8\r\n" + 
				"       8 8888    ` 8888     ,88'     8888   ,d8P                    `8.`8' `8,`'       ` 8888     ,88'   8         `Y8o.`\r\n" + 
				"       8 8888       `8888888P'        `Y88888P'                      `8.`   `8'           `8888888P'     8            `Yo");
		System.out.println("Congratulations! You're smarter than a robot!");
	}

	@Override
	public char getPlayerIcon() {
		return HUMAN_ICON;
	}

	/** Gets level of play desired from user, where input is 'easy' or 'medium.'
	 * 
	 */
	public static PlayerType getLevelOfPlay() {

		boolean isValidInput = false;
		PlayerType level = PlayerType.EASYROBOT;

		while(!isValidInput) {
			System.out.print("Please select a level 'Easy' or 'Medium':");
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
				System.out.print("Hmm, I don't understand. Please select 'Easy' or 'Medium': ");
			}
		}

		return level;
	}
}
