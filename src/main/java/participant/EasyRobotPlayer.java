package main.java.participant;

import java.util.Collections;
import main.java.board.TicTacToeBoard;

public class EasyRobotPlayer implements Player{
	
	private static char ROBOT_ICON = 'O';

	@Override
	public void makeMove(TicTacToeBoard game) {
		
		//shuffle available cells
		Collections.shuffle(game.availableCells);
		
		//make move
		game.isCellAvailable(game.availableCells.get(0), ROBOT_ICON);
		
		//show updated board and close stream
		System.out.println("\nRobot just moved:");
		game.displayBoard();		
	}
	
 	
	@Override
	public void congratulatePlayer() {
		System.out.println("Sorry, you lost! Robots are taking over the world.");
	}

	@Override
	public char getPlayerIcon() {
		return ROBOT_ICON;
	}	

}
