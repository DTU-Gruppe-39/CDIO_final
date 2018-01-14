package gamelogic;


import boundary.GUI_GUI; 

import java.awt.List;
import java.util.Arrays;

import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;

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


	public void movePlayer(Player player, int die1, int die2) {
		GUI_GUI.gui.setDice(die1, die2);
		int nextField = 0;
		int currField;
		int diceSum=0;
		//Get current field of player
		currField = currentPlayer().getCurrentField();
		diceSum= die1 + die2;
		//Calculate next field with dice and current field
		//If above 40, then modulus 40
		nextField += currField + diceSum;
		if (nextField > 39) {
			nextField = (currField + diceSum) % 40;
			player.setNewBalance(4000);
			//Update whosTurn's players balance on GUI
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(currentPlayer().getBalance());
		}
		GUI_GUI.getFields(currentPlayer().getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
		ListOfPlayers.getPlayers(whosTurn).setCurrentField(nextField);

		//Move player on GUI
		GUI_GUI.getFields(currentPlayer().getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
	}

	public void setOwner(Player player) {
		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][4] = whosTurn;
		Fields[ListOfPlayers.getPlayers(whosTurn).getCurrentField()][3] = 1;
		//		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		GUI_GUI.displayOwner(ListOfPlayers.getPlayers(whosTurn).getCurrentField(), player.getName());
	}

	public void removeOwner(int fieldnumber) {
		GUI_GUI.displayPrice(fieldnumber);
	}

	public static int getWhosTurn() {
		return whosTurn;
	}
}