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
		// TODO Auto-generated method stub
	}
}