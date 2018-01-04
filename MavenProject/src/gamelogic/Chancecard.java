package gamelogic;

import entity.Player;

public abstract class Chancecard {
	private String text;

	public Chancecard(String text) {
		this.text = text;
	}

	abstract public void drawCard(Player player);

	
	public String getChanceCardText() {
		return text;
	}
	
}
