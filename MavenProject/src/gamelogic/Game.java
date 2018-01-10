package gamelogic;


import boundary.GUI_GUI;

import java.awt.List;
import java.util.Arrays;
import boundary.GUI_GUI;

import controller.ListOfPlayers;
import entity.Player;
import entity.TwoDice;
import controller.ChanceDeck;
import entity.GameBoard;

public class Game {
	final static int MIN_POINTS = 0;
	private static int whosTurn;
	private static int NumberOfDeadPlayers;
	private static int sameDice = 0;

	static int FieldNumb = 40;
	ChanceDeck deck = new ChanceDeck();
	static int 	Attribute = 10;
	/**
	 * Field[][] har formen [FieldNumb][Attributes], hvor [Attributes] = [FieldNumb, rent, color, isOwned, owner, isOwnable, buyPrice, pawnPrice, isPawned, buildings]
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


		//		//Create dice
		TwoDice dice = new TwoDice();

		ListOfPlayers.addFunds(GUI_GUI.getNumberOfPlayers());
		while (GUI_GUI.getNumberOfPlayers()-1 == NumberOfDeadPlayers==false) {
			Game turn = new Game();
			if(ListOfPlayers.getPlayers(whosTurn).isDead()==false) {
				switch (GUI_GUI.gui.getUserSelection("                                            Det er: " + ListOfPlayers.getPlayers(whosTurn).getName() + "'s tur, vælg hvad du vil fortage dig", "Kast", "Byg huse/hotel", "Pantsæt grunde", "Genkøb")) {
				case "Kast":
					System.out.println("1");
					dice.roll();
					turn.updateTurn(dice.getdie1(), dice.getdie2(), ListOfPlayers.getPlayers(whosTurn));
					break;
				case "Byg huse/hotel":
					System.out.println("2");
					if (turn.LegalHouse().length != 0) {
						turn.setHouse(turn.titleToInt(turn.chooseHouse()));
					}
					break;
				case "Pantsæt grunde":
					System.out.println("3");
					if (turn.pawnableFields().length != 0) {							
						turn.setPawned(turn.titleToInt(turn.choosePawn()));
					}
					break;
				case "Genkøb":
					System.out.println("4");
					if (turn.pawnedFields().length != 0) {							
						turn.rebuy(turn.titleToInt(turn.choosePawned()));
					}
					break;
				default:
					System.out.println("Selection not recognized");
					break;
				}
			}
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
		if (ListOfPlayers.getPlayers(whosTurn).getHaveJailCard()>0) {
			ListOfPlayers.getPlayers(whosTurn).setHaveJailCard(-1);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
			ChanceDeck.setJailInDeck(1);
		}

		else if (GUI_GUI.displayJailChoice()==false) {
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1000);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}

		else if (die1 == die2) {
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}
		else if (ListOfPlayers.getPlayers(whosTurn).RoundsInJail==3) {
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1000);
			ListOfPlayers.getPlayers(whosTurn).setJailed(false);
			ListOfPlayers.getPlayers(whosTurn).GotOutOfJail();
		}
		else {
			ListOfPlayers.getPlayers(whosTurn).StayedInJail();
			GUI_GUI.gui.setDice(die1, die2);
		}
	}



	//Everything needed between each turn
	public void updateTurn (int die1, int die2, Player player) {
		if (die1 == die2) {
			sameDice++;
			if (sameDice == 3) {
				tripleTurn(player);			
			}
		}	else {
			if(ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==true) {
				Jail(die1, die2);
			}
			if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==false) {
				movePlayer(player, die1, die2);
				handleField(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player);
				goToJail();

				if (ListOfPlayers.getPlayers(whosTurn).getBalance() == 0){
					ListOfPlayers.getPlayers(whosTurn).setDead(true);
					for (int i=0; i<40; i++) {
						if(whosTurn==Fields[i][4]) {
							Fields[i][4] = 0;
							Fields[i][3] = 0;
							Fields[i][8] = 0;
							removeOwner(i);
						}

					}
					GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
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
	}

	public void tripleTurn(Player player) {
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
		ListOfPlayers.getPlayers(whosTurn).setCurrentField(10);
		ListOfPlayers.getPlayers(whosTurn).setJailed(true);
		GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
		sameDice = 0;
		if (whosTurn == GUI_GUI.getNumberOfPlayers()) {
			whosTurn = 1;
		}
		else {
			whosTurn++;
		}
	}


	public static void movePlayer(Player player, int die1, int die2) {
		GUI_GUI.gui.setDice(die1, die2);
		int nextField = 0;
		int currField;
		int diceSum=0;
		//Get current field of player
		currField = ListOfPlayers.getPlayers(whosTurn).getCurrentField();
		diceSum= die1 + die2;
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

	public boolean ownsGroupFields(int whosturn) {
		int tempOwner = 0;
		int tempruns = 0;
		for (int j = 0; j < 40; j++) {
			for (int i = 1; i < 10; i++) {
				if (getFields()[j][2] == i && Fields[j][3] == 1) {
					if (Fields[j][4] == whosturn) {
						tempOwner++;
					}
					tempruns++;
				}
			}
		}
		if (tempOwner == tempruns) {
			return true;			
		} else {
			return false;
		}
	}

	public boolean doubleRent(int field) { //Skal ændres til feltet og ikke spilleren
		int Owner = Fields[field][4];
		int tempOwner = 0;
		int tempruns = 0;
		for (int j = 0; j < 40; j++) {
			if (Fields[j][2] == Fields[field][2]) {
				if (Fields[j][3] == 1) {
					if (Fields[j][4] == Owner) {
						tempOwner++;
					}
					tempruns++;
				}
			}
		}
		if (tempOwner == tempruns) {
			return true;			
		} else {
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
				if (doubleRent(field)) {
					//Multiply rent by 2
					if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
						setPawned(titleToInt(choosePawn()));							
					}
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
					ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(2 * (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));

					//Update recievers balance on GUI
					GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
				} else {
					//Pay normal rent
					if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).isDead()== false) {
						while(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
							setPawned(titleToInt(choosePawn()));	
						}
						if (ListOfPlayers.getPlayers(whosTurn).getBalance()<(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1])) {
							ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
							ListOfPlayers.getPlayers(whosTurn).setBalance(0);
							//							changeOwnerToCreditor();
						}
						// Pay rent on shippingcompanies
						else if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][2]==9 && Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][8]==0) {
							// The owner of the shippingcompany only owns 1 shippingcompany
							if(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getShippingCompaniesOwned()==1) {
								ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]));
								ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]);
							}
							// The owner of the shippingcompany owns 2 shippingcompany
							else if (ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getShippingCompaniesOwned()==2) {
								ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]*2));
								ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]*2);
							}
							// The owner of the shippingcompany owns 3 shippingcompany
							else if (ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getShippingCompaniesOwned()==3) {
								ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]*4));
								ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]*4);
							}
							// The owner of the shippingcompany owns 4 shippingcompany
							else if (ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getShippingCompaniesOwned()==4) {
								ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]*8));
								ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][1]*8);
							}

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

		else if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][5] == 0) {

			switch (ListOfPlayers.getPlayers(whosTurn).getCurrentField()) {
			case 0: break;
			case 2: this.deck.drawCard();
			GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
			break;
			case 4:  //betal 4000 eller 10%;
				ListOfPlayers.getPlayers(whosTurn).setPropertyValue();			
				ListOfPlayers.getPlayers(whosTurn).setNetWorth();
				ListOfPlayers.getPlayers(whosTurn).CalculateTax();
				if(GUI_GUI.displayTaxChoice()==false) {
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-ListOfPlayers.getPlayers(whosTurn).getTax());;
				}
				else {
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-4000);
				}
				break;
			case 7:	this.deck.drawCard();
			GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
			break;
			case 10:break;
			case 17:this.deck.drawCard();
			GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
			break;
			case 20:break;
			case 22:this.deck.drawCard();
			GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
			break;
			case 33:this.deck.drawCard();
			GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
			break;
			case 36:this.deck.drawCard();
			GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
			break;
			case 38: //betal skat
				ListOfPlayers.getPlayers(whosTurn).setNewBalance(-2000);
				break;
			default:
				break;
			}
		}

		else {
			//Buy field if it is ownable
			if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][5] == 1 && GUI_GUI.displayBuyChoice()==true) {
				if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][6]) {
					setPawned(titleToInt(choosePawn()));
				}
				else {
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][6]));
					setOwner(ListOfPlayers.getPlayers(whosTurn));	
					// Updates the amount of shippingcompanies a player owns after he bought one
					if((Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][2]==9)){
						ListOfPlayers.getPlayers(whosTurn).boughtShippingCompany();
					}
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

	public String choosePawn() {
		return GUI_GUI.gui.getUserSelection("                                            Vælg hvilken grund du vil pantsætte", pawnableFields());
	}
	public String choosePawned() {
		return GUI_GUI.gui.getUserSelection("                                            Vælg hvilken grund du vil pantsætte", pawnedFields());
	}

	public String[] pawnableFields() {
		String [] Fields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if(whosTurn == getFields()[i][4] && getFields()[i][8] == 0) {
				//				System.out.println(getFields()[i][0]);
				Fields[i] = "" + getFields()[i][0];
				if(Fields[i] != null) {
					Fields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
		//		System.out.println("Fields are " + Arrays.deepToString(Fields));
		//		System.out.println("size is " + size);

		refinedFields = new String[size];
		int temp = 0;
		for (int i=0; i<40; i++) {
			if(Fields[i] != null) {
				refinedFields[temp] = Fields[i];
				temp++;
			}
		}
		//		System.out.println("refined are " + Arrays.deepToString(refinedFields));		

		return refinedFields;
	}

	public String [] pawnedFields() {
		String [] Fields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if(whosTurn == getFields()[i][4] || getFields()[i][8] == 1) {
				Fields[i] = "" + getFields()[i][0];
				if(Fields[i] != null) {
					Fields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
		refinedFields = new String[size];
		int temp = 0;
		for (int i=0; i<40; i++) {
			if(Fields[i] != null) {
				refinedFields[temp] = Fields[i];
				temp++;
			}
		}
		return refinedFields;
	}

	public int titleToInt(String title) {
		int fieldNumber = 0;
		for (int i=0; i<40; i++) {
			if (title.equals(GUI_GUI.getTitles()[i])) {
				fieldNumber = i;
				break;
			}
		}

		return fieldNumber;
	}

	public void setPawned(int fieldnumber) {
		//		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		GUI_GUI.displayOwner(fieldnumber, "("+ListOfPlayers.getPlayers(whosTurn).getName()+")");
		Fields[fieldnumber][8] = 1;
		ListOfPlayers.getPlayers(whosTurn).setNewBalance(Fields[fieldnumber][7]);
		GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
	}

	public void rebuy(int fieldnumber) {
		GUI_GUI.displayOwner(fieldnumber, ListOfPlayers.getPlayers(whosTurn).getName());
		Fields[fieldnumber][8] = 0;
		ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1.1 * Fields[fieldnumber][7]);
		GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
	}

	public void removeOwner(int fieldnumber) {
		GUI_GUI.displayPrice(fieldnumber);
	}


	public static int getWhosTurn() {
		return whosTurn;
	}
	public String chooseHouse() {
		return GUI_GUI.gui.getUserSelection("                                            Hvor vil du købe hus?", LegalHouse() );
	}

	public String [] LegalHouse() {
		String [] Fields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {

			if((whosTurn == getFields()[i][4]) && ownsGroupFields(whosTurn))
				//					&& !(getFields()[i][10] == 0)) {
				//				System.out.println(getFields()[i][0]);
				Fields[i] = "" + getFields()[i][0];
			if(Fields[i] != null) {
				Fields[i] = GUI_GUI.getTitles()[i];
				size++;
			}
		}
		//}
		//		System.out.println(Arrays.deepToString(Fields));
		//		System.out.println(size);
		refinedFields = new String[size];
		int temp = 0;
		for (int i=0; i<40; i++) {
			if(Fields[i] != null) {
				refinedFields[temp] = Fields[i];
				temp++;
			}
		}
		//		System.out.println(Arrays.deepToString(refinedFields));		

		return refinedFields;
	}
	public void setHouse(int fieldnumber) {
		//		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		GUI_GUI.displayOwner(fieldnumber, "("+ListOfPlayers.getPlayers(whosTurn).getName()+")");
		Fields[fieldnumber][8] = 1;
		ListOfPlayers.getPlayers(whosTurn).setNewBalance(Fields[fieldnumber][7]);
		GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
	}

}
