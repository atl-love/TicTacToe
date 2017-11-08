package main.java.ui;

import main.java.board.TicTacToeBoard;

/** Contains all of the input to display to the screen. This is good in case we want to:
 * (1) Support internationalization (i.e. have a table in the future to map to other languages
 * (2) Want to swap a graphic or some other functionality and we know the intended action
 * (3) Have cleaner code and have a central location for updating+= maintaining all output
 * @author Ashley
 *
 */
public class UserInterface {
	
	public static char ROBOT_ICON = 'O';
	public static char HUMAN_ICON = 'X';
	public static char EMPTY_ICON = '-';
	
	/** Display game rules
	 * 
	 */
	public static void showRules() {

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
	
	public static void displayTieMessage() {
		System.out.println("It's a tie! Robots and humans live in harmony.");
	}
	
	public static void nonExistentCellMessage(){
		System.out.print("Hmm, the cell you selected doesn't exist. ");
	}
	
	public static void cellNotAvailableMessage(){
		System.out.print("That cell isn't available. ");
	}
	
	public static void cellInputNotWithinRange() {
		System.out.print("Woahh, that value is not an integer between 1 and 9! Try again:");	
	}
	
	public static void displayBoard(TicTacToeBoard game) {

		for(int row = 0; row < game.BOARD_SIZE_ROW; row++) {
			System.out.print("\t\t|");
			for(int col = 0; col < game.BOARD_SIZE_COL; col++) {
				System.out.print(game.getItemAtRowCol(row, col)+ "|");
			}
			System.out.println();
		}

		System.out.println();

	}
	
	public static void tellUserValidMoveEnteredMessage() {
		System.out.println("\nGood move! Here's the new board:");
	}
	public static void thankUserForPlayingMessage(){
		System.out.println("Thanks for playing!"); 
	}
	
	public static void congratulateHuman() {
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
	public static void congratulateRobot() {
		System.out.println("Sorry, you lost! Robots are taking over the world.");
	}
	
	public static void robotMovedMessage() {
		System.out.println("\nRobot just moved:");
	}
	
	public static void getLevelFromUserMessage() {
		System.out.print("Please select a level 'Easy' or 'Medium':");
	}
	
	public static void invalidLevelFromUserMessage() {
		System.out.print("Hmm, I don't understand. Please select 'Easy' or 'Medium': ");
	}
	
	public static void requestMoveFromUser() {
		System.out.print("Make your move! Select an unused cell from 1-9: ");
	}
	
	public static void askUserToRetrySelectingCell() {
		System.out.print("Try again: ");
	}
	
	

}
