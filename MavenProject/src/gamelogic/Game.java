package gamelogic;

import java.util.Arrays;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.Player;
import entity.TwoDice;

public class Game {
	final static int MIN_POINTS = 0;
	private static int whosTurn;
	private static int NumberOfDeadPlayers;
	static int FieldNumb = 40;
	static int 	Attribute = 8;
	/**
	 * Field[][] har formen [FieldNumb][Attributes], hvor [Attributes] = [FieldNumb, rent, color, isOwned, owner, isOwnable, BuyPrice, PawnPrice]
	 */
	static int Fields[][] = new int [FieldNumb][Attribute];  //simple array to determine what field the player is on.

	public static int[][] getFields() {
		return Fields;
	}

	public static void setFields(int[][] fields) {
		Fields = fields;
	}


	public static void gameLogic() {
		//Game logic

		//Randomize whosTurn
		whosTurn = (int) Math.ceil(Math.random() * GUI_GUI.getNumberOfPlayers());

		//Create dice
		TwoDice dice = new TwoDice();
		ListOfPlayers.addFunds(GUI_GUI.getNumberOfPlayers());
		
			while (GUI_GUI.getNumberOfPlayers()-1 == NumberOfDeadPlayers==false) {
				Game turn = new Game();
				GUI_GUI.gui.getUserButtonPressed("                                            Det er: " + ListOfPlayers.getPlayers(whosTurn).getName() + "'s tur", "Kast");
				TwoDice.roll();
				turn.updateTurn(dice.getdie1(), ListOfPlayers.getPlayers(whosTurn));
			}
	}


	public void goToJail() {
		if(ListOfPlayers.getPlayers(whosTurn).getCurrentField()==30) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(true);
			ListOfPlayers.getPlayers(whosTurn).setCurrentField(10);
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1);
			GUI_GUI.getFields(18).removeAllCars();
			//Move player on GUI to prison
			GUI_GUI.getFields(6).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);

		}
	}


	//Everything needed between each turn
	public void updateTurn (int diceSum, Player player) {
		if (ListOfPlayers.getPlayers(whosTurn).isDead()==false) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			movePlayer(player, diceSum);
			handleField(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player);
			goToJail();

			if (ListOfPlayers.getPlayers(whosTurn).getBalance() == 0){
				ListOfPlayers.getPlayers(whosTurn).setDead(true);
				NumberOfDeadPlayers++;	
			}
		}
		if (whosTurn == GUI_GUI.getNumberOfPlayers()) {
			whosTurn = 1;
		}
		else {
			whosTurn++;
		}

	}

	public static void movePlayer(Player player, int diceSum) {
		GUI_GUI.gui.setDie(diceSum);
		int nextField = 0;
		int currField;
		//Get current field of player
		currField = ListOfPlayers.getPlayers(whosTurn).getCurrentField();

		//Calculate next field with dice and current field
		//If above 40, then modulus 40
		nextField += currField + diceSum;
		if (nextField > 39) {
			nextField = (currField + diceSum) % 40;
			player.setNewBalance(4000);
		}
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
		ListOfPlayers.getPlayers(whosTurn).setCurrentField(nextField);

		//Move player on GUI
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
	}

	public static void fillFields() {
		int field[][];
		field = new int [40][8];
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
		setFields(field);
		//		System.out.println(Arrays.deepToString(Fields));
	}

	public boolean ownsBothFields() {

		if (ListOfPlayers.getPlayers(whosTurn).getCurrentField() % 3 == 1) {
			int otherField = Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField() + 1][4];
			return (otherField == Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]);
		}

		else if (ListOfPlayers.getPlayers(whosTurn).getCurrentField() % 3 == 2) {
			int otherField = Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField() - 1][4];
			return (otherField == Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]);
		}
		else {
			return false;
		}
	}

	//Update the balance depending on the field	
	//[Attributes] = [FieldNumb, rent, color, isOwned, owner, isOwnable]
	public void handleField (int field, Player name) {
		if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][3] == 1) {
			//Field is owned
			if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4] == whosTurn) {
				//Lands on his own field
			}
			else {
				if (ownsBothFields()) {
					//Multiply rent by 2
					for (int i=0; i<24; i++) {
						if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
							if(whosTurn==Fields[i][4]) {
								Fields[i][4]=0;
								Fields[i][3]=0;
								ListOfPlayers.getPlayers(whosTurn).setNewBalance(Fields[i][1]);
								removeOwner(i);
							}
						}
						else {
							break;
						}
					}
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
					ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));

					//Update recievers balance on GUI
					GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
				} else {
					//Pay normal rent
					if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).isDead()== false) {
						for (int i=0; i<24; i++) {
							if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
								if(whosTurn==Fields[i][4]) {
									Fields[i][4]=0;
									Fields[i][3]=0;
									ListOfPlayers.getPlayers(whosTurn).setNewBalance(Fields[i][1]);
									removeOwner(i);
								}
							}
							else {
								
								break;
							}
						}
						if (ListOfPlayers.getPlayers(whosTurn).getBalance()<(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
							ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
							ListOfPlayers.getPlayers(whosTurn).setBalance(0);
						}
						else {
							ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
							ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]);							
						}
					}
					//Update recievers balance on GUI
					GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
				}
			}
		}

		else {
			//Buy field if it is ownable
			if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][5] == 1) {
				ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
				setOwner(ListOfPlayers.getPlayers(whosTurn));
				
			}
		}
		//Update whosTurn's players balance on GUI
		GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
	}

//	private void payRent() {
//		if (ownsBothFields()) {
//			//Multiply rent by 2
//
//			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
//			ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
//			
//			//Update recievers balance on GUI
//			GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
//		} else {
//			//Pay normal rent
//			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
//			ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]);
//			
//			//Update recievers balance on GUI
//			GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
//		}
//	}

	public void setOwner(Player player) {
		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4] = whosTurn;
		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][3] = 1;
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		GUI_GUI.displayOwner(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), ListOfPlayers.getPlayers(whosTurn).getName());
	}
	public void removeOwner(int fieldnumber) {
		GUI_GUI.getFields(fieldnumber).setDescription("");
	}
	
	
	public static int getWhosTurn() {
		return whosTurn;
	}

	//Updates the GUI
	//			public void updateGUI (int field, Player player, int dice) {
	//				GUI_Test.gui.removeAllCars(player.getName());
	//				GUI_Test.gui.setCar(field, player.getName());
	//				GUI_Test.gui.setBalance(player.getName(), player.getBalance());
	//				GUI_Test.gui.setDie(dice);

	//Print text to GUI
	//				try {
	//					printText(field);
	//				} catch (IOException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				}
	//			}

}
