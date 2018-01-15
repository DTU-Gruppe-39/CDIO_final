package Chancecards;


public class RecieveCard extends Chancecard {
	private int amount;

	public RecieveCard(String text, int amount)  {
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