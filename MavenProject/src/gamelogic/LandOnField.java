package gamelogic;

import boundary.GUI_GUI;
import controller.ChanceDeck;
import controller.ListOfPlayers;
import entity.Player;

public class LandOnField {
	public LandOnField(int whosturn, int [][]fields) {
		this.whosTurn = whosturn;
		this.Fields = fields;
	}
		int whosTurn;
		int [][] Fields;
		Miscellaneous misc = new Miscellaneous(Game.getWhosTurn(), Game.getFields());	
		PlayerPayment playerPay = new PlayerPayment(Game.getWhosTurn(), Game.getFields());
		Pawning_Rebuy PawReb = new Pawning_Rebuy(Game.getWhosTurn(), Game.getFields());
		ChanceDeck deck = new ChanceDeck();
	//Update the balance depending on the field	
		//[Attributes] = [FieldNumb, rent, color, isOwned, owner, isOwnable]
		public void handleField (int field, Player player, int die1, int die2) {
			if (Fields[field][3] == 1) {
				//Field is owned
				if (misc.ownerOfCurrentField() == whosTurn) {
					//Lands on his own field
				} else if (Fields[field][8] == 1) {
					//Field is pawned
					
				} else {
					playerPay.payRent(field, die1, die2);
				}
			} else if (Fields[field][5] == 0) {

				switch (field) {
				case 0: break;
				case 2: this.deck.drawCard();
				GUI_GUI.gui.displayChanceCard(this.deck.ShowCardText());
				break;
				case 4:  //betal 4000 eller 10%;
					player.setPropertyValue();			
					player.setNetWorth();
					player.CalculateTax();
					if(GUI_GUI.displayTaxChoice()==false) {
						player.setNewBalance(-player.getTax());
					}
					else {
						player.setNewBalance(-4000);
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
					player.setNewBalance(-2000);
					break;
				default:
					break;
				}
			}

			else {
				//Buy field if it is ownable
				if (Fields[field][5] == 1 && GUI_GUI.displayBuyChoice()==true) {
					if(player.getBalance()<=Fields[player.getCurrentField()][6]) {
						PawReb.setPawned(misc.titleToInt(PawReb.choosePawn()), misc.hasHousesOnColor(field));
					}
					else {
						player.setNewBalance(-(Fields[player.getCurrentField()][6]));
						misc.setOwner(player);	
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
			Game.setFields(this.Fields);
		}
		public void movePlayer(Player player, int die1, int die2) {
			GUI_GUI.gui.setDice(die1, die2);
			int nextField = 0;
			int currField;
			int diceSum=0;
			//Get current field of player
			currField = player.getCurrentField();
			diceSum= die1 + die2;
			//Calculate next field with dice and current field
			//If above 40, then modulus 40
			nextField += currField + diceSum;
			if (nextField > 39) {
				nextField = (currField + diceSum) % 40;
				player.setNewBalance(4000);
				//Update whosTurn's players balance on GUI
				GUI_GUI.getGuiPlayers(whosTurn).setBalance(player.getBalance());
			}
			GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), false);
			ListOfPlayers.getPlayers(whosTurn).setCurrentField(nextField);

			//Move player on GUI
			GUI_GUI.getFields(player.getCurrentField()).setCar(GUI_GUI.getGuiPlayers(whosTurn), true);
		}
}
