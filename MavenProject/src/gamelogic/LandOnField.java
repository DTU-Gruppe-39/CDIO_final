package gamelogic;

import boundary.GUI_GUI;
import controller.ListOfPlayers;
import entity.Player;

public class LandOnField {
	//Update the balance depending on the field	
		//[Attributes] = [FieldNumb, rent, color, isOwned, owner, isOwnable]
		public void handleField (int field, Player player, int die1, int die2) {
			if (Fields[field][3] == 1) {
				//Field is owned
				if (ownerOfCurrentField() == whosTurn) {
					//Lands on his own field
				} else if (Fields[field][8] == 1) {
					//Field is pawned
					
				} else {
					payRent(field, die1, die2);
				}
			} else if (Fields[field][5] == 0) {

				switch (field) {
				case 0: break;
				case 2: this.deck.drawCard();
				GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
				break;
				case 4:  //betal 4000 eller 10%;
					currentPlayer().setPropertyValue();			
					currentPlayer().setNetWorth();
					currentPlayer().CalculateTax();
					if(GUI_GUI.displayTaxChoice()==false) {
						currentPlayer().setNewBalance(-currentPlayer().getTax());
					}
					else {
						currentPlayer().setNewBalance(-4000);
					}
					break;
				case 7:	this.deck.drawCard();
				GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
				break;
				case 10:break;
				case 17:this.deck.drawCard();
				GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
				break;
				case 20:break;
				case 22:this.deck.drawCard();
				GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
				break;
				case 33:this.deck.drawCard();
				GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
				break;
				case 36:this.deck.drawCard();
				GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
				break;
				case 38: //betal skat
					currentPlayer().setNewBalance(-2000);
					break;
				default:
					break;
				}
			}

			else {
				//Buy field if it is ownable
				if (Fields[field][5] == 1 && GUI_GUI.displayBuyChoice()==true) {
					if(player.getBalance()<=Fields[player.getCurrentField()][6]) {
						setPawned(titleToInt(choosePawn()));
					}
					else {
						player.setNewBalance(-(Fields[player.getCurrentField()][6]));
						setOwner(ListOfPlayers.getPlayers(whosTurn));	
						// Updates the amount of shippingcompanies a player owns after he bought one
						if(Fields[field][2]==9){
							player.boughtShippingCompany();
						} else if (Fields[field][2]==10) {
							player.boughtBrewery();
						}
					}
				}
			}
			//Update whosTurn's players balance on GUI
			GUI_GUI.getGuiPlayers(whosTurn).setBalance(player.getBalance());
		}
		
		

}
