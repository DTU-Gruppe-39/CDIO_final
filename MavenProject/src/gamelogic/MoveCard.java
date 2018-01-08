package gamelogic;

import entity.Player;


public class MoveCard extends Chancecard {
	private static int MoveTo;

	public MoveCard(String text, int MoveTo) {
		super(text);
		this.MoveTo = MoveTo;
	}

	public static int getMoveTo() {
		return MoveTo;
	}

	public void setMoveTo(int moveTo) {
		MoveTo = moveTo;
	}

//	@Override
//	public void drawCard(Player player) {
//	}
}

