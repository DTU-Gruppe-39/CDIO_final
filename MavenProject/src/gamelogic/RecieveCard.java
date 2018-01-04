package gamelogic;

import entity.Player;

public class RecieveCard extends Chancecard {
	private int amount;

	public RecieveCard(String text, int amount)  {
		super(text);
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