package boundary;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import gui_fields.GUI_Car;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_main.GUI;
public class GUI_GUI {
	private static String [] titles;
	private static String [] names;
	private static int numberOfPlayers;
	public static GUI gui;
	private static GUI_Field[] fields;
	private static GUI_Player[] guiPlayers;
	private static GUI_Street[] street;
	
	
	
	public static GUI_Street getStreet(int index) {
		return street[index];
	}

	public static GUI_Field getFields(int index) {
		return fields[index];
	}

	public static String[] getNames() {
		return names;
	}

	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	
	public static void GUILauncher() throws IOException {
		long t0 = System.currentTimeMillis();
		readText();

		 fields = new GUI_Field[24];
		 street = new GUI_Street[24];

		//	Opretter alle felterne

		fields[0] = new GUI_Start();
		fields[0].setBackGroundColor(Color.RED);
		fields[0].setForeGroundColor(Color.white);
		fields[0].setTitle("START");
		fields[0].setSubText("");

		fields[1] = new GUI_Street(titles[1], "M1" , titles[1], "M1", Color.GRAY, Color.white);
//		street[1] = new GUI_Street(titles[1], "M1" , titles[1], "M1", Color.GRAY, Color.white);

		fields[2] = new GUI_Street(titles[2], "M1", titles[2], "M1", Color.GRAY, Color.WHITE);
//		street[2] = new GUI_Street(titles[2], "M1", titles[2], "M1", Color.GRAY, Color.WHITE);
		
		fields[3] = new GUI_Chance(titles[3], "", titles[3], Color.white, Color.BLACK);

		fields[4] = new GUI_Street(titles[4], "M1", titles[4], "M1", Color.cyan, Color.BLACK);
//		street[4] = new GUI_Street(titles[4], "M1", titles[4], "M1", Color.cyan, Color.BLACK);

		fields[5] = new GUI_Street(titles[5], "M1", titles[5], "M1", Color.cyan, Color.BLACK);
//		street[5] = new GUI_Street(titles[5], "M1", titles[5], "M1", Color.cyan, Color.BLACK);

		fields[6] = new GUI_Street(titles[6], "", titles[6], "", Color.WHITE, Color.BLACK);
//		street[6] = new GUI_Street(titles[6], "", titles[6], "", Color.WHITE, Color.BLACK);

		fields[7] = new GUI_Street(titles[7], "M2", titles[7], "M2", Color.MAGENTA, Color.BLACK);
//		street[7] = new GUI_Street(titles[7], "M2", titles[7], "M2", Color.MAGENTA, Color.BLACK);

		fields[8] = new GUI_Street(titles[8], "M2", titles[8], "M2", Color.magenta, Color.BLACK);
//		street[8] = new GUI_Street(titles[8], "M2", titles[8], "M2", Color.magenta, Color.BLACK);

		fields[9] = new GUI_Chance(titles[9], "", titles[9], Color.white, Color.BLACK);

		fields[10] = new GUI_Street(titles[10], "M2", titles[10], "M2", Color.orange, Color.BLACK);

		fields[11] = new GUI_Street(titles[11], "M2", titles[11], "M2", Color.orange, Color.BLACK);

		fields[12] = new GUI_Street(titles[12], "", titles[12], "", Color.WHITE, Color.RED);

		fields[13] = new GUI_Street(titles[13], "M3", titles[13], "M3", Color.red, Color.BLACK);

		fields[14] = new GUI_Street(titles[14], "M3", titles[14], "M3", Color.red, Color.BLACK);

		fields[15] = new GUI_Chance(titles[15], "", titles[15], Color.white, Color.BLACK);

		fields[16] = new GUI_Street(titles[16], "M3", titles[16], "M3", Color.yellow, Color.BLACK);

		fields[17] = new GUI_Street(titles[17], "M3", titles[17], "M3", Color.yellow, Color.BLACK);

		fields[18] = new GUI_Street(titles[18], "", titles[18],"", Color.white, Color.black);

		fields[19] = new GUI_Street(titles[19], "M4", titles[19], "M4", Color.GREEN, Color.BLACK);

		fields[20] = new GUI_Street(titles[20], "M4", titles[20], "M4", Color.green, Color.BLACK);

		fields[21] = new GUI_Chance(titles[21], "", titles[21], Color.white, Color.BLACK);

		fields[22] = new GUI_Street(titles[22], "M5", titles[22], "M5", Color.BLUE, Color.WHITE);

		fields[23] = new GUI_Street(titles[23], "M5", titles[23], "M5", Color.BLUE, Color.WHITE);
		gui = new GUI(fields, Color.LIGHT_GRAY);			

		long t1 = System.currentTimeMillis();
		System.out.println("GUI'en starter på "+ (t1-t0) + "ms");
		
//		System.out.println(Arrays.toString(titles));
		numberOfPlayers = gui.getUserInteger("Indtast antal spillere", 2, 4);
//		System.out.println(numberOfPlayers);
		
		names = new String [numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++ ) {
			names[i] = gui.getUserString("Spiller " + (i + 1) + " indtast navn");
		}
//		Cars = new GUI_Cars[numberOfPlayers];
//		for (int i = 0; i < numberOfPlayers; i++ ) {
//			cars[i] = new GUI_Car();
//		}
		
		guiPlayers = new GUI_Player[numberOfPlayers];
		switch(numberOfPlayers) {
			case 2: 
				GUI_Car one = new GUI_Car();
				one.setPrimaryColor(Color.RED);
				guiPlayers[0] = new GUI_Player(names[0],20, one);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car two = new GUI_Car();
				two.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],20, two);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				break;
				
			case 3:
				GUI_Car three = new GUI_Car();
				three.setPrimaryColor(Color.RED);
				guiPlayers[0] = new GUI_Player(names[0],18, three);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car four = new GUI_Car();
				four.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],18, four);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				
				GUI_Car five = new GUI_Car();
				five.setPrimaryColor(Color.WHITE);
				guiPlayers[2] = new GUI_Player(names[2],18, five);
				gui.addPlayer(guiPlayers[2]);
				fields[0].setCar(guiPlayers[2], true);

				break;
				
			case 4:
				GUI_Car six = new GUI_Car();
				six.setPrimaryColor(Color.RED);
				guiPlayers[0] = new GUI_Player(names[0],16, six);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car seven = new GUI_Car();
				seven.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],16, seven);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				
				GUI_Car eight = new GUI_Car();
				eight.setPrimaryColor(Color.WHITE);
				guiPlayers[2] = new GUI_Player(names[2],16, eight);
				gui.addPlayer(guiPlayers[2]);
				fields[0].setCar(guiPlayers[2], true);
				
				GUI_Car nine = new GUI_Car();
				nine.setPrimaryColor(Color.cyan);
				guiPlayers[3] = new GUI_Player(names[3],16, nine);
				gui.addPlayer(guiPlayers[3]);
				fields[0].setCar(guiPlayers[3], true);
				
				break;
			
			default: 
				break;
		}
		
		
//		System.out.println(Arrays.toString(names));
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
		title = new String[24];

		for (int i = 0; i < 24; i++) {
			String currentLine = reader.readLine();
			title[i] = currentLine;
		}
		reader.close();
		setTitles(title);
	}

	public static GUI_Player getGuiPlayers(int index) {
		return guiPlayers[index -1];
	}
}
