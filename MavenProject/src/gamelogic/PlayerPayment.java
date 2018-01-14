package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;

public class PlayerPayment {
	int whosTurn;
	int [][] Fields;
	public PlayerPayment(int whosturn, int [][]fields) {
		this.whosTurn = whosturn;
		this.Fields = fields;
	}
	Miscellaneous misc = new Miscellaneous(Game.getWhosTurn(), Game.getFields());
	Pawning_Rebuy PawReb = new Pawning_Rebuy(Game.getWhosTurn(), Game.getFields());
	
	public void payRent(int field, int die1, int die2) {
			if (this.misc.doubleRent(field) && this.Fields[field][9] == 0 && this.Fields[field][2] != 9 && this.Fields[field][2] != 10) {
			//Multiply rent by 2
			if(this.misc.currentPlayer().getBalance()<=(this.misc.rent(field))) {
				this.PawReb.setPawned(this.misc.titleToInt(this.PawReb.choosePawn()), this.misc.hasHousesOnColor(field));							
			}
			this.misc.currentPlayer().setNewBalance(-2 * (this.misc.rent(field)));
			ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(2 * (this.misc.rent(field)));
			//Update recievers balance on GUI
			GUI_GUI.getGuiPlayers(this.misc.ownerOfCurrentField()).setBalance(ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).getBalance());
		} else {
			//Pay normal rent
			if (this.misc.currentPlayer().isDead()==false && ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).isDead()== false) { //Er det nødvendigt at tjekke om ejeren er død?
				while(this.misc.currentPlayer().getBalance()<=(this.misc.rent(field))) {
					this.PawReb.setPawned(this.misc.titleToInt(this.PawReb.choosePawn()), this.misc.hasHousesOnColor(field));	
				}
				if (this.misc.currentPlayer().getBalance()<(this.misc.rent(field))) {
					ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(this.misc.currentPlayer().getBalance());
					this.misc.currentPlayer().setBalance(0);
					//							changeOwnerToCreditor();
				}
				// Pay rent on shippingcompanies
				else if (this.Fields[field][2]==9) {
					// The owner of the shippingcompany only owns 1 shippingcompany
					if(this.misc.shippingCompaniesNumber()==1) {
						this.misc.currentPlayer().setNewBalance(-(this.Fields[field][1]));
						ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(this.misc.rent(field));
					}
					// The owner of the shippingcompany owns 2 shippingcompany
					else if (this.misc.shippingCompaniesNumber()==2) {
						this.misc.currentPlayer().setNewBalance(-this.Fields[field][1]*2);
						ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(this.misc.rent(field)*2);
					}
					// The owner of the shippingcompany owns 3 shippingcompany
					else if (this.misc.shippingCompaniesNumber()==3) {
						this.misc.currentPlayer().setNewBalance(-(this.Fields[field][1]*4));
						ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(this.misc.rent(field)*4);
					}
					// The owner of the shippingcompany owns 4 shippingcompany
					else if (this.misc.shippingCompaniesNumber()==4) {
						this.misc.currentPlayer().setNewBalance(-(this.Fields[field][1]*8));
						ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(this.misc.rent(field)*8);

				   }
				} else if (this.Fields[ListOfPlayers.getPlayers(this.misc.whosturn()).getCurrentField()][2]==10) {
					this.misc.currentPlayer().setNewBalance(-(this.Fields[field][1]*(die1+die2)*(this.misc.breweryNumber())));
					ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(this.Fields[field][1]*(die1+die2)*this.misc.breweryNumber());
				}
				  else  {
					this.misc.currentPlayer().setNewBalance(-(this.misc.rent(field)));
					ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).setNewBalance(this.misc.rent(field));	
				}
			}
			//Update recievers balance on GUI
			GUI_GUI.getGuiPlayers(this.misc.ownerOfCurrentField()).setBalance(ListOfPlayers.getPlayers(this.misc.ownerOfCurrentField()).getBalance());
		}
			Game.setFields(this.Fields);
	}	
	
}
