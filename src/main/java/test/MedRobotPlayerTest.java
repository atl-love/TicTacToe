package main.java.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import main.java.board.TicTacToeBoard;
import main.java.participant.Player;
import main.java.participant.PlayerFactory;

public class MedRobotPlayerTest {
	private static TicTacToeBoard game = TicTacToeBoard.getInstance();
	private PlayerFactory playerFactory = new PlayerFactory();
	private Player robot = playerFactory.getPlayer(Player.PlayerType.MEDROBOT);
	
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
	public void testMakeMoveWithAvailableSpace() {
		assertTrue(robot.makeMove(game));
	}
	
	@Test
	public void testMakeMoveNotAvailable() {
		
		//make all cells unavailable
		assertTrue(game.setBoard(1, robot.getPlayerIcon()));
		assertTrue(game.setBoard(2, robot.getPlayerIcon()));
		assertTrue(game.setBoard(3, robot.getPlayerIcon()));
		assertTrue(game.setBoard(4, robot.getPlayerIcon()));
		assertTrue(game.setBoard(5, robot.getPlayerIcon()));
		assertTrue(game.setBoard(6, robot.getPlayerIcon()));
		assertTrue(game.setBoard(7, robot.getPlayerIcon()));
		assertTrue(game.setBoard(8, robot.getPlayerIcon()));
		assertTrue(game.setBoard(9, robot.getPlayerIcon()));
		
		//verify that a move can't be made
		assertFalse(robot.makeMove(game));
	}
	
	@Test
	public void testMakeMoveBlocksWin() {
		
		assertTrue(game.setBoard(1, 'X'));
		assertTrue(game.setBoard(2, 'X'));
		assertTrue(robot.makeMove(game));
		assertEquals(robot.getPlayerIcon(),game.getItemAtRowCol(0, 2));
	}
	
	@Test
	public void testMakeMoveMakesWin() {
		assertTrue(game.setBoard(1, robot.getPlayerIcon()));
		assertTrue(game.setBoard(2, robot.getPlayerIcon()));
		assertTrue(robot.makeMove(game));
		assertEquals(robot.getPlayerIcon(),game.isWinner());
	}

	@Test
	public void testGetPlayerIcon() {
		
		assertEquals('O',robot.getPlayerIcon());
	}


}
