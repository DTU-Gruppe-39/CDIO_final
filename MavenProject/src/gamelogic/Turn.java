package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.Player;

public class Turn {
	//Everything needed between each turn
	public void updateTurn (int die1, int die2, Player player) {
		if (die1 == die2) {
			sameDice++;
			if (sameDice == 3) {
				tripleTurn(player);			
			} else {
				handleTurn(die1, die2, player);
			}
		} else {
			handleTurn(die1, die2, player);
			if (Game.getWhosTurn() == GUI_GUI.getNumberOfPlayers()) {
				Game.setWhosTurn(1);
			}
			else {
				Game.increasWhosTurn();
			}
		}
	}

	public void handleTurn(int die1, int die2, Player player) {
		if(player.isDead()==false && player.isJailed()==true) {
			Jail(die1, die2);
			playerDied();
		}
		if (player.isDead()==false && player.isJailed()==false) {
			movePlayer(player, die1, die2);
			handleField(player.getCurrentField(), player, die1, die2);
			goToJail();
			playerDied();
		}
	}

	public void tripleTurn(Player player) {
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), false);
		player.setCurrentField(10);
		player.setJailed(true);
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), true);
		sameDice = 0;
		if (Game.getWhosTurn()== GUI_GUI.getNumberOfPlayers()) {
			Game.setWhosTurn(1);
		}
		else {
			Game.increasWhosTurn();
		}
	}	
	
	

}
