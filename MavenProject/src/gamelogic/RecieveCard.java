package gamelogic;

import entity.Player;

public class RecieveCard extends Chancecard {
	private static int amount;

	public RecieveCard(String text, int amount)  {
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
	
	@Override
	public String getChanceCardText() {
		return super.getChanceCardText();
	}
}