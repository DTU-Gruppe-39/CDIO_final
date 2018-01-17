package controller;

import boundary.GUI_Create;
import boundary.GUI_GUI;
import entity.Player;

public class Inside_Trading {
		int whosTurn;
		int [][] Fields;
		public Inside_Trading(int whosturn, int [][]fields) {
			this.whosTurn = whosturn;
			this.Fields = fields;
		}
		
		//best to set in building class
	public boolean hasPawnedOnColor(int fieldnumber) {
		boolean hasPawned;
		int pawned = 0;
		for (int j = 0; j < 40; j++) {
			if (Fields[j][2] == Fields[fieldnumber][2] && Fields[j][5] == 1) {
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
		String [] SFields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if(!(Game.getWhosTurn() == Fields[i][4]) && Fields[i][3] == 1) {
				//				System.out.println(getFields()[i][0]);
				SFields[i] = "" + Fields[i][0];
				if(SFields[i] != null) {
					SFields[i] = GUI_GUI.getTitles()[i];
					size++;
				}
			}
		}
		//		System.out.println(Arrays.deepToString(Fields));
		//		System.out.println(size);
		refinedFields = new String[size];
		int temp = 0;
		for (int i=0; i<40; i++) {
			if(SFields[i] != null) {
				refinedFields[temp] = SFields[i];
				temp++;
			}
		}
		//		System.out.println(Arrays.deepToString(refinedFields));		
		return refinedFields;
	}
	
	public void buyUsed(int fieldnumber, boolean hasBuildingsOnColor, Player player) {
		if (hasBuildingsOnColor) {
			GUI_GUI.gui.showMessage("Sælg alle huse på grunde med samme farve, som den valgte grund, før du kan lave indbyrdes handel");
		} else {
			int decidedPrice = choosePrice(Fields[fieldnumber][4], player);
			if (decidedPrice == 0) {
				//Transaction denied
				GUI_GUI.gui.showMessage("                                       	     Handlen blev afvist");
			} else {
				//Transaction confirmed
				player.setNewBalance(-1 * decidedPrice);
				ListOfPlayers.getPlayers(Fields[fieldnumber][4]).setNewBalance(decidedPrice);
				GUI_Create.getGuiPlayers(whosTurn).setBalance(player.getBalance());
				GUI_Create.getGuiPlayers(Fields[fieldnumber][4]).setBalance(ListOfPlayers.getPlayers(Fields[fieldnumber][4]).getBalance());
				if (Fields[fieldnumber][2] == 9) {
					ListOfPlayers.getPlayers(Fields[fieldnumber][4]).lostShippingCompany();
					player.boughtShippingCompany();
				}
				Fields[fieldnumber][4] = whosTurn;
				if (Fields[fieldnumber][8] != 0) {
					GUI_Create.displayOwner(fieldnumber, "(" + player.getName() + ")");  //Show after confirmation
				} else {
					GUI_Create.displayOwner(fieldnumber, player.getName()); 
				}
			}
		}
		Game.setFields(this.Fields);
	}
	
	
	public String chooseProperty() {
		return GUI_GUI.gui.getUserSelection("                                            Vælg hvilken grund du vil byde på", opponentsFields());
	}
	
	public int choosePrice(int owner, Player biddingPlayer) {
		int offerPrice = 0;
		boolean accepted;
		offerPrice = GUI_GUI.gui.getUserInteger("                            Indtast dit bud på grunden, bemærk at 0 betyder fortryd");
		if (offerPrice == 0) {
			//Transaction denied
			accepted = false;
		} else {	
			while (offerPrice > biddingPlayer.getBalance()) {
				GUI_GUI.gui.showMessage("                                            Du har ikke så mange penge, vælg et mindre beløb");
				offerPrice = GUI_GUI.gui.getUserInteger("                            Indtast dit bud på grunden, bemærk at 0 betyder fortryd");
			}
			accepted = GUI_GUI.gui.getUserLeftButtonPressed("                                            " + ListOfPlayers.getPlayers(owner).getName() + " vil du accepterer tilbuddet på " + offerPrice + "kr.?", "Accepter", "Afvis");
		}
		if (accepted && offerPrice != 0) {
			return offerPrice;
		} else {
			return 0;
		}
	}
}
