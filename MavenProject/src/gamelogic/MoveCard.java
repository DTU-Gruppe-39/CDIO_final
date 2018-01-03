package gamelogic;

import entity.Player;


public class MoveCard extends Chancecard {
	private int MoveTo;

	public MoveCard(String text, int MoveTo) {
		super(text);
		this.MoveTo = MoveTo;
	}

	@Override
	public void drawCard(Player player) {
		// TODO Auto-generated method stub

	}
}

