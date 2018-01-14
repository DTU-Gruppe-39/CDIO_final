package controller;
import java.io.IOException;
import java.util.Arrays;

import boundary.GUI_GUI;
import gamelogic.Game;
import entity.GameBoard;
import entity.HousePrice;
public class Main {
	public static void main(String[] args) throws IOException {

//		new GameController().playGame();
		HousePrice.readHousePrice();
		HousePrice.setupHouse();
		System.out.println(Arrays.toString(HousePrice.getHousePriceInt()));
		GUI_GUI.readDisc();
		GUI_GUI.readSubText();
		GUI_GUI.GUILauncher();
		GameBoard.fillFields();
		//GameBoard.adjustPrices();
		ListOfPlayers list = new ListOfPlayers((GUI_GUI.getNumberOfPlayers() + 1)); //Players index in array needs to start at 1, because 0 = to no owner on a field
		list.addplayer(GUI_GUI.getNumberOfPlayers());		
		Game.gameLogic();
	}
}