package main.java.participant;

import java.util.Collections;

import main.java.board.TicTacToeBoard;

public class MedRobotPlayer implements Player {

	private static char ROBOT_ICON = 'O';

	@Override
	public void makeMove(TicTacToeBoard game) {

		int blockingMove = blockWin(game);//-1 if no blocking move
		if(blockingMove == -1) {

			//shuffle available cells
			Collections.shuffle(game.availableCells);

			//make move
			game.isCellAvailable(game.availableCells.get(0), ROBOT_ICON);
		} else {

			game.isCellAvailable(blockingMove, ROBOT_ICON);

		}

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

	int blockWinHelper(TicTacToeBoard game, int xpos, int ypos, int xdelta, int ydelta) {

		//var to see hold if there are two
		int countX = 0, countO = 0;
		int blockingSpace = -1;
		char currentChar;

		for (int i = 0; i < game.BOARD_SIZE_ROW;  i++) {
			currentChar = game.board[xpos][ypos];

			if (currentChar != '-') {

				//increment X or O accordingly
				if(currentChar == ROBOT_ICON) {
					countO++;
				}else {
					countX++;
				}
				xpos += xdelta; ypos+= ydelta;
				continue;
			}
			else {
					blockingSpace = xpos*3+ypos+1;//calculation to translate coordinates into cell #
					xpos = xpos+ xdelta >= 0 && xpos+xdelta < game.BOARD_SIZE_ROW ? xpos+xdelta : xpos;
					ypos = ypos+ ydelta >= 0 && ypos+ydelta < game.BOARD_SIZE_ROW ? ypos+ydelta : ypos;
				
			}
		}

		if(countX == 2 || countO ==2)
			return blockingSpace;//calculation to translate blocking space into cell

		return -1;
	}

	/** Check to see if user has two along any row, col, or diagonal in order
	 *  to block the win
	 * @param game
	 * @return position to play if a win can be blocked, if nothing to block
	 */
	int blockWin(TicTacToeBoard game)
	{		
		//var to translate coordinates into single digit from [x,y]
		//if two in a row has been found
		int cell = -1;
		int almostWin;

		//check for two along row
		for (int i = 0 ; i < game.BOARD_SIZE_COL; i++)
		{
			almostWin = blockWinHelper(game, 0, i, 1, 0); 
			cell = almostWin == -1 ? cell: almostWin;
		}

		//check for two along row
		for (int i = 0 ; i < game.BOARD_SIZE_ROW && cell == -1; i++)
		{
			almostWin = blockWinHelper(game, i, 0, 0, 1); 
			cell = almostWin == -1 ? cell: almostWin;
		}

		//check for two along left diag
		almostWin = blockWinHelper(game, 0, 0, 1, 1); 
		cell = almostWin == -1 ? cell: almostWin;


		//check for two along right diag
		almostWin = blockWinHelper(game, 0, game.BOARD_SIZE_ROW-1, 1, -1); 
		cell = almostWin == -1 ? cell: almostWin;


		return cell;
	}
}
