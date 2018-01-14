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
			if (whosTurn == GUI_GUI.getNumberOfPlayers()) {
				whosTurn = 1;
			}
			else {
				whosTurn++;
			}
		}
	}

	public void handleTurn(int die1, int die2, Player player) {
		if(ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==true) {
			Jail(die1, die2);
			playerDied();
		}
		if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==false) {
			movePlayer(player, die1, die2);
			handleField(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player, die1, die2);
			goToJail();
			playerDied();
		}
	}

	public void tripleTurn(Player player) {
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
		ListOfPlayers.getPlayers(whosTurn).setCurrentField(10);
		ListOfPlayers.getPlayers(whosTurn).setJailed(true);
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
		sameDice = 0;
		if (whosTurn == GUI_GUI.getNumberOfPlayers()) {
			whosTurn = 1;
		}
		else {
			whosTurn++;
		}
	}	
	
	

}
