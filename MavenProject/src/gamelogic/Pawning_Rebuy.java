package gamelogic;

import boundary.GUI_Create;
import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.Player;

public class Pawning_Rebuy {
	int Fields[][];
	int whosTurn;
	public Pawning_Rebuy(int whosturn, int [][]fields) {
		this.whosTurn = whosturn;
		this.Fields = fields;
	}
	
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
	
	public void setPawned(int fieldnumber, boolean houseOnColor) {
		//		GUI_GUI.getFields(ListOfPlayers.getPlayers(whosTurn).getCurrentField()).setDescription("Ejes af: " + ListOfPlayers.getPlayers(whosTurn).getName());
		if (houseOnColor) {
			GUI_GUI.gui.showMessage("                            Du kan ikke pantsætte grunde med huse. Sælg dine huse først");
		} else {
			GUI_Create.displayOwner(fieldnumber, "("+ListOfPlayers.getPlayers(Game.getWhosTurn()).getName()+")");
			Fields[fieldnumber][8] = 1;
			ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(Fields[fieldnumber][7]);
			GUI_Create.getGuiPlayers(Game.getWhosTurn()).setBalance(ListOfPlayers.getPlayers(Game.getWhosTurn()).getBalance());
			if (Fields[fieldnumber][2] == 9) {
				ListOfPlayers.getPlayers(Game.getWhosTurn()).lostShippingCompany();			
		    } else if(Fields[fieldnumber][2] == 10) {
		    	ListOfPlayers.getPlayers(Game.getWhosTurn()).lostBrewery();
		    }
		}
		Game.setFields(this.Fields);
	}
	
	public void rebuy(int fieldnumber, Player player) {
		if (player.getBalance() < 1.1 * Fields[fieldnumber][7]) {
			GUI_GUI.gui.showMessage("                                            Du har ikke råd til at genkøbe denne grund");
		} else {
			GUI_Create.displayOwner(fieldnumber, player.getName());
			Fields[fieldnumber][8] = 0;
			player.setNewBalance(-1.1 * Fields[fieldnumber][7]);
			GUI_Create.getGuiPlayers(Game.getWhosTurn()).setBalance(player.getBalance());
			if (Fields[fieldnumber][2] == 9) {
				player.boughtShippingCompany();
			}
		}
		Game.setFields(this.Fields);
	}
}
