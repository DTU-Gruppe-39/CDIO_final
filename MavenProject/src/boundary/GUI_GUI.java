package boundary;
import java.awt.Color; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import gui_codebehind.GUI_BoardController;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Car;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Empty;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Player;
import gui_fields.GUI_Refuge;
import gui_fields.GUI_Shipping;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_fields.GUI_Tax;
import gui_main.GUI;
public class GUI_GUI {
	private static String [] titles;
	private static String [] disc;
	private static String [] subText;
	private static String [] names;
	private static int numberOfPlayers;
	public static GUI gui;
	private static GUI_Player[] guiPlayers;
	private static GUI_Street[] street;
	
	public static GUI_Street getStreet(int index) {
		return street[index];
	}

//	public static GUI_Field getFields(int index) {
//		return fields[index];
//	}

	public static String[] getNames() {
		return names;
	}

	public static int getNumberOfPlayers() {		
		return numberOfPlayers;
	}
	
	
	public static void GUILauncher() throws IOException {
		long t0 = System.currentTimeMillis();
		readText();
		GUI_Create.createGuiFields();			
		long t1 = System.currentTimeMillis();
		System.out.println("GUI'en starter på "+ (t1-t0) + "ms");
		
		numberOfPlayers = gui.getUserInteger("                                            Indtast antal spillere, mellem 2 - 6", 2, 6);			
		while (2 > numberOfPlayers || numberOfPlayers > 6) {
			numberOfPlayers = gui.getUserInteger("                                            Indtast antal spillere, mellem 2 - 6", 2, 6);			
		}
		
		names = new String [numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++ ) {
			names[i] = gui.getUserString("                                            Spiller " + (i + 1) + " indtast navn");
			while (names[i].length() == 0) {
				gui.showMessage("                                            Du skal indtaste et navn \n                                            Vælg venligst et andet navn");
				names[i] = gui.getUserString("                                            Spiller " + (i + 1) + " indtast navn");
			}
			char result = names[i].charAt(0);
			while (result == ' ') {
				gui.showMessage("                                            Dit navn må ikke starte med et mellemrum \n                                            Vælg venligst et andet navn");
				names[i] = gui.getUserString("                                            Spiller " + (i + 1) + " indtast navn");
				result = names[i].charAt(0);
			}
			if (i > 0) {
				while (contains(names[i], i) || result == ' ' ) {
					if (contains(names[i], i)) {
						gui.showMessage("                                            En anden spiller hedder allerede dette navn \n                                            Vælg venligst et andet navn");
					} else if (result == ' ') {
						gui.showMessage("                                            Dit navn må ikke starte med et mellemrum \n                                            Vælg venligst et andet navn");
					}
					names[i] = gui.getUserString("                                            Spiller " + (i + 1) + " indtast navn");
					result = names[i].charAt(0);
				}
			}
		}
		GUI_Create.guiCreatePlayerCar();
	}
	
	public static boolean contains(String name, int j) {
		boolean answer;
		int number = 0;
		for (int i = 0; i < j; i++) {
			if (name.equals(names[i])) {
				number++;
			}
		}
		if (number != 0) {
			answer = true;
		} else {
			answer = false;
		}
		return answer;
	}

// Læser titlerne på felterne fra titles arrayet

	public static String [] getTitles() {
		return titles;
	}

	public static void setTitles(String [] titles) {
		GUI_GUI.titles = titles;
	}

	public static void readText() throws IOException {
		String file = "../textFile.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] title;
		title = new String[40];

		for (int i = 0; i < 40; i++) {
			String currentLine = reader.readLine();
			title[i] = currentLine;
		}
		reader.close();
		setTitles(title);
	}

	public static void readDisc() throws IOException {
		String file = "../FieldDescription.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] discText;
		discText = new String[40];
		
		for (int i = 0; i < 40; i++) {
			String currentLine = reader.readLine();
			discText[i] = currentLine;
//			System.out.println(discText[i]);
		}
		reader.close();
		setDisc(discText);
	}

	public static void readSubText() throws IOException {
		String file = "../Subtext.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String [] subtext;
		subtext = new String[40];
		
		for (int i = 0; i < 40; i++) {
			String currentLine = reader.readLine();
			subtext[i] = currentLine;
//			System.out.println(subtext[i]);
		}
		reader.close();
		setSubText(subtext);
	}

	public static String[] getSubText() {
		return subText;
	}

	public static void setSubText(String[] subText) {
		GUI_GUI.subText = subText;
	}

	public static String[] getDisc() {
		return disc;
	}

	public static void setDisc(String[] disc) {
		GUI_GUI.disc = disc;
	}
	public static boolean displayJailChoice() {
		return gui.getUserLeftButtonPressed("Kast med terningerne for at forsøge at slå 2 ens, eller betal 1000kr, for at komme ud af fængsel", "Kast", "Betal 1000kr.");
	}
	public static boolean displayBuyChoice() {
		return gui.getUserLeftButtonPressed("                                            Vil du købe denne Grund?", "Ja", "Afslut tur");
	}

	public static boolean displayTaxChoice() {
		return gui.getUserLeftButtonPressed("                                Du skal enten betale 4000kr eller 10% af dit kapital i indkomstskat", "4000kr", "10% af kapital");
	}
	public static GUI_Player getGuiPlayers(int index) {
		return guiPlayers[index -1];
	}
}
