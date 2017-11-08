package main.java.participant;

import java.util.ArrayList;
import java.util.Collections;
import main.java.board.TicTacToeBoard;
import main.java.ui.UserInterface;

public class EasyRobotPlayer implements Player{
	
	@Override
	public boolean makeMove(TicTacToeBoard game) {
		
		//check if moves are available
		ArrayList<Integer> availableCells = game.getAvailableCells();
		if(availableCells.size() == 0) {
			return false;
		}
		
		//shuffle available cells
		Collections.shuffle(availableCells); 
		
		//make move
		game.makeMoveAndMarkCell(availableCells.get(0), UserInterface.ROBOT_ICON);
		
		//show updated board and close stream
		UserInterface.robotMovedMessage();
		game.displayBoard();	
		return true;
	}
	
 	
	@Override
	public void congratulatePlayer() {
		UserInterface.congratulateRobot();
	}

	@Override
	public char getPlayerIcon() {
		return UserInterface.ROBOT_ICON;
	}	

}
