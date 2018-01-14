package gamelogic;

import boundary.GUI_GUI;
import controller.ChanceDeck;
import controller.ListOfPlayers;
import entity.*;

public class Jail {

	public void goToJail(Player player) {
		if(player.getCurrentField()==30) {
			player.setJailed(true);
			player.setCurrentField(10);
			GUI_GUI.getFields(30).removeAllCars();
			//Move player on GUI to prison
			GUI_GUI.getFields(10).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), true);
			GUI_GUI.gui.showMessage("                                            Du er røget i fængsel");

		}
	}

	public void Jail(int die1, int die2,Player player) {
		if (player.getHaveJailCard()>0) {
			player.setHaveJailCard(-1);
			player.setJailed(false);
			player.GotOutOfJail();
			ChanceDeck.setJailInDeck(1);
		}

		else if (GUI_GUI.displayJailChoice()==false) {
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(-1000);
			player.setJailed(false);
			player.GotOutOfJail();
		}

		else if (die1 == die2) {
			player.setJailed(false);
			player.GotOutOfJail();
		}
		else if (player.RoundsInJail==3) {
			player.setNewBalance(-1000);
			player.setJailed(false);
			player.GotOutOfJail();
		}
		else {
			player.StayedInJail();
			GUI_GUI.gui.setDice(die1, die2);
		}
	}


}
