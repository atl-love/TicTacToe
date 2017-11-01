package main.java.demo;

import java.util.Arrays;

import main.java.board.TicTacToeBoard;
import main.java.participant.Player;
import main.java.participant.PlayerFactory;

public class TicTacToeDemo {

	public static void main(String[] args) {


		// instantiating Tic Tac Board Singleton
		TicTacToeBoard game = TicTacToeBoard.getInstance();

		//create players
		PlayerFactory playerFactory = new PlayerFactory();
		Player human = playerFactory.getPlayer(Player.PlayerType.HUMAN);
		Player robot = playerFactory.getPlayer(Player.PlayerType.EASYROBOT);

		//display rules
		game.showRules();

		//play game
		//while(!game.isGameOver()) {

			//get player1(human) move and check if winner
			human.makeMove(game);
			/*if(game.isWinner() == human.getPlayerIcon()) {
				game.displayWinner(human.getPlayerIcon());
				break;
			}*/

			/*//get player2(robot) move and check if winner
			robot.makeMove(game);
			if(game.isWinner() == robot.getPlayerIcon()) {
				game.displayWinner(robot.getPlayerIcon());
				break;
			}
*/
		//}
		
		System.out.println("Thanks for playing!"); 
		/*
		 * 
		 * 		game.board[1][1] = 'X';

		System.out.println("Tic Tac Toe Board: " + Arrays.deepToString(game.board));
		System.out.println("Tic Tac Toe Board (display): ");
		game.displayBoard();


		//get an object of Circle and call its draw method.
		Player player1 = playerFactory.getPlayer(Player.PlayerType.HUMAN);
		//game.isCellAvailable(player1.makeMove(), player1.getPlayerIcon());

		//call draw method of Circle
		//player1.draw();

		//get an object of Rectangle and call its draw method.
		Player player2 = playerFactory.getPlayer(Player.PlayerType.EASYROBOT);

		//call draw method of Rectangle
		//player2.draw();

		//get an object of Square and call its draw method.
		Player player3 = playerFactory.getPlayer(Player.PlayerType.MEDROBOT);

		//call draw method of circle
		//player3.draw();
		 */	}

}
