package controller;

import entity.*;
import gamelogic.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ChanceDeck {
	private static String[] text;
	private static int[] amount;
	private Chancecard[] Cards;
	private int nextDraw;
	private int jailInDeck = 2;


	//	public ChanceDeck(String[] text) {
	//		this.text = text;
	//	}

	public void CreateCards(){
		Cards[0] = new RecieveCard("Chance0", 200);
		Cards[1] = new RecieveCard("Chance1", 500);
		Cards[2] = new RecieveCard("Chance2", 1000);
		Cards[3] = new RecieveCard("Chance3", 1000);
		Cards[4] = new RecieveCard("Chance4", 1000);
		Cards[5] = new RecieveCard("Chance5", 1000);
		Cards[6] = new RecieveCard("Chance6", 1000);
		Cards[7] = new RecieveCard("Chance7", 1000);
		Cards[8] = new PayCard("Chance8", -200);
		Cards[9] = new PayCard("Chance9", -200);
		Cards[10] = new PayCard("Chance10", -1000);	
		Cards[11] = new PayCard("Chance11", -1000);
		Cards[12] = new PayCard("Chance12", -2000);
		Cards[13] = new PayCard("Chance13", -2000);
		Cards[14] = new PayCard("Chance14", -200);
		Cards[15] = new PayCard("Chance15", -200);
		Cards[16] = new PayCard("Chance16", -1000);
		Cards[17] = new JailCard("Chance17");
		Cards[18] = new JailCard("Chance18");
		Cards[19] = new MoveCard("Chance19", 30);
		Cards[20] = new MoveCard("Chance20", 30);
		Cards[21] = new MoveCard("Chance21", (((25))));
		Cards[22] = new MoveCard("Chance22", (((26))));
		Cards[23] = new MoveCard("Chance23", (((27))));
		Cards[24] = new MoveCard("Chance24", 0);
		Cards[25] = new MoveCard("Chance25", ((29)));
		Cards[26] = new MoveCard("Chance26", (((30))));
		Cards[27] = new MoveCard("Chance27", ((31)));
		Cards[28] = new MoveCard("Chance28", ((32)));
	}

	//shuffle * 2
	public void shuffle() {
		for(int i = 0; i < Cards.length*2; i++) {
			int j  = (int) ((Math.random()) * (Cards.length - 1));
			Chancecard temp  = Cards[j];
			Cards[j] = Cards[i];
			Cards[i] = temp;
		}
		nextDraw = 0;
	}

	public Chancecard drawCard(Player player) {
		if (Cards.length == nextDraw)
			shuffle();
		
		Chancecard draw = Cards[nextDraw];
		nextDraw++;
			
		if(draw.getClass() == RecieveCard.class)
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(RecieveCard.getAmount());
		
		else if(draw.getClass() == PayCard.class) {
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(PayCard.getAmount());
			
		}else if(draw.getClass() == MoveCard.class) {
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setCurrentField(MoveCard.getMoveTo());
		}

		else if	(draw.getClass() == JailCard.class) {
			if(jailInDeck == 0) {
				if (Cards.length == nextDraw) { 
					while (draw.getClass() ==  JailCard.class) {
						shuffle();			
						draw = Cards[nextDraw];
						nextDraw++;
					}
				} else {
					draw = Cards[nextDraw];
					nextDraw++;
				}
			} else {
				ListOfPlayers.getPlayers(Game.getWhosTurn()).setHaveJailCard(1);
				jailInDeck--;
			}
		}
		return draw;
	}


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
			//			System.out.print(mmoney[i]);
			amount[i] = (Integer.parseInt(mmoney[i]));
			//			System.out.println(amount[i]);
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
