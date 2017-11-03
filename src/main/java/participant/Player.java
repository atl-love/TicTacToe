package main.java.participant;

import main.java.board.TicTacToeBoard;

public interface Player {
	
	/** Gets a player's move as a numerical input 1-9. For Human player this will be input from
	 *  the console; for robots, this shall be a randomly generated number.
	 */
	void makeMove(TicTacToeBoard game);
	
	/** Print a congratulation message to the player for winning.
	 * 
	 */
	void congratulatePlayer();
	
	/** Returns icon for player. Either 'X' or 'O.' This function can be modified later so that
	 *  a user can select or create their own icon.
	 */
	char getPlayerIcon();
	
	/**
	 *  Different player options. Human is the default Player 1. This code is flexible
	 *  in case we want to change the defaults or order of play (i.e. Two Human Players, Human vs Med Robot).
	 *  <li>{@link #EASYROBOT}</li>
	 *  <li>{@link #MEDROBOT}</li>
	 *  <li>{@link #ADVROBOT}</li>
	 */
	enum PlayerType {
		HUMAN,
		EASYROBOT, /** A dumb robot with no strategy*/
		MEDROBOT,  /** A slightly smarter robot with some strategy*/
		ADVROBOT,  /** TODO The ultra-smart-bot! */

	}

}
