package controller;

import entity.*; 
import gamelogic.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import boundary.GUI_GUI;


public class ChanceDeck {
	private static String[] text;
	private static int[] amount;
	private static Chancecard[] Cards;
	private static int jailInDeck = 2;
	private static int nextDraw = -1;

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
		Card = new Chancecard[22];

		Card[0] = new RecieveCard("Chance1", 200);
		Card[1] = new RecieveCard("Chance2", 200);		
		Card[2] = new RecieveCard("Chance3", 500);
		Card[3] = new RecieveCard("Chance4", 1000);
		Card[4] = new RecieveCard("Chance5", 1000);
		Card[5] = new RecieveCard("Chance6", 1000);
		Card[6] = new RecieveCard("Chance7", 1000);
		Card[7] = new RecieveCard("Chance8", 1000);
		Card[8] = new RecieveCard("Chance9", 1000);
		Card[9] = new RecieveCard("Chance10", 3000);
		Card[10] = new PayCard("Chance11", -200);
		Card[11] = new PayCard("Chance12", -200);
		Card[12] = new PayCard("Chance13", -1000);	
		Card[13] = new PayCard("Chance14", -1000);
		Card[14] = new PayCard("Chance15", -2000);
		Card[15] = new PayCard("Chance16", -3000);
		Card[16] = new PayCard("Chance17", -3000);
		Card[17] = new JailCard("Chance18");
		Card[18] = new JailCard("Chance19");
		Card[19] = new MoveCard("Chance20", 30);
		Card[20] = new MoveCard("Chance21", 30);
		Card[21] = new MoveCard("Chance22", 0);

		setCards(Card);
	}


	public static void shuffle() {
		for(int c = 0; c < 3; c++) {
			for(int i = 0; i < ChanceDeck.Cards.length - 1; i++) {
				int j  = (int) ((Math.random()) * (ChanceDeck.Cards.length - 1));
				Chancecard temp  = ChanceDeck.Cards[j];
				ChanceDeck.Cards[j] = ChanceDeck.Cards[i];
				ChanceDeck.Cards[i] = temp;
			}
		}
		ChanceDeck.nextDraw = -1;
		System.out.println("Kortene er blandet");
	}

	public Chancecard drawCard() {
		if (ChanceDeck.Cards.length - 1 == ChanceDeck.nextDraw)
			shuffle();

		ChanceDeck.nextDraw++;
		Chancecard draw = ChanceDeck.Cards[ChanceDeck.nextDraw];
		
		
		System.out.println("index" +ChanceDeck.nextDraw);
		System.out.println(draw);

	
//		System.out.println(getAmount(ChanceDeck.nextDraw));
//		System.out.println(getAmount(18));
//		System.out.println(draw.getClass());
//		System.out.println((draw.getClass() == MoveCard.class));
		System.out.println("Længde" +ChanceDeck.Cards.length);


		if(draw instanceof RecieveCard) {
			System.out.println(((RecieveCard)draw).getAmount());
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(((RecieveCard)draw).getAmount());
		}
			
		else if(draw instanceof PayCard) {
			System.out.println(((PayCard)draw).getAmount());	
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(((PayCard)draw).getAmount());

		}else if(draw instanceof MoveCard) {
						System.out.println("Move to: " +((MoveCard)draw).getAmount());
						GUI_GUI.getFields(ListOfPlayers.getPlayers(Game.getWhosTurn()).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), false);
						ListOfPlayers.getPlayers(Game.getWhosTurn()).setCurrentField(((MoveCard)draw).getAmount());
						GUI_GUI.getFields(ListOfPlayers.getPlayers(Game.getWhosTurn()).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), true);

			//			ListOfPlayers.getPlayers(Game.getWhosTurn()).setCurrentField(getAmount(ChanceDeck.nextDraw));
			//			GUI_GUI.getFields(ListOfPlayers.getPlayers(Game.getWhosTurn()).getCurrentField()).removeAllCars();
			//			GUI_GUI.getFields(getAmount(ChanceDeck.nextDraw)).setCar(GUI_GUI.getGuiPlayers(Game.getWhosTurn()), true);
		}

		else if	(draw instanceof JailCard) {
			if(this.jailInDeck == 0) {
				if (ChanceDeck.Cards.length == ChanceDeck.nextDraw) { 
					while (draw instanceof JailCard) {
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
	
	public String ShowCardText() {
		String ctext = "";
		Chancecard draw = ChanceDeck.Cards[ChanceDeck.nextDraw];
		for(int i = 0; i < 38; i++) {
			if(draw.getText().equals(getText()[i])) {
				ctext = getText()[i+1];
			}
		}
		return ctext;
	}
	
	

	public static void readText() throws IOException {
		String file = "../CardText.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] title;
		title = new String[44];

		for (int i = 0; i < 44; i++) {
			String currentLine = reader.readLine();
			title[i] = currentLine;
			//			System.out.println(title[i]);
		}
		reader.close();
		setText(title);
	}

	public static int getJailInDeck() {
		return jailInDeck;
	}


	public static void setJailInDeck(int jailInDeck) {
		jailInDeck += jailInDeck;
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
		mmoney = new String[21];
		amount = new int[21];

		for (int i = 0; i < 21; i++) {
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
