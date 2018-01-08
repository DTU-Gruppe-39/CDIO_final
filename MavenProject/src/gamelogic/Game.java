package gamelogic;

import java.util.Arrays;
import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.Player;
import entity.TwoDice;
import entity.GameBoard;

public class Game {
	final static int MIN_POINTS = 0;
	private static int whosTurn;
	private static int NumberOfDeadPlayers;
	static int FieldNumb = 40;
	static int 	Attribute = 9;
	/**
	 * Field[][] har formen [FieldNumb][Attributes], hvor [Attributes] = [FieldNumb, rent, color, isOwned, owner, isOwnable, BuyPrice, PawnPrice, isPawned]
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
				if(ListOfPlayers.getPlayers(whosTurn).isJailed()==false && ListOfPlayers.getPlayers(whosTurn).isDead()==false) {
					GUI_GUI.gui.getUserButtonPressed("                                            Det er: " + ListOfPlayers.getPlayers(whosTurn).getName() + "'s tur", "Kast");					
				}
				TwoDice.roll();
				turn.updateTurn(TwoDice.getdie1(), TwoDice.getdie2(), ListOfPlayers.getPlayers(whosTurn));
			}
	}


	public void goToJail() {
		if(ListOfPlayers.getPlayers(whosTurn).getCurrentField()==30) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(true);
			ListOfPlayers.getPlayers(whosTurn).setCurrentField(10);
			GUI_GUI.getFields(30).removeAllCars();
			//Move player on GUI to prison
			GUI_GUI.getFields(10).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);

		}
	}
	
	public void Jail(int die1, int die2) {
		if (GUI_GUI.displayJailChoice()==false) {
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1000);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}
		
		if (die1 == die2) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}
		else if (ListOfPlayers.getPlayers(whosTurn).RoundsInJail==3) {
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1000);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}
//		indsæt kode til at komme ud af fængslet med chance kort 
		else {
			ListOfPlayers.getPlayers(whosTurn).StayedInJail();
			GUI_GUI.gui.setDice(die1, die2);
		}
	}
	


	//Everything needed between each turn
	public void updateTurn (int die1, int die2, Player player) {
		if(ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==true) {
			Jail(die1, die2);
		}
		if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==false) {
			movePlayer(player, die1 + die2);
			handleField(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player);
			goToJail();

			if (ListOfPlayers.getPlayers(whosTurn).getBalance() == 0){
				ListOfPlayers.getPlayers(whosTurn).setDead(true);
				for (int i=0; i<40; i++) {
					if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][6])) {
						if(whosTurn==Fields[i][4]) {
							Fields[i][4] = 0;
							Fields[i][3] = 0;
							removeOwner(i);
						}
					}
				}
				GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
				NumberOfDeadPlayers++;	
			}
		}
		if (die1 != die2) {
			if (whosTurn == GUI_GUI.getNumberOfPlayers()) {
				whosTurn = 1;
			}
			else {
				whosTurn++;
			}			
		}

	}

	public static void movePlayer(Player player, int diceSum) {
		GUI_GUI.gui.setDice(TwoDice.getdie1(), TwoDice.getdie2());
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
//Skrald kode
//	GameBoard fields = new GameBoard(); 
//		GameBoard.fillFields();
		//		System.out.println(Arrays.deepToString(Fields));
	

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
					if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
						pawnToPayDebt();							
					}
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
					ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));

					//Update recievers balance on GUI
					GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
				} else {
					//Pay normal rent
					if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).isDead()== false) {
						if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
							pawnToPayDebt();							
						}
						if (ListOfPlayers.getPlayers(whosTurn).getBalance()<(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
							ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
							ListOfPlayers.getPlayers(whosTurn).setBalance(0);
//							changeOwnerToCreditor();
						}
						else if(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][8]==0) {
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
			if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][5] == 1 && GUI_GUI.displayBuyChoice()==true) {
				if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][6]) {
					pawnToBuyField();
				}
				else {
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][6]));
					setOwner(ListOfPlayers.getPlayers(whosTurn));					
				}
			}
		}
		//Update whosTurn's players balance on GUI
		GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
	}

//	public void changeOwnerToCreditor() {
//		for (int i=0; i<40; i++) {			
//				if(whosTurn==Fields[i][4]) {
//					Fields[i][4] = Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4];
//					setCreditorOwner(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]));
//				}							
//		}
//	}

	public void pawnToPayDebt() {
		for (int i=0; i<40; i++) {
			if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
				if(whosTurn==Fields[i][4] && Fields[i][8]==0) {
					Fields[i][8] = 1;
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(Fields[i][7]);
					setPawned(i);
				}
			}
		}
	}
	
	public void pawnToBuyField() {
		for (int i=0; i<40; i++) {
			if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][6])) {
				if(whosTurn==Fields[i][4] && Fields[i][8]==0) {
					Fields[i][8] = 1;
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(Fields[i][7]);
					setPawned(i);
				}
			}
		}
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
//		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		GUI_GUI.displayOwner(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player.getName());
	}
	
//	public void setCreditorOwner(Player player) {
//		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4] = whosTurn;
//		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][3] = 1;
////		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
//		GUI_GUI.displayOwner(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), "( " + player.getName() + " )");
//	}
	
	public void setPawned(int fieldnumber) {
//		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		GUI_GUI.displayOwner(fieldnumber, "( "+ListOfPlayers.getPlayers(whosTurn).getName()+")");
	}
	
	public void removeOwner(int fieldnumber) {
		GUI_GUI.displayOwner(fieldnumber, " ");
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
