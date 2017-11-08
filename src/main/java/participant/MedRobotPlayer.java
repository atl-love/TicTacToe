package main.java.participant;

import java.util.ArrayList;
import java.util.Collections;

import main.java.board.TicTacToeBoard;
import main.java.ui.UserInterface;

public class MedRobotPlayer implements Player {

	@Override
	public boolean makeMove(TicTacToeBoard game) {

		//check if any moves available
		ArrayList<Integer> availableCells = game.getAvailableCells();		
		if(availableCells.size()== 0) {
			return false;
		}
		
		int blockingMove = smartMove(game);//-1 if no blocking move
		if(blockingMove == -1) {

			//shuffle available cells
			Collections.shuffle(availableCells);

			//make move
			game.makeMoveAndMarkCell(availableCells.get(0), UserInterface.ROBOT_ICON);
		} else {

			game.makeMoveAndMarkCell(blockingMove, UserInterface.ROBOT_ICON);
		}

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

	/** Helper that checks for a match for the given inputs. The logic for checking is to hold one
	 *  value constant, either row, columng, or diag, and then check the corresponding neighbor cells.
	 *  For example check row win by:
	 *  Iteration 0: row = 0, col=[0:BOARD_SIZE_COL].
	 *  		- If 2 of the same icon are found and one empty space is found, return icon, else return empty space
	 *  Iteration 1: row = 1, col=[0:BOARD_SIZE_COL]. ""
	 *  Iteration N: row = N, col=[0:BOARD_SIZE_COL]. ""
	 * @param game
	 * @param row
	 * @param col
	 * @param rowDelta
	 * @param colDelta
	 * @return
	 */
	int smartMoveHelper(TicTacToeBoard game, int row, int col, int rowDelta, int colDelta) {

		int countX = 0, countO = 0;//var to count the num of Xs and Os in a row, col, or diag
		int almostWin = 2;//when two of X_ICON or O_ICON == 2, then a blocking move can be made
		int blockingSpace = -1;
		char currentChar;

		for (int i = 0; i < game.BOARD_SIZE_ROW;  i++) {
			currentChar = game.getItemAtRowCol(row, col);

			if (currentChar != UserInterface.EMPTY_ICON) {

				//increment X or O accordingly
				if(currentChar == UserInterface.ROBOT_ICON) {
					countO++;
				}else {
					countX++;
				}
				row += rowDelta; col+= colDelta;
				continue;
			}
			else {
					blockingSpace = row*game.BOARD_SIZE_ROW+col+1;//calculation to translate coordinates into cell #
					row = row+ rowDelta >= 0 && row+rowDelta < game.BOARD_SIZE_ROW ? row+rowDelta : row;
					col = col+ colDelta >= 0 && col+colDelta < game.BOARD_SIZE_ROW ? col+colDelta : col;
				
			}
		}

		//if there are exactly two in a row, return the empty space to block or make winning move
		if(countX == almostWin || countO ==almostWin)
			return blockingSpace;

		return -1;
	}

	/** Check to see if user has two along any row, col, or diagonal in order
	 *  to block the win
	 * @param game
	 * @return position to play if a win can be blocked, if nothing to block
	 */
	int smartMove(TicTacToeBoard game)
	{		

		int blockingCell = -1;
		int almostWin;

		//check for two along row
		for (int i = 0 ; i < game.BOARD_SIZE_COL; i++)
		{
			almostWin = smartMoveHelper(game, 0, i, 1, 0); 
			blockingCell = almostWin == -1 ? blockingCell: almostWin;
		}

		//check for two along row
		for (int i = 0 ; i < game.BOARD_SIZE_ROW && blockingCell == -1; i++)
		{
			almostWin = smartMoveHelper(game, i, 0, 0, 1); 
			blockingCell = almostWin == -1 ? blockingCell: almostWin;
		}

		//check for two along left diag
		almostWin = smartMoveHelper(game, 0, 0, 1, 1); 
		blockingCell = almostWin == -1 ? blockingCell: almostWin;


		//check for two along right diag
		almostWin = smartMoveHelper(game, 0, game.BOARD_SIZE_ROW-1, 1, -1); 
		blockingCell = almostWin == -1 ? blockingCell: almostWin;


		return blockingCell;
	}
}
