package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.HousePrice;

public class Building {
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
	
	

}
