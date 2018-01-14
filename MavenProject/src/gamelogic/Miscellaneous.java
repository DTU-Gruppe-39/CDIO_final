package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.GameBoard;
import entity.HousePrice;
import entity.Player;


public class Miscellaneous {
	
	int Fields[][] = Game.getFields();
	
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

	public boolean ownsGroupFields(int whosturn, int color) {
		int tempOwner = 0;
		int tempruns = 0;
		for (int j = 0; j < 40; j++) {
			if (Fields[j][2] == color && Fields[j][5] == 1) {
				if (Fields[j][4] == whosturn()) {
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
	public int whosturn() {
		return Game.getWhosTurn();
	}

	public int ownerOfCurrentField() {
		return Fields[ListOfPlayers.getPlayers(whosturn()).getCurrentField()][4];
	}
	public int rent(int field) {
		return HousePrice.getHouseRentPrice()[ListOfPlayers.getPlayers(whosturn()).getCurrentField()][Fields[field][9]];
	}
	public Player currentPlayer() {
		return ListOfPlayers.getPlayers(whosturn());
	}
	public int breweryNumber() {
		return  ListOfPlayers.getPlayers(ownerOfCurrentField()).getBreweriesOwned();
	}
	public int shippingCompaniesNumber() {
		return ListOfPlayers.getPlayers(ownerOfCurrentField()).getShippingCompaniesOwned();
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
	
}
