package gamelogic;

import entity.Player;

public abstract class Chancecard {
	protected String text;
	public static int cards[] = new int [32];

	public Chancecard(String text) {
		this.text = text;
	}

	abstract public void drawCard(Player player);

	
	public String getName() {
		return text;
	}
}
