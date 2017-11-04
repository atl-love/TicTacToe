package main.java.participant;

import main.java.participant.Player.PlayerType;

public class PlayerFactory {

	//use getPlayer method to get player type 
	public Player getPlayer(Enum<PlayerType> playerType){
		if(playerType == null){
			return null;
		}		
		if(playerType == Player.PlayerType.HUMAN){
			return new HumanPlayer();

		} else if(playerType == Player.PlayerType.EASYROBOT){
			return new EasyRobotPlayer();
		} else if(playerType == Player.PlayerType.MEDROBOT){
			return new MedRobotPlayer();
		} else if(playerType == Player.PlayerType.ADVROBOT){
			return new AdvRobotPlayer();
		}

		//TODO Update to use Google Guava instead of returning null
		return null;
	}

}
