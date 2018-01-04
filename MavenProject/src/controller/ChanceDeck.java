package controller;

import gamelogic.Chancecard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class ChanceDeck {
	private static String[] text;
	private static int[] amount;
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
	
	public static void readAmaount() throws IOException {
		String file = "../ChanceBalanceCards.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] mmoney;
		mmoney = new String[18];
		amount = new int[18];

		for (int i = 0; i < 18; i++) {
			String currentLine = reader.readLine();
			mmoney[i] = currentLine;
			System.out.print(mmoney[i]);
			amount[i] = (Integer.parseInt(mmoney[i]));
			System.out.println(amount[i]);
		}
		reader.close();
		setAmount(amount);
	}

	public static int[] getAmount() {
		return amount;
	}

	public static void setAmount(int[] amount) {
		ChanceDeck.amount = amount;
	}
	
}
