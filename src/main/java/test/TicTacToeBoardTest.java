package main.java.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import main.java.board.TicTacToeBoard;
import main.java.ui.UserInterface;

public class TicTacToeBoardTest {

	private static TicTacToeBoard game = TicTacToeBoard.getInstance();

	@After
	public void tearDown() throws Exception {

		//initialize board to empty
		//initialize board to have EMPTY_ICON as default value
		game.initializeBoard();

		//initialize available cells
		game.availableCells.clear();
		for(int i=1; i <= game.BOARD_SIZE_COL*game.BOARD_SIZE_ROW;i++) {
			game.availableCells.add(i);
		}

		//reset cells played to 0
		game.clearCellsPlayed();
	}

	@Test
	public void testBoardCreationIsSetToEmptyIcon(){

		//test board set to EMPTY_ICON as default value
		for(int row = 0; row < game.BOARD_SIZE_ROW; row++) {
			for(int col = 0; col < game.BOARD_SIZE_COL; col++ ) {
				assertEquals(game.getItemAtRowCol(row, col),UserInterface.EMPTY_ICON);
			}
		}
	}

	@Test
	public void testMakeMoveAndMarkAvailableCell() {

		int selectedCell = 1;
		char playerIcon = 'X';
		assertTrue(game.makeMoveAndMarkCell(selectedCell, playerIcon));
	}

	@Test
	public void testAvailableCellsAreUpdatedAfterMakingMove() {

		int selectedCell = 1;
		char playerIcon = 'X';

		//verify that cell 1 is available
		assertTrue(game.getAvailableCells().contains(1));

		//verify that 1 cell is no longer listed in availableCells
		assertTrue(game.makeMoveAndMarkCell(selectedCell, playerIcon));
		//assertFalse(game.availableCells.contains(1));
	}

	@Test
	public void testMakeMoveAndMarkUnavailableCell() {

		TicTacToeBoard game = TicTacToeBoard.getInstance();

		//mark board cell
		char otherPlayerIcon = 'O';
		assertTrue(game.setBoardRowCol(0, 0, otherPlayerIcon));

		//verify that marked cell cannot be marked
		int selectedCell = 1;
		char playerIcon = 'X';
		assertFalse(game.makeMoveAndMarkCell(selectedCell, playerIcon));
	}

	@Test
	public void testIsWinnerAlongRowUsingSetBoard() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoard(1, winnerIcon));
		assertTrue(game.setBoard(2, winnerIcon));
		assertTrue(game.setBoard(3, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}

	@Test
	public void testIsWinnerAlongRowUsingSetBoardRolCol() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoardRowCol(0, 0, winnerIcon));
		assertTrue(game.setBoardRowCol(0, 1, winnerIcon));
		assertTrue(game.setBoardRowCol(0, 2, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}

	@Test
	public void testIsWinnerAlongColumn() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoard(1, winnerIcon));
		assertTrue(game.setBoard(4, winnerIcon));
		assertTrue(game.setBoard(7, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}
	
	@Test
	public void testIsWinnerAlongColUsingSetBoardRolCol() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoardRowCol(0, 0, winnerIcon));
		assertTrue(game.setBoardRowCol(1, 0, winnerIcon));
		assertTrue(game.setBoardRowCol(2, 0, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}

	@Test
	public void testIsWinnerAlongLeftDiagonal() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoard(1, winnerIcon));
		assertTrue(game.setBoard(5, winnerIcon));
		assertTrue(game.setBoard(9, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}
	
	@Test
	public void testIsWinnerAlongLeftDiagonalUsingSetBoardRolCol() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoardRowCol(0, 0, winnerIcon));
		assertTrue(game.setBoardRowCol(1, 1, winnerIcon));
		assertTrue(game.setBoardRowCol(2, 2, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}

	@Test
	public void testIsWinnerAlongRightDiagonal() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoard(3, winnerIcon));
		assertTrue(game.setBoard(5, winnerIcon));
		assertTrue(game.setBoard(7, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}
	
	@Test
	public void testIsWinnerAlongRightDiagonalUsingSetBoardRolCol() {

		//create winner on tictacoe board
		char winnerIcon = 'X';

		//set 3 in a row
		assertTrue(game.setBoardRowCol(0, 2, winnerIcon));
		assertTrue(game.setBoardRowCol(1, 1, winnerIcon));
		assertTrue(game.setBoardRowCol(2, 0, winnerIcon));

		//check that winner == winnerIcon
		assertEquals(winnerIcon, game.isWinner());

	}

	@Test
	public void testGameOver() {

		TicTacToeBoard game = TicTacToeBoard.getInstance();

		//make BOARD_SIZE_COL*BOARD_SIZE_ROW moves and verify that the game is over and no new moves can be made
		char playerIcon = 'X';
		int playerInput = 1;
		int boardSize = game.BOARD_SIZE_COL*game.BOARD_SIZE_ROW;		
		while(playerInput <= boardSize) {
			game.makeMoveAndMarkCell(playerInput++, playerIcon);
		}
		assertTrue(game.isGameOver());
		assertFalse(game.makeMoveAndMarkCell(playerInput, playerIcon));
	}

	@Test
	public void testGameNotOver() {

		TicTacToeBoard game = TicTacToeBoard.getInstance();
		assertFalse(game.isGameOver());
	}

}
