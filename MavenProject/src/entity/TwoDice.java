	package entity;

public class TwoDice {

	private int die1;	//value of 1st die.
	private int die2;	//value of 2nd die.
	private int faces;

	public TwoDice() {
		this.die1 = 0;
		this.die2 = 0;	
		this.faces =6;
		// Add if you need 2 dice
	}
	
	
	public void roll() {		//Rolls both dice to a value between 1 and 6.
		//die1
		double d1=(float)Math.random();		// [0 ; 1[
		double d2 = d1*this.faces;				// [0 ; 6[
		this.die1 = (int)Math.ceil(d2);			// [1-6] integer

		//die2
		double d3=(float)Math.random();		// [0 ; 1[
		double d4 = d3*this.faces;				// [0 ; 6[
		this.die2 = (int)Math.ceil(d4);			// [1-6] integer

	} 
	public int getdie1() {	//Returns value of die 1.
		return this.die1;
	}
	public int getdie2() {	//Returns value of die 2.
		return this.die2;
	}
	public int getDiceTotal() {	//Returns the values of the two dice added together.
		return this.die1 + this.die2;
	}
}