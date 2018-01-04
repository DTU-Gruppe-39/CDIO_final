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

		 fields = new GUI_Field[40];
		 street = new GUI_Street[40];

		//	Opretter alle felterne

		fields[0] = new GUI_Start();
		fields[0].setBackGroundColor(Color.RED);
		fields[0].setForeGroundColor(Color.white);
		fields[0].setTitle("START");
		fields[0].setSubText("");

		fields[1] = new GUI_Street(titles[1], "" , titles[1], "", Color.cyan, Color.black);

		fields[2] = new GUI_Chance(titles[2], "", titles[2], Color.white, Color.BLACK);
		
		fields[3] = new GUI_Street(titles[3], "", titles[3], "", Color.cyan, Color.BLACK);

		fields[4] = new GUI_Street(titles[4], "", titles[4], "", Color.white, Color.BLACK);

		fields[5] = new GUI_Street(titles[5], "", titles[5], "", Color.white, Color.BLACK);

		fields[6] = new GUI_Street(titles[6], "", titles[6], "", Color.pink, Color.BLACK);

		fields[7] = new GUI_Chance(titles[7], "", titles[7], Color.white, Color.BLACK);

		fields[8] = new GUI_Street(titles[8], "", titles[8], "", Color.pink, Color.BLACK);

		fields[9] = new GUI_Street(titles[9], "", titles[9], "", Color.pink, Color.BLACK);

		fields[10] = new GUI_Street(titles[10], "", titles[10], "", Color.orange, Color.BLACK);

		fields[11] = new GUI_Street(titles[11], "", titles[11], "", Color.green, Color.BLACK);

		fields[12] = new GUI_Street(titles[12], "", titles[12], "", Color.WHITE, Color.black);

		fields[13] = new GUI_Street(titles[13], "", titles[13], "", Color.green, Color.BLACK);

		fields[14] = new GUI_Street(titles[14], "", titles[14], "", Color.green, Color.BLACK);

		fields[15] = new GUI_Street(titles[15], "", titles[15], "", Color.white, Color.BLACK);

		fields[16] = new GUI_Street(titles[16], "", titles[16], "", Color.gray, Color.white);

		fields[17] = new GUI_Chance(titles[17], "", titles[17], Color.white, Color.BLACK);

		fields[18] = new GUI_Street(titles[18], "", titles[18],"", Color.gray, Color.white);

		fields[19] = new GUI_Street(titles[19], "", titles[19], "", Color.gray, Color.white);

		fields[20] = new GUI_Street(titles[20], "", titles[20], "", Color.white, Color.BLACK);

		fields[21] = new GUI_Street(titles[21], "", titles[21],"", Color.red, Color.white);

		fields[22] = new GUI_Chance(titles[22], "", titles[22], Color.white, Color.black);

		fields[23] = new GUI_Street(titles[23], "", titles[23], "", Color.red, Color.WHITE);
		
		fields[24] = new GUI_Street(titles[24], "" , titles[24], "", Color.red, Color.white);

		fields[25] = new GUI_Street(titles[25], "", titles[25], "", Color.white, Color.black);
		
		fields[26] = new GUI_Street(titles[26], "", titles[26],"", Color.white, Color.BLACK);

		fields[27] = new GUI_Street(titles[27], "", titles[27], "", Color.white, Color.BLACK);

		fields[28] = new GUI_Street(titles[28], "", titles[28], "", Color.white, Color.BLACK);

		fields[29] = new GUI_Street(titles[29], "", titles[29], "", Color.WHITE, Color.BLACK);

		fields[30] = new GUI_Street(titles[30], "", titles[30], "", Color.white, Color.BLACK);

		fields[31] = new GUI_Street(titles[31], "", titles[31], "", Color.yellow, Color.BLACK);

		fields[32] = new GUI_Street(titles[32], "", titles[32],"", Color.yellow, Color.BLACK);

		fields[33] = new GUI_Chance(titles[33], "", titles[33], Color.white, Color.BLACK);

		fields[34] = new GUI_Street(titles[34], "", titles[34], "", Color.yellow, Color.BLACK);

		fields[35] = new GUI_Street(titles[35], "", titles[35], "", Color.WHITE, Color.black);

		fields[36] = new GUI_Chance(titles[36], "", titles[36], Color.white, Color.BLACK);

		fields[37] = new GUI_Street(titles[37], "", titles[37], "", Color.red, Color.BLACK);

		fields[38] = new GUI_Street(titles[38], "", titles[38],"", Color.white, Color.BLACK);
		
		fields[39] = new GUI_Street(titles[39], "", titles[39],"", Color.red, Color.BLACK);

		
		gui = new GUI(fields, Color.LIGHT_GRAY);			

		long t1 = System.currentTimeMillis();
		System.out.println("GUI'en starter på "+ (t1-t0) + "ms");
		
//		System.out.println(Arrays.toString(titles));
		numberOfPlayers = gui.getUserInteger("Indtast antal spillere", 2, 6);
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
				guiPlayers[0] = new GUI_Player(names[0],30000, one);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car two = new GUI_Car();
				two.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],30000, two);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				break;
				
			case 3:
				GUI_Car three = new GUI_Car();
				three.setPrimaryColor(Color.RED);
				guiPlayers[0] = new GUI_Player(names[0],30000, three);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car four = new GUI_Car();
				four.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],30000, four);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				
				GUI_Car five = new GUI_Car();
				five.setPrimaryColor(Color.WHITE);
				guiPlayers[2] = new GUI_Player(names[2],30000, five);
				gui.addPlayer(guiPlayers[2]);
				fields[0].setCar(guiPlayers[2], true);

				break;
				
			case 4:
				GUI_Car six = new GUI_Car();
				six.setPrimaryColor(Color.RED);
				guiPlayers[0] = new GUI_Player(names[0],30000, six);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car seven = new GUI_Car();
				seven.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],30000, seven);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				
				GUI_Car eight = new GUI_Car();
				eight.setPrimaryColor(Color.WHITE);
				guiPlayers[2] = new GUI_Player(names[2],30000, eight);
				gui.addPlayer(guiPlayers[2]);
				fields[0].setCar(guiPlayers[2], true);
				
				GUI_Car nine = new GUI_Car();
				nine.setPrimaryColor(Color.cyan);
				guiPlayers[3] = new GUI_Player(names[3],30000, nine);
				gui.addPlayer(guiPlayers[3]);
				fields[0].setCar(guiPlayers[3], true);
				
				break;
			
			case 5:
				GUI_Car ten = new GUI_Car();
				ten.setPrimaryColor(Color.RED);
				guiPlayers[0] = new GUI_Player(names[0],30000, ten);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car eleven = new GUI_Car();
				eleven.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],30000, eleven);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				
				GUI_Car twelve = new GUI_Car();
				twelve.setPrimaryColor(Color.WHITE);
				guiPlayers[2] = new GUI_Player(names[2],30000, twelve);
				gui.addPlayer(guiPlayers[2]);
				fields[0].setCar(guiPlayers[2], true);
				
				GUI_Car thirteen = new GUI_Car();
				thirteen.setPrimaryColor(Color.cyan);
				guiPlayers[3] = new GUI_Player(names[3],30000, thirteen);
				gui.addPlayer(guiPlayers[3]);
				fields[0].setCar(guiPlayers[3], true);
				
				GUI_Car fourteen = new GUI_Car();
				fourteen.setPrimaryColor(Color.black);
				guiPlayers[4] = new GUI_Player(names[4],30000, fourteen);
				gui.addPlayer(guiPlayers[4]);
				fields[0].setCar(guiPlayers[4], true);
				
				break;
				
			case 6:
				GUI_Car fifteen = new GUI_Car();
				fifteen.setPrimaryColor(Color.RED);
				guiPlayers[0] = new GUI_Player(names[0],30000, fifteen);
				gui.addPlayer(guiPlayers[0]);
				fields[0].setCar(guiPlayers[0], true);
				
				GUI_Car sixteen = new GUI_Car();
				sixteen.setPrimaryColor(Color.yellow);
				guiPlayers[1] = new GUI_Player(names[1],30000, sixteen);
				gui.addPlayer(guiPlayers[1]);
				fields[0].setCar(guiPlayers[1], true);
				
				GUI_Car seventeen = new GUI_Car();
				seventeen.setPrimaryColor(Color.WHITE);
				guiPlayers[2] = new GUI_Player(names[2],30000, seventeen);
				gui.addPlayer(guiPlayers[2]);
				fields[0].setCar(guiPlayers[2], true);
				
				GUI_Car eighteen = new GUI_Car();
				eighteen.setPrimaryColor(Color.cyan);
				guiPlayers[3] = new GUI_Player(names[3],30000, eighteen);
				gui.addPlayer(guiPlayers[3]);
				fields[0].setCar(guiPlayers[3], true);
				
				GUI_Car nineteen = new GUI_Car();
				nineteen.setPrimaryColor(Color.black);
				guiPlayers[4] = new GUI_Player(names[4],30000, nineteen);
				gui.addPlayer(guiPlayers[4]);
				fields[0].setCar(guiPlayers[4], true);
				
				GUI_Car twenty = new GUI_Car();
				twenty.setPrimaryColor(Color.yellow);
				guiPlayers[5] = new GUI_Player(names[5],30000, twenty);
				gui.addPlayer(guiPlayers[5]);
				fields[0].setCar(guiPlayers[5], true);
				
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
		title = new String[40];

		for (int i = 0; i < 40; i++) {
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
