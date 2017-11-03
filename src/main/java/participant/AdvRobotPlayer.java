package main.java.participant;

import main.java.board.TicTacToeBoard;

/** This robot has the following algorithms:
 * (1) A table that logs the user's inputs, which weights the randomized selection
 * 		- only important for repeated play option
 * (2) A check to see if the opposing player is one away from a win to block the win
 * (3) Ensure that each robot move has at least 1+ robot icon along a winnable path
 * 
 * @author ATL
 *
 */
public class AdvRobotPlayer implements Player {

	private static char ROBOT_ICON = 'O';

	@Override
	public void makeMove(TicTacToeBoard game) {
		// TODO Auto-generated method stub
		
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
