package controller;

<<<<<<< HEAD
=======
import gamelogic.Chancecard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
>>>>>>> e78f2874bbbe1e453f6c7a3129c795e045496684

import gamelogic.*;

public class ChanceDeck {
	private static String[] text;
	private Chancecard[] Cards;
	
	
//	public Chancecard drawCard() {
//		Chancecard drawn;
//		
//		
//		return drawn;
//	}
	
<<<<<<< HEAD
	private void CreateCards(){
	Cards[0] = new RecieveCard("Chance0", 200);
	Cards[1] = new RecieveCard("Chance1", 500);
	Cards[2] = new RecieveCard("Chance2", 1000);
	Cards[3] = new RecieveCard("Chance3", 1000);
	Cards[4] = new RecieveCard("Chance4", 1000);
	Cards[5] = new RecieveCard("Chance5", 1000);
	Cards[6] = new RecieveCard("Chance6", 1000);
	Cards[7] = new RecieveCard("Chance7", 1000);
	Cards[8] = new PayCard("Chance8", 200);
	Cards[9] = new PayCard("Chance9", 200);
	Cards[10] = new PayCard("Chance10", 1000);	
	Cards[11] = new PayCard("Chance11", 1000);
	Cards[12] = new PayCard("Chance12", 2000);
	Cards[13] = new PayCard("Chance13", 2000);
	Cards[14] = new PayCard("Chance14", 200);
	Cards[15] = new PayCard("Chance15", 200);
	Cards[16] = new PayCard("Chance16", 1000);
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
	
	public void shuffle() {
		for(int i = 0; i < Cards.length; i++) {
			int j  = (int) (Math.random() * Cards.length);
			Chancecard temp  = Cards[i];
			int Cards[i] = Cards[];
		}
		
=======
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
>>>>>>> e78f2874bbbe1e453f6c7a3129c795e045496684
	}
	
}
