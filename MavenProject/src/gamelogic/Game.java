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
import entity.HousePrice;

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
				switch (GUI_GUI.gui.getUserSelection("                                            Det er: " + ListOfPlayers.getPlayers(whosTurn).getName() + "'s tur, vælg hvad du vil fortage dig", "Kast", "Byg huse/hotel", "Pantsæt grunde", "Genkøb", "Indbyrdes handel", "Sælg huse")) {
				case "Kast":
					System.out.println("1");
					dice.roll();
					turn.updateTurn(dice.getdie1(), dice.getdie2(), ListOfPlayers.getPlayers(whosTurn));
					break;
				case "Byg huse/hotel":
					System.out.println("2");
					if (turn.LegalHouse().length != 0) {
						turn.buyBuildings(turn.titleToInt(turn.chooseHouse()));
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke alle grunde i denne farve endnu");
					}
					break;
				case "Pantsæt grunde":
					System.out.println("3");
					if (turn.pawnableFields().length != 0) {							
						turn.setPawned(turn.titleToInt(turn.choosePawn()));
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke nogen grunde");
					}
					break;
				case "Genkøb":
					System.out.println("4");
					if (turn.pawnedFields().length != 0) {							
						turn.rebuy(turn.titleToInt(turn.choosePawned()));
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke nogen pantsatte grunde");
					}
					break;
				case "Indbyrdes handel":
					System.out.println("5");
					if (turn.opponentsFields().length != 0) {							
						turn.buyUsed(turn.titleToInt(turn.chooseProperty()));
					} else {
						GUI_GUI.gui.showMessage("                                            Dine modstandere ejer ikke nogen grunde");
					}
					break;
				case "Sælg huse":
					System.out.println("5");
					if (turn.LegalHouseSale().length != 0) {							
						turn.sellBuildings(turn.titleToInt(turn.chooseSellHouse()));
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke nogen huse endnu");
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
			GUI_GUI.gui.showMessage("                                            Du er røget i fængsel");

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
			} else {
				handleTurn(die1, die2, player);
			}
		} else {
			handleTurn(die1, die2, player);
			if (whosTurn == GUI_GUI.getNumberOfPlayers()) {
				whosTurn = 1;
			}
			else {
				whosTurn++;
			}
		}
	}

	public void handleTurn(int die1, int die2, Player player) {
		if(ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==true) {
			Jail(die1, die2);
			playerDied();
		}
		if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(whosTurn).isJailed()==false) {
			movePlayer(player, die1, die2);
			handleField(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player);
			goToJail();
			playerDied();
		}
	}

	public void playerDied() {
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
			GUI_GUI.gui.showMessage("Spilleren " + whosTurn + " er død");
			NumberOfDeadPlayers++;	
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
			//Update whosTurn's players balance on GUI
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
		}
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
		ListOfPlayers.getPlayers(whosTurn).setCurrentField(nextField);

		//Move player on GUI
		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
	}

	public boolean ownsGroupFields(int whosturn, int color) {
		int tempOwner = 0;
		int tempruns = 0;
		for (int j = 0; j < 40; j++) {
			if (getFields()[j][2] == color && Fields[j][5] == 1) {
				if (Fields[j][4] == whosturn) {
					tempOwner++;
				}
				tempruns++;
			}
		}
		if (tempOwner == tempruns) {
			return true;			
		} else {
			return false;
		}
	}

	//Skal tjekkes at den virker
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
				}
				tempruns++;
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
			} else if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][8] == 1) {
				//Field is pawned
				
			} else {
				payRent(field);
			}
		} else if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][5] == 0) {

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
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-ListOfPlayers.getPlayers(whosTurn).getTax());
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

	private void payRent(int field) {
		if (doubleRent(field) && Fields[field][9] == 0) {
			//Multiply rent by 2
			if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]])) {
				setPawned(titleToInt(choosePawn()));							
			}
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-2 * (HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]]));
			ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(2 * (HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]]));

			//Update recievers balance on GUI
			GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
		} else {
			//Pay normal rent
			if (ListOfPlayers.getPlayers(whosTurn).isDead()==false && ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).isDead()== false) { //Er det nødvendigt at tjekke om ejeren er død?
				while(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]])) {
					setPawned(titleToInt(choosePawn()));	
				}
				if (ListOfPlayers.getPlayers(whosTurn).getBalance()<(HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]])) {
					ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
					ListOfPlayers.getPlayers(whosTurn).setBalance(0);
					//							changeOwnerToCreditor();
				}
				// Pay rent on shippingcompanies
				else if (Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][2]==9 && Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][8]==0) {
					// The owner of the shippingcompany only owns 1 shippingcompany
					if(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getShippingCompaniesOwned()==1) {
						ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]]));
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
					ListOfPlayers.getPlayers(whosTurn).setNewBalance(-(HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]]));
					ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setNewBalance(HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[field][9]]);	
				}
			}
			//Update recievers balance on GUI
			GUI_GUI.getGuiPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).setBalance(ListOfPlayers.getPlayers(Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4]).getBalance());
		}
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
			if(ListOfPlayers.getPlayers(whosTurn).getBalance()<=(HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][9]])) {
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
		return GUI_GUI.gui.getUserSelection("                                            Vælg hvilken grund du vil genkøbe", pawnedFields());
	}
	public String chooseProperty() {
		return GUI_GUI.gui.getUserSelection("                                            Vælg hvilken grund du vil pantsætte", opponentsFields());
	}
	
	public int choosePrice(int owner) {
		int offerPrice = 0;
		boolean accepted;
		offerPrice = GUI_GUI.gui.getUserInteger("                            Indtast dit bud på grunden, bemærk at 0 betyder fortryd");
		if (offerPrice == 0) {
			//Transaction denied
			accepted = false;
		} else {	
			while (offerPrice > ListOfPlayers.getPlayers(whosTurn).getBalance()) {
				GUI_GUI.gui.showMessage("                                            Du har ikke så mange penge, vælg et mindre beløb");
				offerPrice = GUI_GUI.gui.getUserInteger("                            Indtast dit bud på grunden, bemærk at 0 betyder fortryd");
			}
			accepted = GUI_GUI.gui.getUserLeftButtonPressed("                                            " + ListOfPlayers.getPlayers(owner).getName() + " vil du accepterer tilbuddet? ", "Accepter", "Afvis");
		}
		if (accepted && offerPrice != 0) {
			return offerPrice;
		} else {
			return 0;
		}
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
			if(whosTurn == getFields()[i][4] && getFields()[i][8] == 1) {
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
		if (hasHousesOnColor(fieldnumber)) {
			GUI_GUI.gui.showMessage("                            Du kan ikke pantsætte grunde med huse. Sælg dine huse først");
		} else {
			GUI_GUI.displayOwner(fieldnumber, "("+ListOfPlayers.getPlayers(whosTurn).getName()+")");
			Fields[fieldnumber][8] = 1;
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(Fields[fieldnumber][7]);
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
			if (Fields[fieldnumber][2] == 9) {
				ListOfPlayers.getPlayers(whosTurn).lostShippingCompany();			
		    }
		}
	}

	public void rebuy(int fieldnumber) {
		if (ListOfPlayers.getPlayers(whosTurn).getBalance() < 1.1 * Fields[fieldnumber][7]) {
			GUI_GUI.gui.showMessage("                                            Du har ikke råd til at genkøbe denne grund");
		} else {
			GUI_GUI.displayOwner(fieldnumber, ListOfPlayers.getPlayers(whosTurn).getName());
			Fields[fieldnumber][8] = 0;
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1.1 * Fields[fieldnumber][7]);
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
			if (Fields[fieldnumber][2] == 9) {
				ListOfPlayers.getPlayers(whosTurn).boughtShippingCompany();
			}
		}
	}
	
	public boolean hasHousesOnColor(int fieldnumber) {
		boolean hasHouses;
		int houses = 0;
		for (int j = 0; j < 40; j++) {
			if (getFields()[j][2] == Fields[fieldnumber][2] && Fields[j][5] == 1) {
				if (Fields[j][9] != 0) {
					houses++;
				}
			}
		}
		if (houses != 0) {
			hasHouses = true;
		} else {
			hasHouses = false;
		}
		return hasHouses;
	}

	public void buyUsed(int fieldnumber) {
		if (hasHousesOnColor(fieldnumber)) {
			GUI_GUI.gui.showMessage("Sælg alle huse på grunde med samme farve, som den valgte grund, før du kan lave indbyrdes handel");
		} else {
			int decidedPrice = choosePrice(Fields[fieldnumber][4]);
			if (decidedPrice == 0) {
				//Transaction denied
				GUI_GUI.gui.showMessage("                                       	     Handlen blev afvist");
			} else {
				//Transaction confirmed
				ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1 * decidedPrice);
				ListOfPlayers.getPlayers(Fields[fieldnumber][4]).setNewBalance(decidedPrice);
				GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());	//Bug around here with GUI balance
				GUI_GUI.getGuiPlayers(Fields[fieldnumber][4]).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
				if (Fields[fieldnumber][2] == 9) {
					ListOfPlayers.getPlayers(Fields[fieldnumber][4]).lostShippingCompany();
					ListOfPlayers.getPlayers(whosTurn).boughtShippingCompany();
				}
				Fields[fieldnumber][4] = whosTurn;
				if (Fields[fieldnumber][8] != 0) {
					GUI_GUI.displayOwner(fieldnumber, "(" + ListOfPlayers.getPlayers(whosTurn).getName() + ")");  //Show after confirmation
				} else {
					GUI_GUI.displayOwner(fieldnumber, ListOfPlayers.getPlayers(whosTurn).getName()); 
				}
			}
		}
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

	public String chooseSellHouse() {
		return GUI_GUI.gui.getUserSelection("                                            Hvor vil du sælge et hus?", LegalHouseSale() );
	}
	public String [] LegalHouse() {
		String [] Fields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if((whosTurn == getFields()[i][4]) && ownsGroupFields(whosTurn, getFields()[i][2])) {
				//				System.out.println(getFields()[i][0]);
				Fields[i] = "" + getFields()[i][0];
				if(Fields[i] != null) {
					Fields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
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
	
	public String [] LegalHouseSale() {
		String [] Fields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {

			if((whosTurn == getFields()[i][4]) && getFields()[i][9]>0) {
				//					&& !(getFields()[i][10] == 0)) {
				//				System.out.println(getFields()[i][0]);
				Fields[i] = "" + getFields()[i][0];
				if(Fields[i] != null) {
					Fields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
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
	public void sellBuildings(int fieldnumber) {
		if (Fields[fieldnumber][9] <= 4 && Fields[fieldnumber][9] > 0) {
			Fields[fieldnumber][9]--;
			GUI_GUI.displayHouses(fieldnumber, Fields[fieldnumber][9]);  //Remove house on GUI
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(HousePrice.getHousePriceInt()[fieldnumber]/2);
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
			System.out.println("Huset blev solgt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else if(Fields[fieldnumber][9] == 5) {
			Fields[fieldnumber][9]--;
			GUI_GUI.removeHotel(fieldnumber);  // Remove hotel on GUI
			GUI_GUI.displayHouses(fieldnumber, Fields[fieldnumber][9]);
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(HousePrice.getHousePriceInt()[fieldnumber]/2);
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
			System.out.println("Hotelet blev købt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else {
			//Tell user they can't sell any more houses
			GUI_GUI.gui.showMessage("                                            Du kan ikke sælge flere huse");
		}
	}
	
	public boolean hasPawnedOnColor(int fieldnumber) {
		boolean hasPawned;
		int pawned = 0;
		for (int j = 0; j < 40; j++) {
			if (getFields()[j][2] == Fields[fieldnumber][2] && Fields[j][5] == 1) {
				if (Fields[j][8] != 0) {
					pawned++;
				}
			}
		}
		if (pawned != 0) {
			hasPawned = true;
		} else {
			hasPawned = false;
		}
		return hasPawned;
	}

	public void buyBuildings(int fieldnumber) {
		if (Fields[fieldnumber][8] != 0) {
			GUI_GUI.gui.showMessage("                                            Du kan ikke købe huse på en pantsat grund");
		} else if (hasPawnedOnColor(fieldnumber)) {
			GUI_GUI.gui.showMessage("                 Du kan ikke købe huse, da en af dine grunde i denne farve er pantsat");
		} else if (ListOfPlayers.getPlayers(whosTurn).getBalance() < HousePrice.getHousePriceInt()[fieldnumber]) {
			GUI_GUI.gui.showMessage("                                            Du har ikke råd til at købe huse på denne grund");
		} else if (Fields[fieldnumber][9] < 4) {
			Fields[fieldnumber][9]++;
			GUI_GUI.displayHouses(fieldnumber, Fields[fieldnumber][9]);  //Set house on GUI
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1 * HousePrice.getHousePriceInt()[fieldnumber]);
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
			System.out.println("Huset blev købt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else if(Fields[fieldnumber][9] == 4) {
			Fields[fieldnumber][9]++;
			GUI_GUI.displayHotel(fieldnumber);  //Set house on GUI
			ListOfPlayers.getPlayers(whosTurn).setNewBalance(-1 * HousePrice.getHousePriceInt()[fieldnumber]);
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(ListOfPlayers.getPlayers(whosTurn).getBalance());
			System.out.println("Hotelet blev købt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else {
			//Tell user they can't buy any more houses
			GUI_GUI.gui.showMessage("                                            Du kan ikke købe flere huse");
		}
	}
	
	public String[] opponentsFields() {
		String [] Fields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if(!(whosTurn == getFields()[i][4]) && getFields()[i][3] == 1) {
				//				System.out.println(getFields()[i][0]);
				Fields[i] = "" + getFields()[i][0];
				if(Fields[i] != null) {
					Fields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
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
}