package gamelogic;

import entity.Player;
import gamelogic.RecieveCard;

public abstract class Chancecard {
	private String text;

	public Chancecard(String text) {
		this.text = text;
	}

//	abstract public void drawCard(Player player);

//	public int getAmount () {
//		return RecieveCard.getAmount();
//	}
	
	public String getChanceCardText() {
		return this.text;
	}
	
}
