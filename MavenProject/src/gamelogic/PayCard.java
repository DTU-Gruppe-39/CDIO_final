package gamelogic;

import entity.Player;

public class PayCard extends Chancecard {
	private static int amount;

	public PayCard(String text, int amount)  {
		super(text);
		this.amount = amount;
	}
	
	public static int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void drawCard(Player player) {
	}
}