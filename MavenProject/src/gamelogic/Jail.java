package gamelogic;

import boundary.GUI_Create;
import boundary.GUI_GUI;
import controller.ChanceDeck;
import entity.Player;

public class Jail {
	public void goToJail(Player player) {
		if(player.getCurrentField()==30) {
			player.setJailed(true);
			player.setCurrentField(10);
			GUI_Create.getFields(30).removeAllCars();
			//Move player on GUI to prison
			GUI_Create.getFields(10).setCar(GUI_Create.getGuiPlayers(Game.getWhosTurn()), true);
			GUI_GUI.gui.showMessage("                                            Du er røget i fængsel");

		}
	}

	public void jailTurn(int die1, int die2, Player player) {
		if (player.getHaveJailCard()>0) {
			player.setHaveJailCard(-1);
			player.setJailed(false);
			player.GotOutOfJail();
			ChanceDeck.setJailInDeck(1);
		}

		else if (GUI_GUI.displayJailChoice()==false) {
			player.setNewBalance(-1000);
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
