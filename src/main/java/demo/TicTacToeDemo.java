package main.java.demo;

import main.java.board.TicTacToeBoard;
import main.java.participant.Player;
import main.java.participant.HumanPlayer;
import main.java.participant.PlayerFactory;

public class TicTacToeDemo {

	public static void main(String[] args) {

		// instantiating Tic Tac Board Singleton
		TicTacToeBoard game = TicTacToeBoard.getInstance();

		//get level of play from 'human'
		PlayerFactory playerFactory = new PlayerFactory();
		Player human = playerFactory.getPlayer(Player.PlayerType.HUMAN);
		Player robot = playerFactory.getPlayer(HumanPlayer.getLevelOfPlay());

		//display rules and show board
		game.showRules();
		game.displayBoard();		

		//play game until winner or tie
		while(!game.isGameOver()) {


			//get player1(human) move and check if winner
			human.makeMove(game);
			if(human.getPlayerIcon() == game.isWinner()) {
				human.congratulatePlayer();
				break;
			}

			//get player2(robot) move and check if winner
			robot.makeMove(game);
			if(robot.getPlayerIcon() == game.isWinner()) {
				robot.congratulatePlayer();
				break;
			}

		}

		System.out.println("Thanks for playing!"); 
	}

}
