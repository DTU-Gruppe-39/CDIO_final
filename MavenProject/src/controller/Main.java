package controller;
import java.io.IOException;
import java.util.Arrays;

import boundary.GUI_GUI;
import gamelogic.Game;
import entity.GameBoard;
import entity.HousePrice;
public class Main {
	public static void main(String[] args) throws IOException  {
		
		new GameController().playGame();
		
	}
}