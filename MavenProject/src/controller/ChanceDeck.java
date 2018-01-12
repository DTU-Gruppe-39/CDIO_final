package controller;

import gamelogic.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Chancecards.*;
import boundary.GUI_GUI;


public class ChanceDeck {
	private static String[] text;
	private static Chancecard[] Cards;
	private static int jailInDeck = 2;
	private static int nextDraw = -1;

	public static void CreateCards() throws IOException{
		Chancecard[] Card;
		Card = new Chancecard[21];
		readText();
		Card[0] = new RecieveCard("Chance1", 200);
		Card[1] = new RecieveCard("Chance2", 500);
		Card[2] = new RecieveCard("Chance3", 1000);
		Card[3] = new RecieveCard("Chance4", 1000);
		Card[4] = new RecieveCard("Chance5", 1000);
		Card[5] = new RecieveCard("Chance6", 1000);
		Card[6] = new RecieveCard("Chance7", 1000);
		Card[7] = new RecieveCard("Chance8", 1000);
		Card[8] = new RecieveCard("Chance9", 3000);
		Card[9] = new PayCard("Chance10", -200);
		Card[10] = new PayCard("Chance11", -200);
		Card[11] = new PayCard("Chance12", -1000);	
		Card[12] = new PayCard("Chance13", -1000);
		Card[13] = new PayCard("Chance14", -2000);
		Card[14] = new PayCard("Chance15", -3000);
		Card[15] = new PayCard("Chance16", -3000);
		Card[16] = new JailCard("Chance17");
		Card[17] = new JailCard("Chance18");
		Card[18] = new MoveCard("Chance19", 30);
		Card[19] = new MoveCard("Chance20", 30);
		Card[20] = new MoveCard("Chance21", 0);

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
						
						if (((MoveCard)draw).getAmount() == 0){
						ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(4000);
						}
		}

		else if	(draw instanceof JailCard) {
			if(ChanceDeck.jailInDeck == 0) {
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
				ChanceDeck.jailInDeck--;
			}
		}
		return draw;
	}
	
	public String ShowCardText() {
		String ctext = "";
		Chancecard draw = ChanceDeck.Cards[ChanceDeck.nextDraw];
		for(int i = 0; i <= 41; i++) {
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
		title = new String[42];

		for (int i = 0; i <= 41; i++) {
			String currentLine = reader.readLine();
			title[i] = currentLine;
		}
		reader.close();
		setText(title);
	}

	public Chancecard[] getCards() {
		return ChanceDeck.Cards;
	}


	public static void setCards(Chancecard[] cards) {
		Cards = cards;
	}
	
	public static int getJailInDeck() {
		return jailInDeck;
	}


	public static void setJailInDeck(int jailInDeck) {
		ChanceDeck.jailInDeck += jailInDeck;
	}


	public String[] getText() {
		return text;
	}

	public static void setText(String[] text) {
		ChanceDeck.text = text;
	}

}
