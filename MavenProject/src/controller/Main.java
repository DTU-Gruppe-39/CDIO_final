package controller;
import java.io.IOException;

import boundary.GUI_GUI;
import gamelogic.Game;

public class Main {
	public static void main(String[] args) throws IOException {

		ChanceDeck.readAmaount();

//		new GameController().playGame();
		GUI_GUI.readDisc();
		GUI_GUI.readSubText();
		GUI_GUI.GUILauncher();
		Game.fillFields();
		ChanceDeck.CreateCards();
		ChanceDeck.shuffle();
		ListOfPlayers list = new ListOfPlayers((GUI_GUI.getNumberOfPlayers() + 1)); //Players index in array needs to start at 1, because 0 = to no owner on a field
		list.addplayer(GUI_GUI.getNumberOfPlayers());		
		Game.gameLogic();
	}
}