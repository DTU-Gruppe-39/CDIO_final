package controller;


import boundary.GUI_GUI;  

import java.io.IOException;

import entity.TwoDice;
import entity.Miscellaneous;

public class Game {
	final static int MIN_POINTS = 0;
	private static int whosTurn;
	private static int FieldNumb = 40;
	private static int 	Attribute = 10;
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

	public static void gameLogic() throws IOException {
		//Game logic

		//Randomize whosTurn
		whosTurn = (int) Math.ceil(Math.random() * GUI_GUI.getNumberOfPlayers());


		//		//Create dice
		TwoDice dice = new TwoDice();
		
		ListOfPlayers.addFunds(GUI_GUI.getNumberOfPlayers());
		while ((GUI_GUI.getNumberOfPlayers()-1 == Miscellaneous.getNumberOfDeadPlayers()) ==false) {
			Turn turn = new Turn();
			Miscellaneous misc = new Miscellaneous(Game.getWhosTurn(), Game.getFields());	
			Pawning_Rebuy PawReb = new Pawning_Rebuy(Game.getWhosTurn(), Game.getFields());
			Building build = new Building(Game.getWhosTurn(), Game.getFields());	
			Inside_Trading InTr = new Inside_Trading(Game.getWhosTurn(), Game.getFields());
			
			
			if(ListOfPlayers.getPlayers(whosTurn).isDead()==false) {
				switch (GUI_GUI.gui.getUserSelection("                                            Det er: " + ListOfPlayers.getPlayers(whosTurn).getName() + "'s tur, vælg hvad du vil fortage dig", "Kast", "Byg huse/hotel", "Pantsæt grunde", "Genkøb", "Indbyrdes handel", "Sælg huse")) {
				case "Kast":
					System.out.println("1"); //Printout to console for log purposes.
					dice.roll();
					turn.updateTurn(dice.getdie1(), dice.getdie2(), ListOfPlayers.getPlayers(whosTurn));
					break;
				case "Byg huse/hotel":
					System.out.println("2"); //Printout to console for log purposes.
					if (build.LegalHouse().length != 0) {
						build.buyBuildings(misc.titleToInt(build.chooseHouse()), ListOfPlayers.getPlayers(whosTurn), InTr.hasPawnedOnColor(ListOfPlayers.getPlayers(whosTurn).getCurrentField()) );
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke alle grunde i denne farve endnu");
					}
					break;
				case "Pantsæt grunde":
					System.out.println("3"); //Printout to console for log purposes.
					if (PawReb.pawnableFields().length != 0) {							
						PawReb.setPawned(misc.titleToInt(PawReb.choosePawn()), misc.hasHousesOnColor(ListOfPlayers.getPlayers(whosTurn).getCurrentField()));
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke nogen grunde");
					}
					break;
				case "Genkøb":
					System.out.println("4"); //Printout to console for log purposes.
					if (PawReb.pawnedFields().length != 0) {							
						PawReb.rebuy(misc.titleToInt(PawReb.choosePawned()), ListOfPlayers.getPlayers(whosTurn));
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke nogen pantsatte grunde");
					}
					break;
				case "Indbyrdes handel": //Printout to console for log purposes.
					System.out.println("5");
					if (InTr.opponentsFields().length != 0) {							
						InTr.buyUsed(misc.titleToInt(InTr.chooseProperty()), misc.hasHousesOnColor(ListOfPlayers.getPlayers(whosTurn).getCurrentField()), ListOfPlayers.getPlayers(whosTurn));
					} else {
						GUI_GUI.gui.showMessage("                                            Dine modstandere ejer ikke nogen grunde");
					}
					break;
				case "Sælg huse": //Printout to console for log purposes.
					System.out.println("5");
					if (build.LegalHouseSale().length != 0) {							
						build.sellBuildings(misc.titleToInt(build.chooseSellHouse()), ListOfPlayers.getPlayers(whosTurn));
					} else {
						GUI_GUI.gui.showMessage("                                            Du ejer ikke nogen huse endnu");
					}
					break;
				default:
					System.out.println("Selection not recognized"); //Printout to console for log purposes.
					break;
				}
			} else {
				if (getWhosTurn() == GUI_GUI.getNumberOfPlayers()) {
					setWhosTurn(1);
				}
				else {
					increasWhosTurn();
				}
			}
		}
		GUI_GUI.gui.showMessage(findWinner() + " vandt spillet");
	}

	public static int getWhosTurn() {
		return whosTurn;
	}
	public static void increasWhosTurn() {
		whosTurn++;
	}
	
	public static void setWhosTurn(int turn) {
		whosTurn = turn;
	}
	public static String findWinner() {
		String name = ""; 
		for (int i = 1; i <= GUI_GUI.getNumberOfPlayers(); i++) {
			if (ListOfPlayers.getPlayers(i).getBalance() != 0) {
				name = ListOfPlayers.getPlayers(i).getName();
			}
		}
		return name;
	}
}