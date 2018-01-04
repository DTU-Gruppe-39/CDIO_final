package gamelogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entity.Player;

public abstract class Chancecard {
	protected String text;

	


	public void setChancecardText(String text) {
		this.text = text;
	}

	abstract public void drawCard(Player player);

	
	public String getChanceCardText() {
		return text;
	}
	
}
