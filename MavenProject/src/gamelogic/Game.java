package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.Player;
import entity.TwoDice;

public class Game {
	final static int MIN_POINTS = 0;
	private static int whosTurn;

	static int FieldNumb = 24;
	static int 	Attribute = 6;
	/**
	 * Field[][] har formen [FieldNumb][Attributes], hvor [Attributes] = [FieldNumb, rent, color, isOwned, owner, isOwnable]
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

		
			while (ListOfPlayers.getPlayers(1).isDead() == false && ListOfPlayers.getPlayers(2).isDead() == false && ListOfPlayers.getPlayers(3).isDead() == false && ListOfPlayers.getPlayers(4).isDead() == false) {
				Game turn = new Game();
				GUI_GUI.gui.getUserButtonPressed("                                            Det er: " + ListOfPlayers.getPlayers(whosTurn).getName() + "'s tur", "Kast");
				TwoDice.roll();
				turn.updateTurn(dice.getdie1(), ListOfPlayers.getPlayers(whosTurn));
			}
	}
	
	
	public void goToJail() {
		if(ListOfPlayers.getPlayers(whosTurn).getCurrentField()==18) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(true);
			ListOfPlayers.getPlayers(whosTurn).setCurrentField(6);
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1);
			GUI_GUI.getFields(18).removeAllCars();
			//Move player on GUI to prison
			GUI_GUI.getFields(6).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);

		}
	}


	//Everything needed between each turn
	public void updateTurn (int diceSum, Player player) {
		ListOfPlayers.getPlayers(whosTurn).setJailed(false);
		movePlayer(player, diceSum);
		handleField(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player);
		goToJail();

		if (ListOfPlayers.getPlayers(whosTurn).getBalance() == 0){
			ListOfPlayers.getPlayers(whosTurn).setDead(true);
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
		//If above 24, then modulus 24
		nextField += currField + diceSum;
		if (nextField > 23) {
			nextField = (currField + diceSum) % 24;
			player.setNewBalance(2);
		}
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
		ListOfPlayers.getPlayers(whosTurn).setCurrentField(nextField);
		
		//Move player on GUI
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
	}

	public static void fillFields() {
		int field[][];
		field = new int [24][6];
		for (int i = 0; i < 24; i++) {
			field[i][0] = i; 
			switch (i) {
			case 0:
				break;
			case 1:
			case 2:
				field[i][1] = 1;
				field[i][2] = 1;
				field[i][5] = 1;
				break;
			case 3:
				break;
			case 4:
			case 5:
				field[i][1] = 1;
				field[i][2] = 2;
				field[i][5] = 1;
				break;
			case 6:
				break;
			case 7:
			case 8:
				field[i][1] = 2;
				field[i][2] = 3;
				field[i][5] = 1;
				break;
			case 9:
				break;
			case 10:
			case 11:
				field[i][1] = 2;
				field[i][2] = 4;
				field[i][5] = 1;
				break;
			case 12:
				break;
			case 13:
			case 14:
				field[i][1] = 3;
				field[i][2] = 5;
				field[i][5] = 1;
				break;
			case 15:
				break;
			case 16:
			case 17:
				field[i][1] = 3;
				field[i][2] = 6;
				field[i][5] = 1;
				break;
			case 18:
				break;
			case 19:
			case 20:
				field[i][1] = 4;
				field[i][2] = 7;
				field[i][5] = 1;
				break;
			case 21:
				break;
			case 22:
			case 23:
				field[i][1] = 5;
				field[i][2] = 8;
				field[i][5] = 1;
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

					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
					ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
					
					//Update recievers balance on GUI
					GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
				} else {
					//Pay normal rent
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
					ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]);
					
					//Update recievers balance on GUI
					GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
				}
			}

		} else {
			//Buy field if it is ownable
			if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][5] == 1) {
				ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
				setOwner(ListOfPlayers.getPlayers(whosTurn));
			}
		}
		//Update whosTurn's players balance on GUI
		GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
	}

	public void setOwner(Player player) {
		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4] = whosTurn;
		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][3] = 1;
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
	}
	
	public int getWhosTurn() {
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
