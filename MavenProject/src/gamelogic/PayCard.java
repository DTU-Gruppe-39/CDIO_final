package gamelogic;

import entity.Player;

public class PayCard extends Chancecard {
	private int amount;

	public PayCard(String text, int amount)  {
		super(text);
		this.amount = amount;
	}
	@Override
	public void drawCard(Player player) {
		// TODO Auto-generated method stub
	}
}