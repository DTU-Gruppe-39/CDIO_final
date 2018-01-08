package controller;

import entity.*; 
import gamelogic.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ChanceDeck {
	private static String[] text;
	private static int[] amount;
	private static Chancecard[] Cards;
	private int jailInDeck = 2;
	private static int nextDraw = 0;
	
	public Chancecard[] getCards() {
	return ChanceDeck.Cards;
	}

	
	public static void setCards(Chancecard[] cards) {
		Cards = cards;
	}

	

//		public ChanceDeck(String name) {
//			this.text = name;
//		}

	
	public static void CreateCards(){
		Chancecard[] Card;
		Card = new Chancecard[21];
		
		Card[0] = new RecieveCard("Chance0", 200);
		Card[1] = new RecieveCard("Chance1", 500);
		Card[2] = new RecieveCard("Chance2", 1000);
		Card[3] = new RecieveCard("Chance3", 1000);
		Card[4] = new RecieveCard("Chance4", 1000);
		Card[5] = new RecieveCard("Chance5", 1000);
		Card[6] = new RecieveCard("Chance6", 1000);
		Card[7] = new RecieveCard("Chance7", 1000);
		Card[8] = new RecieveCard("Chance8", 3000);
		Card[9] = new PayCard("Chance8", -200);
		Card[10] = new PayCard("Chance9", -200);
		Card[11] = new PayCard("Chance10", -1000);	
		Card[12] = new PayCard("Chance11", -1000);
		Card[13] = new PayCard("Chance12", -2000);
		Card[14] = new PayCard("Chance13", -3000);
		Card[15] = new PayCard("Chance14", -3000);
		Card[16] = new JailCard("Chance17");
		Card[17] = new JailCard("Chance18");
		Card[18] = new MoveCard("Chance19", 30);
		Card[19] = new MoveCard("Chance20", 30);
		Card[20] = new MoveCard("Chance21", 0);
		
		setCards(Card);
	}


	public void shuffle() {
		for(int i = 0; i < ChanceDeck.Cards.length - 1; i++) {
			int j  = (int) ((Math.random()) * (ChanceDeck.Cards.length - 1));
			Chancecard temp  = ChanceDeck.Cards[j];
			ChanceDeck.Cards[j] = ChanceDeck.Cards[i];
			ChanceDeck.Cards[i] = temp;
		}
		ChanceDeck.nextDraw = 0;
		System.out.println("Kortene er blandet");
	}

	public Chancecard drawCard() {

		if (ChanceDeck.Cards.length == ChanceDeck.nextDraw)
			shuffle();
		
		Chancecard draw = ChanceDeck.Cards[ChanceDeck.nextDraw];
		ChanceDeck.nextDraw++;
		
		System.out.println(ChanceDeck.nextDraw);
		System.out.println(Cards[nextDraw]);
		System.out.println(ChanceDeck.Cards.length);
		
		
		if(draw.getClass() == RecieveCard.class)
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(getAmount(ChanceDeck.nextDraw));
		
		else if(draw.getClass() == PayCard.class) {
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(getAmount(ChanceDeck.nextDraw));
			
		}else if(draw.getClass() == MoveCard.class) {
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setCurrentField(MoveCard.getMoveTo());
		}

		else if	(draw.getClass() == JailCard.class) {
			if(this.jailInDeck == 0) {
				if (ChanceDeck.Cards.length == ChanceDeck.nextDraw) { 
					while (draw.getClass() ==  JailCard.class) {
						shuffle();
						draw = ChanceDeck.Cards[ChanceDeck.nextDraw];
						ChanceDeck.nextDraw++;
					}
				} else {
					draw = ChanceDeck.Cards[ChanceDeck.nextDraw];
					ChanceDeck.nextDraw++;
				}
			} else {
				ListOfPlayers.getPlayers(Game.getWhosTurn()).setHaveJailCard(1);
				this.jailInDeck--;
			}
		}
		return draw;
	}

	public static void readText() throws IOException {
		String file = "../CardText.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] title;
		title = new String[21];

		for (int i = 0; i < 21; i++) {
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
	
	public static int getAmount(int index) {
		return amount [index];
	}

}
