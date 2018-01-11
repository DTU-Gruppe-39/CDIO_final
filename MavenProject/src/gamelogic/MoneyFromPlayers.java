package gamelogic;

import boundary.GUI_GUI;
import gamelogic.*;
import boundary.*;

public class MoneyFromPlayers extends Chancecard {
	private  int amount;
	private int numb;
	
	public MoneyFromPlayers(String text, int amount, int numb)  {
		super(text);
		this.amount = amount;
		this.numb = GUI_GUI.getNumberOfPlayers();
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
