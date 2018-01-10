package entity;

import gamelogic.Game;

public class GameBoard {
	public static void fillFields() {
		int field[][];
		field = new int [40][10];
		for (int i = 0; i < 40; i++) {
			field[i][0] = i; 
			switch (i) {
			case 0:
				break;
			case 1:
				field[i][1] = 50;
				field[i][2] = 1;
				field[i][5] = 1;
				field[i][6] = 1200;
				field[i][7] = 600;
				break;
			case 2:
				//				Chance felt
				break;
			case 3:
				field[i][1] = 50;
				field[i][2] = 1;
				field[i][5] = 1;
				field[i][6] = 1200;
				field[i][7] = 600;
				break;
			case 4:
				//				Indkomst skat felt
				break;
			case 5:
				//				Rederi felt
				//				Hvis man ejer 2 så skal rent være 1000 og for hvert mere man ejer så skal rent dobbles
				field[i][1] = 500;
				field[i][2] = 9;
				field[i][5] = 1;
				field[i][6] = 4000;
				field[i][7] = 2000;
				break;
			case 6:
				field[i][1] = 100;
				field[i][2] = 2;
				field[i][5] = 1;
				field[i][6] = 2000;
				field[i][7] = 1000;
				break;
			case 7:
				//				Chance felt
				break;
			case 8:
				field[i][1] = 100;
				field[i][2] = 2;
				field[i][5] = 1;
				field[i][6] = 2000;
				field[i][7] = 1000;
				break;
			case 9:
				field[i][1] = 150;
				field[i][2] = 2;
				field[i][5] = 1;
				field[i][6] = 2400;
				field[i][7] = 1200;
				break;
			case 10:
				//				Fængslet
				break;
			case 11:
				field[i][1] = 200;
				field[i][2] = 3;
				field[i][5] = 1;
				field[i][6] = 2800;
				field[i][7] = 1400;
				break;
			case 12:
				//				Tuborg bryggeri
				//				Husk der skal ganges med øjne / dicesum og ganges med 2 hvis begge bryggerier ejes 
				field[i][1] = 100;
				field[i][2] = 10;
				field[i][5] = 1;
				field[i][6] = 3000;
				field[i][7] = 1500;
				break;
			case 13:
				field[i][1] = 200;
				field[i][2] = 3;
				field[i][5] = 1;
				field[i][6] = 2800;
				field[i][7] = 1400;
				break;
			case 14:
				field[i][1] = 250;
				field[i][2] = 3;
				field[i][5] = 1;
				field[i][6] = 3200;
				field[i][7] = 1600;
				break;
			case 15:
				//				Rederi felt
				//				Hvis man ejer 2 så skal rent være 1000 og for hvert mere man ejer så skal rent dobbles
				field[i][1] = 500;
				field[i][2] = 9; 
				field[i][5] = 1;
				field[i][6] = 4000;
				field[i][7] = 2000;
				break;
			case 16:
				field[i][1] = 300;
				field[i][2] = 4;
				field[i][5] = 1;
				field[i][6] = 3600;
				field[i][7] = 1800;
				break;
			case 17:
				//				Chance felt
				break;
			case 18:
				field[i][1] = 300;
				field[i][2] = 4;
				field[i][5] = 1;
				field[i][6] = 3600;
				field[i][7] = 1800;
				break;
			case 19:
				field[i][1] = 350;
				field[i][2] = 4;
				field[i][5] = 1;
				field[i][6] = 4000;
				field[i][7] = 2000;
				break;
			case 20:
				//				Helle felt
				break;
			case 21:
				field[i][1] = 350;
				field[i][2] = 5;
				field[i][5] = 1;
				field[i][6] = 4400;
				field[i][7] = 2200;
				break;
			case 22:
				//				Chance felt
				break;
			case 23:
				field[i][1] = 350;
				field[i][2] = 5;
				field[i][5] = 1;
				field[i][6] = 4400;
				field[i][7] = 2200;
				break;
			case 24:
				field[i][1] = 400;
				field[i][2] = 5;
				field[i][5] = 1;
				field[i][6] = 4800;
				field[i][7] = 2400;
				break;
			case 25:
				//				Rederi
				//				Hvis man ejer 2 så skal rent være 1000 og for hvert mere man ejer så skal rent dobbles
				field[i][1] = 500;
				field[i][2] = 9;
				field[i][5] = 1;
				field[i][6] = 4000;
				field[i][7] = 2000;
				break;
			case 26:
				field[i][1] = 450;
				field[i][2] = 6;
				field[i][5] = 1;
				field[i][6] = 5200;
				field[i][7] = 2600;
				break;
			case 27:
				field[i][1] = 450;
				field[i][2] = 6;
				field[i][5] = 1;
				field[i][6] = 5200;
				field[i][7] = 2600;
				break;
			case 28:
				//				Carlsberg bryggeri
				//				Husk der skal ganges med øjne / dicesum og ganges med 2 hvis begge bryggerier ejes 
				field[i][1] = 100;
				field[i][2] = 10; 
				field[i][5] = 1;
				field[i][6] = 3000;
				field[i][7] = 1500;
				break;
			case 29:
				field[i][1] = 500;
				field[i][2] = 6;
				field[i][5] = 1;
				field[i][6] = 5600;
				field[i][7] = 2800;
				break;
			case 30:
				//				De fængsles felt
				break;
			case 31:
				field[i][1] = 550;
				field[i][2] = 7;
				field[i][5] = 1;
				field[i][6] = 6000;
				field[i][7] = 3000;
				break;
			case 32:
				field[i][1] = 550;
				field[i][2] = 7;
				field[i][5] = 1;
				field[i][6] = 6000;
				field[i][7] = 3000;
				break;
			case 33:
				//				Chance felt
				break;
			case 34:
				field[i][1] = 600;
				field[i][2] = 7;
				field[i][5] = 1;
				field[i][6] = 6400;
				field[i][7] = 3200;
				break;
			case 35:
				//				Rederi
				//				Hvis man ejer 2 så skal rent være 1000 og for hvert mere man ejer så skal rent dobbles
				field[i][1] = 500;
				field[i][2] = 9;
				field[i][5] = 1;
				field[i][6] = 4000;
				field[i][7] = 2000;
				break;
			case 36:
				//				Chance felt
				break;
			case 37:
				field[i][1] = 700;
				field[i][2] = 8;
				field[i][5] = 1;
				field[i][6] = 7000;
				field[i][7] = 3500;
				break;
			case 38:
				//				Ekstraordinær statsskat
				break;
			case 39:
				field[i][1] = 1000;
				field[i][2] = 8;
				field[i][5] = 1;
				field[i][6] = 8000;
				field[i][7] = 4000;
				break;
			default:
				break;
			}
		}
		Game.setFields(field);
	}
	
	//Multiply rent by x
	public static void adjustPrices() {
		for (int i = 0; i < 40; i++) {
			Game.getFields()[i][1] = Game.getFields()[i][1] * 10; //Adjust here
		}
	}
}
