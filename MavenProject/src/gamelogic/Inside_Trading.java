package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;

public class Inside_Trading {

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
	
	
}
