package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.Player;

public class Turn {
	//Everything needed between each turn
	Jail jail = new Jail();
	Miscellaneous misc = new Miscellaneous(Game.getWhosTurn(), Game.getFields());
	LandOnField landOnField = new LandOnField(Game.getWhosTurn(), Game.getFields());
	private static int sameDice = 0;
	
	
	
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
			this.jail.jailTurn(die1, die2, player);
			this.misc.playerDied(player);
		}
		if (player.isDead()==false && player.isJailed()==false) {
			this.landOnField.movePlayer(player, die1, die2);
			this.landOnField.handleField(player.getCurrentField(), player, die1, die2);
			this.jail.goToJail(player);
			this.misc.playerDied(player);
		}
	}

	public void tripleTurn(Player player) {
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), false);
		player.setCurrentField(10);
		player.setJailed(true);
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), true);
		this.sameDice = 0;
		if (Game.getWhosTurn()== GUI_GUI.getNumberOfPlayers()) {
			Game.setWhosTurn(1);
		}
		else {
			Game.increasWhosTurn();
		}
	}	
	
	

}
