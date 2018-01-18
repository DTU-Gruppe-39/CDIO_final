package controller;

import java.io.IOException;
import java.util.Arrays;

import boundary.GUI_GUI;
import entity.GameBoard;
import entity.HousePrice;

public class GameController {

	public void playGame() throws IOException{
		HousePrice.readHousePrice();
		HousePrice.setupHouse();
		System.out.println(Arrays.toString(HousePrice.getHousePriceInt()));
		GUI_GUI.readDisc();
		GUI_GUI.readSubText();
		GUI_GUI.GUILauncher();
		ChanceDeck.CreateCards();
//		ChanceDeck.shuffle();
		GameBoard.fillFields();
		ListOfPlayers list = new ListOfPlayers((GUI_GUI.getNumberOfPlayers() + 1)); //Players index in array needs to start at 1, because 0 = to no owner on a field
		list.addplayer(GUI_GUI.getNumberOfPlayers());		
		Game.gameLogic();
	}
}
