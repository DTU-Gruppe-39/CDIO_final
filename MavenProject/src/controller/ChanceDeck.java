package controller;

import gamelogic.Chancecard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class ChanceDeck {
	private static String[] text;
	private Chancecard[] Cards;
	private Random rand;
	
	
//	public Chancecard drawCard() {
//		Chancecard drawn;
//		
//		
//		return drawn;
//	}
	
	public static void readText() throws IOException {
		String file = "../CardText.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] title;
		title = new String[32];

		for (int i = 0; i < 32; i++) {
			String currentLine = reader.readLine();
			title[i] = currentLine;
//			System.out.println(title[i]);
		}
		reader.close();
		setText(title);
	}
	
	public String[] getText() {
		return text;
	}

	public static void setText(String[] text) {
		ChanceDeck.text = text;
	}
	
}
