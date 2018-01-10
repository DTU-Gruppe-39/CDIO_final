package gamelogic;

import entity.Player;
import controller.ListOfPlayers;

public class PayCard extends Chancecard {
	private  int amount;

	public PayCard(String text, int amount)  {
		super(text);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

//	@Override
//	public void PerformAction(Player player) {
//		ListOfPlayers.getPlayers(Game.getWhosTurn()).setNewBalance(PayCard.getAmount());
//	}

//	@Override
//	public void drawCard(Player player) {
//	}
}