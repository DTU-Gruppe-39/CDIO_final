package Chancecards;

public abstract class Chancecard {
	private String text;
	
	public Chancecard(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
