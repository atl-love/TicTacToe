package main.java.participant;

import main.java.board.TicTacToeBoard;
import main.java.ui.UserInterface;

/**TODO 
 * This robot has the following algorithms:
 * (1) A table that logs the user's inputs, which weights the randomized selection
 * 		- only important for repeated play option
 * (2) A check to see if the opposing player is one away from a win to block the win
 * (3) Ensure that each robot move has at least 1+ robot icon along a winnable path
 * 
 * @author ATL
 *
 */
public class AdvRobotPlayer implements Player {

	@Override
	public boolean makeMove(TicTacToeBoard game) {
		// TODO Auto-generated method stub
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
