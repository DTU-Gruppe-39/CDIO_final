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
		
		
		/*	public static void readText() throws IOException {
		String file = "../textFile.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] title;
		title = new String[24];

		for (int i = 0; i < 24; i++) {
			String currentLine = reader.readLine();
			title[i] = currentLine;
		}
		reader.close();
		setTitles(title);
		
		*/
	
}
