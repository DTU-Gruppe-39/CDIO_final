package gamelogic;

import entity.Player;
import gamelogic.RecieveCard;

public abstract class Chancecard {
	private String text;
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public Chancecard(String text) {
		this.text = text;
	}

	

//	public int getAmount () {
//		return RecieveCard.getAmount();
//	}
	
	public String getChanceCardText() {
		return this.text;
	}
	
}
