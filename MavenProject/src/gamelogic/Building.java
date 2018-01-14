package gamelogic;

import boundary.GUI_Create;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.HousePrice;
import entity.Player;

public class Building {
	int whosTurn;
	int [][] Fields;
	public Building(int whosturn, int [][]fields) {
		this.whosTurn = whosturn;
		this.Fields = fields;
	}
	Miscellaneous misc = new Miscellaneous(Game.getWhosTurn(), Game.getFields());
	
	public void buyBuildings(int fieldnumber, Player player, boolean pawnedOnColor) {
		if (this.Fields[fieldnumber][8] != 0) {
			GUI_GUI.gui.showMessage("                                            Du kan ikke købe huse på en pantsat grund");
		} else if (pawnedOnColor) {
			GUI_GUI.gui.showMessage("                 Du kan ikke købe huse, da en af dine grunde i denne farve er pantsat");
		} else if (player.getBalance() < HousePrice.getHousePriceInt()[fieldnumber]) {
			GUI_GUI.gui.showMessage("                                            Du har ikke råd til at købe huse på denne grund");
		} else if (this.Fields[fieldnumber][9] < 4) {
			this.Fields[fieldnumber][9]++;
			GUI_Create.displayHouses(fieldnumber, this.Fields[fieldnumber][9]);  //Set house on GUI
			player.setNewBalance(-1 * HousePrice.getHousePriceInt()[fieldnumber]);
			GUI_Create.getGuiPlayers(this.whosTurn).setBalance(player.getBalance());
			System.out.println("Huset blev købt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else if(this.Fields[fieldnumber][9] == 4) {
			this.Fields[fieldnumber][9]++;
			GUI_Create.displayHotel(fieldnumber);  //Set house on GUI
			player.setNewBalance(-1 * HousePrice.getHousePriceInt()[fieldnumber]);
			GUI_Create.getGuiPlayers(this.whosTurn).setBalance(player.getBalance());
			System.out.println("Hotelet blev købt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else {
			//Tell user they can't buy any more houses
			GUI_GUI.gui.showMessage("                                            Du kan ikke købe flere huse");
		}
		Game.setFields(this.Fields);
	}
	
	public void sellBuildings(int fieldnumber, Player player) {
		if (this.Fields[fieldnumber][9] <= 4 && this.Fields[fieldnumber][9] > 0) {
			this.Fields[fieldnumber][9]--;
			GUI_Create.displayHouses(fieldnumber, this.Fields[fieldnumber][9]);  //Remove house on GUI
			player.setNewBalance(HousePrice.getHousePriceInt()[fieldnumber]/2);
			GUI_Create.getGuiPlayers(this.whosTurn).setBalance(player.getBalance());
			System.out.println("Huset blev solgt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else if(this.Fields[fieldnumber][9] == 5) {
			this.Fields[fieldnumber][9]--;
			GUI_Create.removeHotel(fieldnumber);  // Remove hotel on GUI
			GUI_Create.displayHouses(fieldnumber, this.Fields[fieldnumber][9]);
			player.setNewBalance(HousePrice.getHousePriceInt()[fieldnumber]/2);
			GUI_Create.getGuiPlayers(this.whosTurn).setBalance(player.getBalance());
			System.out.println("Hotelet blev købt på grunden " + GUI_GUI.getTitles()[fieldnumber]);
		} else {
			//Tell user they can't sell any more houses
			GUI_GUI.gui.showMessage("                                            Du kan ikke sælge flere huse");
		}
		Game.setFields(this.Fields);
	}
	
	public String [] LegalHouseSale() {
//		Consider changing the name of the String array
		String [] SFields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {

			if((this.whosTurn == this.Fields[i][4]) && this.Fields[i][9]>0) {
				//					&& !(getFields()[i][10] == 0)) {
				//				System.out.println(getFields()[i][0]);
				SFields[i] = "" + this.Fields[i][0];
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
	
	public String chooseHouse() {
		return GUI_GUI.gui.getUserSelection("                                            Hvor vil du købe hus?", LegalHouse() );
	}

	public String chooseSellHouse() {
		return GUI_GUI.gui.getUserSelection("                                            Hvor vil du sælge et hus?", LegalHouseSale() );
	}
	public String [] LegalHouse() {
		String [] SFields = new String[40];
		String [] refinedFields;
		int size = 0;
		for (int i=0; i<40; i++) {
			if((this.whosTurn == this.Fields[i][4]) && this.misc.ownsGroupFields(this.whosTurn, this.Fields[i][2])) {
				//				System.out.println(getFields()[i][0]);
				SFields[i] = "" + this.Fields[i][0];
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
}
