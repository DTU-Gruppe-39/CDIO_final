package boundary;

import java.awt.Color;

import gui_fields.GUI_Brewery;
import gui_fields.GUI_Car;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Player;
import gui_fields.GUI_Refuge;
import gui_fields.GUI_Shipping;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_main.GUI;

public class GUI_Create {
	static GUI_Field[] fields;	
	private static GUI_Player[] guiPlayers;
	public static GUI_Field getFields(int index) {
		return fields[index];
	}
	public static void createGuiFields() {
		String[] subText = GUI_GUI.getSubText();
		String[] disc = GUI_GUI.getDisc();
		String[] titles = GUI_GUI.getTitles();
		fields = new GUI_Field[40];
//		 street = new GUI_Street[40];
		//	Opretter alle felterne
		fields[0] = new GUI_Start();
		fields[0].setBackGroundColor(Color.RED);
		fields[0].setForeGroundColor(Color.white);
		fields[0].setTitle(titles[0]);
		fields[0].setSubText(subText[0]);
		fields[0].setDescription(disc[0]);
		fields[1] = new GUI_Street(titles[1], subText[1], titles[1] + disc[1], "", Color.cyan, Color.black);
		fields[2] = new GUI_Chance(titles[2], subText[2], titles[2] + disc[2], Color.white, Color.BLACK);
		fields[3] = new GUI_Street(titles[3], subText[3], titles[3] + disc[3], "", Color.cyan, Color.BLACK);
		fields[4] = new GUI_Street(titles[4], subText[4], titles[4] + disc[4], "", Color.white, Color.BLACK);
		fields[5] = new GUI_Shipping();
		fields[5].setTitle(titles[5]);
		fields[5].setSubText(subText[5]);
		fields[5].setDescription(disc[5]);
		fields[5].setBackGroundColor(Color.blue);
		fields[5].setForeGroundColor(Color.white);
		fields[6] = new GUI_Street(titles[6], subText[6], titles[6] + disc[6], "", Color.pink, Color.BLACK);
		fields[7] = new GUI_Chance(titles[7], subText[7], titles[7] + disc[7], Color.white, Color.BLACK);
		fields[8] = new GUI_Street(titles[8], subText[8], titles[8] + disc[8], "", Color.pink, Color.BLACK);
		fields[9] = new GUI_Street(titles[9], subText[9], titles[9] + disc[9], "", Color.pink, Color.BLACK);
		fields[10] = new GUI_Jail();
		fields[10].setSubText(subText[10]);
		fields[10].setDescription(subText[10] + " / " + titles[10]);
		fields[11] = new GUI_Street(titles[11], subText[11], titles[11] + disc[11], "", Color.green, Color.BLACK);
		fields[12] = new GUI_Brewery();
		fields[12].setTitle(titles[12]);
		fields[12].setSubText(subText[12]);
		fields[12].setDescription(titles[12] + disc[12]);
		fields[12].setBackGroundColor(Color.white);
		fields[12].setForeGroundColor(Color.black);
		fields[13] = new GUI_Street(titles[13], subText[13], titles[13] + disc[13], "", Color.green, Color.BLACK);
		fields[14] = new GUI_Street(titles[14], subText[14], titles[14] + disc[14], "", Color.green, Color.BLACK);
		fields[15] = new GUI_Shipping();
		fields[15].setTitle(titles[15]);
		fields[15].setSubText(subText[15]);
		fields[15].setDescription(disc[15]);
		fields[15].setBackGroundColor(Color.blue);
		fields[15].setForeGroundColor(Color.white);
		fields[16] = new GUI_Street(titles[16], subText[16], titles[16] + disc[16], "", Color.gray, Color.white);
		fields[17] = new GUI_Chance(titles[17], subText[17], titles[17] + disc[17], Color.white, Color.BLACK);
		fields[18] = new GUI_Street(titles[18], subText[18], titles[18] + disc[18],"", Color.gray, Color.white);
		fields[19] = new GUI_Street(titles[19], subText[19], titles[19] + disc[19], "", Color.gray, Color.white);
		fields[20] = new GUI_Refuge();
		fields[20].setSubText(titles[20]);
		fields[20].setTitle(titles[20]);
		fields[20].setBackGroundColor(Color.WHITE);
		fields[20].setDescription(titles[20] + " - " + subText[20]);
		fields[21] = new GUI_Street(titles[21], subText[21], titles[21] + disc[21],"", Color.red, Color.white);
		fields[22] = new GUI_Chance(titles[22], subText[22], titles[22] + disc[22], Color.white, Color.black);
		fields[23] = new GUI_Street(titles[23], subText[23], titles[23] + disc[23], "", Color.red, Color.WHITE);
		fields[24] = new GUI_Street(titles[24], subText[24], titles[24] + disc[24], "", Color.red, Color.white);
		fields[25] = new GUI_Shipping();
		fields[25].setTitle(titles[25]);
		fields[25].setSubText(subText[25]);
		fields[25].setDescription(disc[25]);
		fields[25].setBackGroundColor(Color.blue);
		fields[25].setForeGroundColor(Color.white);
		fields[26] = new GUI_Street(titles[26], subText[26], titles[26] + disc[26],"", Color.white, Color.BLACK);
		fields[27] = new GUI_Street(titles[27], subText[27], titles[27] + disc[27], "", Color.white, Color.BLACK);
		fields[28] = new GUI_Brewery();
		fields[28].setTitle(titles[28]);
		fields[28].setSubText(subText[28]);
		fields[28].setDescription(titles[28] + disc[28]);
		fields[28].setBackGroundColor(Color.white);
		fields[28].setForeGroundColor(Color.black);
		fields[29] = new GUI_Street(titles[29], subText[29], titles[29] + disc[29], "", Color.white, Color.BLACK);
		fields[30] = new GUI_Jail();
		fields[30].setSubText(titles[30]);
		fields[30].setDescription(titles[30]);
		fields[31] = new GUI_Street(titles[31], subText[31], titles[31] + disc[31], "", Color.yellow, Color.BLACK);
		fields[32] = new GUI_Street(titles[32], subText[32], titles[32] + disc[32],"", Color.yellow, Color.BLACK);
		fields[33] = new GUI_Chance(titles[33], subText[33], titles[33] + disc[33], Color.white, Color.BLACK);
		fields[34] = new GUI_Street(titles[34], subText[34], titles[34] + disc[34], "", Color.yellow, Color.BLACK);
		fields[35] = new GUI_Shipping();
		fields[35].setTitle(titles[35]);
		fields[35].setSubText(subText[35]);
		fields[35].setDescription(disc[35]);
		fields[35].setBackGroundColor(Color.blue);
		fields[35].setForeGroundColor(Color.white);
		fields[36] = new GUI_Chance(titles[36], subText[36], titles[36] + disc[36], Color.white, Color.BLACK);
		fields[37] = new GUI_Street(titles[37], subText[37], titles[37] + disc[37], "", Color.magenta, Color.WHITE);
		fields[38] = new GUI_Street(titles[38], subText[38], titles[38] + disc[38],"", Color.white, Color.BLACK);
		fields[39] = new GUI_Street(titles[39], subText[39], titles[39] + disc[39],"", Color.magenta, Color.white);
		GUI_GUI.gui = new GUI(fields, Color.lightGray);
	}
	public static void guiCreatePlayerCar() {
		String[] names = GUI_GUI.getNames();
		GUI_Player[] guiplayers = new GUI_Player[GUI_GUI.getNumberOfPlayers()];
		switch(GUI_GUI.getNumberOfPlayers()) {
			case 2: 
				GUI_Car one = new GUI_Car();
				one.setPrimaryColor(Color.RED);
				guiplayers[0] = new GUI_Player(names[0],30000, one);
				GUI_GUI.getGui().addPlayer(guiplayers[0]);
				fields[0].setCar(guiplayers[0], true);			
				GUI_Car two = new GUI_Car();
				two.setPrimaryColor(Color.yellow);
				guiplayers[1] = new GUI_Player(names[1],30000, two);
				GUI_GUI.getGui().addPlayer(guiplayers[1]);
				fields[0].setCar(guiplayers[1], true);
				setGuiPlayers(guiplayers);
				break;
			case 3:
				GUI_Car three = new GUI_Car();
				three.setPrimaryColor(Color.RED);
				guiplayers[0] = new GUI_Player(names[0],30000, three);
				GUI_GUI.getGui().addPlayer(guiplayers[0]);
				fields[0].setCar(guiplayers[0], true);
				GUI_Car four = new GUI_Car();
				four.setPrimaryColor(Color.yellow);
				guiplayers[1] = new GUI_Player(names[1],30000, four);
				GUI_GUI.getGui().addPlayer(guiplayers[1]);
				fields[0].setCar(guiplayers[1], true);
				GUI_Car five = new GUI_Car();
				five.setPrimaryColor(Color.WHITE);
				guiplayers[2] = new GUI_Player(names[2],30000, five);
				GUI_GUI.getGui().addPlayer(guiplayers[2]);
				fields[0].setCar(guiplayers[2], true);
				setGuiPlayers(guiplayers);
				break;
			case 4:
				GUI_Car six = new GUI_Car();
				six.setPrimaryColor(Color.RED);
				guiplayers[0] = new GUI_Player(names[0],30000, six);
				GUI_GUI.getGui().addPlayer(guiplayers[0]);
				fields[0].setCar(guiplayers[0], true);
				GUI_Car seven = new GUI_Car();
				seven.setPrimaryColor(Color.yellow);
				guiplayers[1] = new GUI_Player(names[1],30000, seven);
				GUI_GUI.getGui().addPlayer(guiplayers[1]);
				fields[0].setCar(guiplayers[1], true);
				GUI_Car eight = new GUI_Car();
				eight.setPrimaryColor(Color.WHITE);
				guiplayers[2] = new GUI_Player(names[2],30000, eight);
				GUI_GUI.getGui().addPlayer(guiplayers[2]);
				fields[0].setCar(guiplayers[2], true);
				GUI_Car nine = new GUI_Car();
				nine.setPrimaryColor(Color.cyan);
				guiplayers[3] = new GUI_Player(names[3],30000, nine);
				GUI_GUI.getGui().addPlayer(guiplayers[3]);
				fields[0].setCar(guiplayers[3], true);
				setGuiPlayers(guiplayers);
				break;
			case 5:
				GUI_Car ten = new GUI_Car();
				ten.setPrimaryColor(Color.RED);
				guiplayers[0] = new GUI_Player(names[0],30000, ten);
				GUI_GUI.getGui().addPlayer(guiplayers[0]);
				fields[0].setCar(guiplayers[0], true);
				GUI_Car eleven = new GUI_Car();
				eleven.setPrimaryColor(Color.yellow);
				guiplayers[1] = new GUI_Player(names[1],30000, eleven);
				GUI_GUI.getGui().addPlayer(guiplayers[1]);
				fields[0].setCar(guiplayers[1], true);
				GUI_Car twelve = new GUI_Car();
				twelve.setPrimaryColor(Color.WHITE);
				guiplayers[2] = new GUI_Player(names[2],30000, twelve);
				GUI_GUI.getGui().addPlayer(guiplayers[2]);
				fields[0].setCar(guiplayers[2], true);
				GUI_Car thirteen = new GUI_Car();
				thirteen.setPrimaryColor(Color.cyan);
				guiplayers[3] = new GUI_Player(names[3],30000, thirteen);
				GUI_GUI.getGui().addPlayer(guiplayers[3]);
				fields[0].setCar(guiplayers[3], true);
				GUI_Car fourteen = new GUI_Car();
				fourteen.setPrimaryColor(Color.black);
				guiplayers[4] = new GUI_Player(names[4],30000, fourteen);
				GUI_GUI.getGui().addPlayer(guiplayers[4]);
				fields[0].setCar(guiplayers[4], true);
				setGuiPlayers(guiplayers);
				break;
			case 6:
				GUI_Car fifteen = new GUI_Car();
				fifteen.setPrimaryColor(Color.RED);
				guiplayers[0] = new GUI_Player(names[0],30000, fifteen);
				GUI_GUI.getGui().addPlayer(guiplayers[0]);
				fields[0].setCar(guiplayers[0], true);
				GUI_Car sixteen = new GUI_Car();
				sixteen.setPrimaryColor(Color.yellow);
				guiplayers[1] = new GUI_Player(names[1],30000, sixteen);
				GUI_GUI.getGui().addPlayer(guiplayers[1]);
				fields[0].setCar(guiplayers[1], true);
				GUI_Car seventeen = new GUI_Car();
				seventeen.setPrimaryColor(Color.WHITE);
				guiplayers[2] = new GUI_Player(names[2],30000, seventeen);
				GUI_GUI.getGui().addPlayer(guiplayers[2]);
				fields[0].setCar(guiplayers[2], true);
				GUI_Car eighteen = new GUI_Car();
				eighteen.setPrimaryColor(Color.cyan);
				guiplayers[3] = new GUI_Player(names[3],30000, eighteen);
				GUI_GUI.getGui().addPlayer(guiplayers[3]);
				fields[0].setCar(guiplayers[3], true);
				GUI_Car nineteen = new GUI_Car();
				nineteen.setPrimaryColor(Color.black);
				guiplayers[4] = new GUI_Player(names[4],30000, nineteen);
				GUI_GUI.getGui().addPlayer(guiplayers[4]);
				fields[0].setCar(guiplayers[4], true);
				GUI_Car twenty = new GUI_Car();
				twenty.setPrimaryColor(Color.green);
				guiplayers[5] = new GUI_Player(names[5],30000, twenty);
				GUI_GUI.getGui().addPlayer(guiplayers[5]);
				fields[0].setCar(guiplayers[5], true);
				setGuiPlayers(guiplayers);
				break;
			default: 
				break;
		}
	}
	public static void removeHouses(int fieldnumber) {
		if (fields[fieldnumber] instanceof GUI_Street) {
			GUI_Street street1 = (GUI_Street)fields[fieldnumber];
			street1.setHouses(0);
		}
	}
	public static void removeHotel(int fieldnumber) {
		if (fields[fieldnumber] instanceof GUI_Street) {
			GUI_Street street1 = (GUI_Street)fields[fieldnumber];
			street1.setHotel(false);
		}
	}
	public static void displayHouses(int fieldnumber, int houses) {
		if (fields[fieldnumber] instanceof GUI_Street) {
			GUI_Street street1 = (GUI_Street)fields[fieldnumber];
			street1.setHouses(houses);
		}
	}
	public static void displayHotel(int fieldnumber) {
		if (fields[fieldnumber] instanceof GUI_Street) {
			GUI_Street street1 = (GUI_Street)fields[fieldnumber];
			street1.setHotel(true);
		}
	}
	public static void displayOwner(int field, String name) {
		fields[field].setSubText(name);
	}
	public static void displayPrice(int field) {
		fields[field].setSubText(GUI_GUI.getSubText()[field]);
	}
	public static GUI_Player getGuiPlayers(int index) {
		return guiPlayers[index - 1];
	}
	public static void setGuiPlayers(GUI_Player[] player) {
		guiPlayers = player;
	}
}
