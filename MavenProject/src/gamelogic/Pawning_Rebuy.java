package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;

public class Pawning_Rebuy {
	int Fields[][] = Game.getFields();
	
	public String choosePawn() {
		return GUI_GUI.gui.getUserSelection("                                            Vælg hvilken grund du vil pantsætte", pawnableFields());
	}
	public String choosePawned() {
		return GUI_GUI.gui.getUserSelection("                                            Vælg hvilken grund du vil genkøbe", pawnedFields());
	}
	
	
	public String[] pawnableFields() {
		String [] SFields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if(Game.getWhosTurn() == Fields[i][4] && Fields[i][8] == 0) {
				//				System.out.println(getFields()[i][0]);
				SFields[i] = "" + Fields[i][0];
				if(SFields[i] != null) {
					SFields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
		//		System.out.println("Fields are " + Arrays.deepToString(Fields));
		//		System.out.println("size is " + size);

		refinedFields = new String[size];
		int temp = 0;
		for (int i=0; i<40; i++) {
			if(SFields[i] != null) {
				refinedFields[temp] = SFields[i];
				temp++;
			}
		}
		//		System.out.println("refined are " + Arrays.deepToString(refinedFields));		

		return refinedFields;
	}

	public String [] pawnedFields() {
		String [] SFields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if(Game.getWhosTurn() == Fields[i][4] && Fields[i][8] == 1) {
				SFields[i] = "" + Fields[i][0];
				if(SFields[i] != null) {
					SFields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
		refinedFields = new String[size];
		int temp = 0;
		for (int i=0; i<40; i++) {
			if(SFields[i] != null) {
				refinedFields[temp] = SFields[i];
				temp++;
			}
		}
		return refinedFields;
	}
	
	public void setPawned(int fieldnumber) {
		//		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		if (hasHousesOnColor(fieldnumber)) {
			GUI_GUI.gui.showMessage("                            Du kan ikke pantsætte grunde med huse. Sælg dine huse først");
		} else {
			GUI_GUI.displayOwner(fieldnumber, "("+ListOfPlayers.getPlayers(Game.getWhosTurn()).getName()+")");
			Fields[fieldnumber][8] = 1;
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(Fields[fieldnumber][7]);
			GUI_GUI.getGuiPlayers(Game.getWhosTurn()).setBalance(ListOfPlayers.getPlayers(Game.getWhosTurn()).getBalance());
			if (Fields[fieldnumber][2] == 9) {
				ListOfPlayers.getPlayers(Game.getWhosTurn()).lostShippingCompany();			
		    } else if(Fields[fieldnumber][2] == 10) {
		    	ListOfPlayers.getPlayers(Game.getWhosTurn()).lostBrewery();
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
}
