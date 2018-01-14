package gamelogic;

import boundary.GUI_GUI;
import controller.ChanceDeck;
import controller.ListOfPlayers;

public class Jail {
	public void goToJail() {
		if(ListOfPlayers.getPlayers(whosTurn).getCurrentField()==30) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(true);
			ListOfPlayers.getPlayers(whosTurn).setCurrentField(10);
			GUI_GUI.getFields(30).removeAllCars();
			//Move player on GUI to prison
			GUI_GUI.getFields(10).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
			GUI_GUI.gui.showMessage("                                            Du er røget i fængsel");

		}
	}

	public void jailTurn(int die1, int die2) {
		if (ListOfPlayers.getPlayers(whosTurn).getHaveJailCard()>0) {
			ListOfPlayers.getPlayers(whosTurn).setHaveJailCard(-1);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
			ChanceDeck.setJailInDeck(1);
		}

		else if (GUI_GUI.displayJailChoice()==false) {
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1000);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}

		else if (die1 == die2) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}
		else if (ListOfPlayers.getPlayers(whosTurn).RoundsInJail==3) {
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1000);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}
		else {
			ListOfPlayers.getPlayers(whosTurn).StayedInJail();
			GUI_GUI.gui.setDice(die1, die2);
		}
	}

}
