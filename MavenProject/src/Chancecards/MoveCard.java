package Chancecards;

import entity.Player;

public class MoveCard extends Chancecard {
	private int amount;

	public MoveCard(String text, int amount) {
		super(text);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}

