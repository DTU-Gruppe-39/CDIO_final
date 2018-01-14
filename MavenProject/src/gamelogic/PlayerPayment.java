package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;

public class PlayerPayment {
	Miscellaneous misc = new Miscellaneous();
	int Fields[][] = Game.getFields();
	private void payRent(int field, int die1, int die2) {
		int Fields[][] = new int [40][10];
		if (misc.doubleRent(field) && Fields[field][9] == 0 && Fields[field][2] != 9 && Fields[field][2] != 10) {
			//Multiply rent by 2
			if(misc.currentPlayer().getBalance()<=(misc.rent(field))) {
				setPawned(titleToInt(choosePawn()));							
			}
			misc.currentPlayer().setNewBalance(-2 * (misc.rent(field)));
			ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(2 * (misc.rent(field)));
			//Update recievers balance on GUI
			GUI_GUI.getGuiPlayers(misc.ownerOfCurrentField()).setBalance(ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).getBalance());
		} else {
			//Pay normal rent
			if (misc.currentPlayer().isDead()==false && ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).isDead()== false) { //Er det nødvendigt at tjekke om ejeren er død?
				while(misc.currentPlayer().getBalance()<=(misc.rent(field))) {
					setPawned(titleToInt(choosePawn()));	
				}
				if (misc.currentPlayer().getBalance()<(misc.rent(field))) {
					ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(misc.currentPlayer().getBalance());
					misc.currentPlayer().setBalance(0);
					//							changeOwnerToCreditor();
				}
				// Pay rent on shippingcompanies
				else if (Fields[field][2]==9) {
					// The owner of the shippingcompany only owns 1 shippingcompany
					if(misc.shippingCompaniesNumber()==1) {
						misc.currentPlayer().setNewBalance(-(Fields[field][1]));
						ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(misc.rent(field));
					}
					// The owner of the shippingcompany owns 2 shippingcompany
					else if (misc.shippingCompaniesNumber()==2) {
						misc.currentPlayer().setNewBalance(-Fields[field][1]*2);
						ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(misc.rent(field)*2);
					}
					// The owner of the shippingcompany owns 3 shippingcompany
					else if (misc.shippingCompaniesNumber()==3) {
						misc.currentPlayer().setNewBalance(-(Fields[field][1]*4));
						ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(misc.rent(field)*4);
					}
					// The owner of the shippingcompany owns 4 shippingcompany
					else if (misc.shippingCompaniesNumber()==4) {
						misc.currentPlayer().setNewBalance(-(Fields[field][1]*8));
						ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(misc.rent(field)*8);

				   }
				} else if (Fields[ListOfPlayers.getPlayers(misc.whosturn()).getCurrentField()][2]==10) {
					misc.currentPlayer().setNewBalance(-(Fields[field][1]*(die1+die2)*(misc.breweryNumber())));
					ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(Fields[field][1]*(die1+die2)*misc.breweryNumber());
				}
				  else  {
					misc.currentPlayer().setNewBalance(-(misc.rent(field)));
					ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).setNewBalance(misc.rent(field));	
				}
			}
			//Update recievers balance on GUI
			GUI_GUI.getGuiPlayers(misc.ownerOfCurrentField()).setBalance(ListOfPlayers.getPlayers(misc.ownerOfCurrentField()).getBalance());
		}
	}	
}
