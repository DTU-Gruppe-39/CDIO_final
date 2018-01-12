package Cards;


public class PayCard extends Chancecard {
	private  int amount;

	public PayCard(String text, int amount)  {
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