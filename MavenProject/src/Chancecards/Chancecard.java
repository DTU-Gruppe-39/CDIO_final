package Chancecards;

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
	
}
